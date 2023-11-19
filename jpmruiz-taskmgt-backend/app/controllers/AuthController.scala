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
import models.domain._
import models.repo._

import security._

@Singleton
class AuthController @Inject()(val controllerComponents: ControllerComponents,
  val user: UserRepo,
  val Auth: Authenticator)
  (implicit val ec: ExecutionContext) extends BaseController {

    val logonForm = Form(
      tuple(
        "username" -> nonEmptyText,
        "password" -> nonEmptyText
      )
    )

    def index = Action.async { implicit request: Request[AnyContent] =>
      logonForm.bindFromRequest()(request, play.api.data.FormBinding.Implicits.formBinding).fold(
          formWithErrors => {
              Future.successful(Ok(JsObject(Seq("message" -> JsString("Invalid form.")))))
          },
          u => {
              user.get(u._1).map {
                case Some(user) => {
                  if (user.password == u._2) {
                      Ok(JsObject(Seq("message" -> JsString("Logon successfully.")))).withSession("id" -> u._1)
                  }
                  else  Ok(JsObject(Seq("message" -> JsString("Invalid username or password."))))
                }
                case None => Ok(JsObject(Seq("message" -> JsString("User does not exist."))))
              }
          }
      )
    }

    def logout = Action { implicit request: Request[AnyContent] => 
      Results.Ok.withNewSession
    }

    def isAuthenticated = Auth { implicit request: UserRequest[AnyContent] => 
      Results.Ok
    }
}