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
import models.repo.UserRepo
import models.domain.User

@Singleton
class RegisterController @Inject()
    (val controllerComponents: ControllerComponents, user: UserRepo)
    (implicit val ec: ExecutionContext) 
    extends BaseController {

    val registerForm = Form(
      tuple(
        "username" -> nonEmptyText
        ,"password" -> nonEmptyText 
        ,"confirmPassword" -> nonEmptyText
      )
    )

    def create = Action.async { implicit request: Request[AnyContent] =>
        registerForm.bindFromRequest()(request, play.api.data.FormBinding.Implicits.formBinding).fold(
            formWithErrors => {
                println(formWithErrors)
                Future(Ok(JsObject(Seq("message" -> JsString("Invalid form.")))))
            },
            u => {
                if (u._2 != u._3) {
                    Future(Ok(JsObject(Seq("message" -> JsString("Passwords do not match.")))))
                } else {
                    user.get(u._1).map {
                        list => {
                            if (list.size > 0) {
                                Ok(JsObject(Seq("message" -> JsString("Account already exists."))))
                            } else {
                                user.create(User(u._1, u._2))
                                Ok(JsObject(Seq("message" -> JsString("Account successfully created."))))
                            }
                        }
                    }
                }
            }
        )
    }
}