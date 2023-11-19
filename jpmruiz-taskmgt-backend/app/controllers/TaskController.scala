package controllers

//inject
import javax.inject._
//play
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json._

import scala.concurrent._
import java.util.UUID

// models
import models.repo._
import models.domain._

import security._

@Singleton
class TaskController @Inject()
    (val controllerComponents: ControllerComponents, 
        task: TaskRepo,
        Auth: Authenticator)
    (implicit val ec: ExecutionContext) 
    extends BaseController {

  def gets = Auth.async { implicit request: UserRequest[AnyContent] =>
    task.get.map { list =>
        if (list.size > 0) Ok(Json.toJson(list))
        else Ok(JsObject(Seq("message" -> JsString("No tasks found."))))
    }
  }

    def get(id: String) = Auth.async { implicit request: UserRequest[AnyContent] =>
        val uuid = UUID.fromString(id)
        task.get(uuid).map { list =>
            if (list.size > 0) Ok(Json.toJson(list))
            else Ok(JsObject(Seq("message" -> JsString("No tasks found."))))
        }
    }

  val createForm = Form(
      tuple(
        "name" -> nonEmptyText
        ,"boardId" -> nonEmptyText
        ,"user" -> optional(nonEmptyText)
        ,"isComplete" -> boolean
      )
    )

    def create = Auth.async { implicit request: UserRequest[AnyContent] =>
        createForm.bindFromRequest()(request, play.api.data.FormBinding.Implicits.formBinding).fold(
            formWithErrors => {
                println(formWithErrors)
                Future(Ok(JsObject(Seq("message" -> JsString("Invalid form.")))))
            },
            t => {
                val id = UUID.randomUUID()
                val taskUUID = UUID.fromString(t._2)
                task.create(
                    Task(
                        id,
                        t._1,
                        taskUUID,
                        t._3,
                        t._4
                    )
                ).map {
                    case size: Int if size > 0 => Ok(JsObject(Seq("id" -> JsString(id.toString))))
                    case _ => Ok(JsObject(Seq("message" -> JsString("Failed to create board."))))
                }
            }
        )
    }

    val updateForm = Form(
      tuple(
        "id" -> nonEmptyText
        ,"name" -> nonEmptyText
        ,"boardId" -> nonEmptyText
        ,"user" -> optional(nonEmptyText)
        ,"isComplete" -> boolean
      )
    )

    //can be used on both update and assigning user on the task
    def update = Auth.async { implicit request: UserRequest[AnyContent] =>
        updateForm.bindFromRequest()(request, play.api.data.FormBinding.Implicits.formBinding).fold(
            formWithErrors => {
                println(formWithErrors)
                Future(Ok(JsObject(Seq("message" -> JsString("Invalid form.")))))
            },
            t => {
                val uuid = UUID.fromString(t._1)
                val taskUUID = UUID.fromString(t._3)
                val updatedTask = Task(uuid, t._2, taskUUID, t._4, t._5)
                task.update(uuid, updatedTask).map{
                    case true =>
                        Ok(JsObject(Seq("message" -> JsString("Task successfully updated."))))
                    case false =>
                        Ok(JsObject(Seq("message" -> JsString("Error updating task."))))
                }
            }
        )
    }
}