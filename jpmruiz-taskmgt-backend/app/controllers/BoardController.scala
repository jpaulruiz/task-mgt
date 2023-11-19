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

import services._
import security._

@Singleton
class BoardController @Inject()
    (val controllerComponents: ControllerComponents, 
        board: BoardRepo,
        service: Services,
        Auth: Authenticator)
    (implicit val ec: ExecutionContext) 
    extends BaseController {

    def get = Auth.async { implicit request: UserRequest[AnyContent] =>
        service.getBoardsFromUser(request.username.get).map { list =>
            if (list.size > 0) Ok(Json.toJson(list.toSet))
            else Ok(JsObject(Seq("message" -> JsString("No boards found."))))
        }
    }

    val createForm = Form(
      tuple(
        "name" -> nonEmptyText
        ,"owner" -> nonEmptyText 
      )
    )

    def create = Auth.async { implicit request: UserRequest[AnyContent] =>
        createForm.bindFromRequest()(request, play.api.data.FormBinding.Implicits.formBinding).fold(
            formWithErrors => {
                println(formWithErrors)
                Future(Ok(JsObject(Seq("message" -> JsString("Invalid form.")))))
            },
            b => {
                val id = UUID.randomUUID()
                board.create(
                    Board(
                        id,
                        b._1,
                        b._2
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
        ,"owner" -> nonEmptyText 
      )
    )

    def update = Auth.async { implicit request: UserRequest[AnyContent] =>
        updateForm.bindFromRequest()(request, play.api.data.FormBinding.Implicits.formBinding).fold(
            formWithErrors => {
                println(formWithErrors)
                Future(Ok(JsObject(Seq("message" -> JsString("Invalid form.")))))
            },
            b => {
                val uuid = UUID.fromString(b._1)
                val updatedBoard = Board(uuid, b._2, b._3)
                println(updatedBoard)
                board.update(uuid, updatedBoard).map{
                    case true =>
                        Ok(JsObject(Seq("message" -> JsString("Board successfully updated."))))
                    case false =>
                        Ok(JsObject(Seq("message" -> JsString("Error updating board."))))
                }
            }
        )
    }

    val deletForm = Form(
        tuple(
            "id" -> nonEmptyText
            ,"none" -> ignored(text)
        )
    )

    def delete = Action.async { (request: Request[AnyContent]) =>
        deletForm.bindFromRequest()(request, play.api.data.FormBinding.Implicits.formBinding).fold(
            formWithErrors => {
                Future(Ok(JsObject(Seq("message" -> JsString("Invalid form.")))))
            },
            b => {
                val uuid = UUID.fromString(b._1)
                board.delete(uuid).map {
                    case true =>
                        Ok(JsObject(Seq("message" -> JsString("Board successfully deleted."))))
                    case false =>
                        Ok(JsObject(Seq("message" -> JsString("Error deleting board."))))
                }
            }
        )
    }

}