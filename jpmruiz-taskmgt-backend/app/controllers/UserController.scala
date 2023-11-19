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
class UserController @Inject()
    (val controllerComponents: ControllerComponents, 
        user: UserRepo,
        Auth: Authenticator)
    (implicit val ec: ExecutionContext) 
    extends BaseController {

  def get = Auth.async { implicit request: UserRequest[AnyContent] =>
    user.get.map { list =>
        if (list.size > 0) Ok(Json.toJson(list))
        else Ok(JsObject(Seq("message" -> JsString("No users found."))))
    }
  }
}