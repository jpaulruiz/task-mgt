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
class BoardMemberController @Inject()
    (val controllerComponents: ControllerComponents, 
        boardMember: BoardMemberRepo,
        Auth: Authenticator)
    (implicit val ec: ExecutionContext) 
    extends BaseController {

    def gets = Auth.async { implicit request: UserRequest[AnyContent] =>
        boardMember.get.map { list =>
            if (list.size > 0) Ok(Json.toJson(list))
            else Ok(JsObject(Seq("message" -> JsString("No board members found."))))
        }
    }

    def get(id: String) = Auth.async { implicit request: UserRequest[AnyContent] =>
        val uuid = UUID.fromString(id)
        boardMember.get(uuid).map { list =>
            if (list.size > 0) Ok(Json.toJson(list))
            else Ok(JsObject(Seq("message" -> JsString("No board members found."))))
        }
    }

    val createForm = Form(
      tuple(
        "boardId" -> nonEmptyText
        ,"username" -> nonEmptyText 
      )
    )

    def create = Auth.async { implicit request: UserRequest[AnyContent] =>
        createForm.bindFromRequest()(request, play.api.data.FormBinding.Implicits.formBinding).fold(
            formWithErrors => {
                println("invalid board member")
                println(formWithErrors)
                Future(Ok(JsObject(Seq("message" -> JsString("Invalid form.")))))
            },
            b => {
                val id = UUID.randomUUID()
                val boardUUID = UUID.fromString(b._1)
                boardMember.create(
                    BoardMember(
                        id,
                        boardUUID,
                        b._2
                    )
                ).map {
                    case size: Int if size > 0 => Ok(JsObject(Seq("id" -> JsString(id.toString))))
                    case _ => Ok(JsObject(Seq("message" -> JsString("Failed to create board member."))))
                }
            }
        )
    }
}