package security

import play.api.mvc._

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import javax.inject._

class UserRequest[A](val username: Option[String], request: Request[A]) extends WrappedRequest[A](request)

class Authenticator @Inject() (val parser: BodyParsers.Default)(implicit val executionContext: ExecutionContext)
    extends ActionBuilder[UserRequest, AnyContent]{

    override def invokeBlock[A](request: Request[A], block: (UserRequest[A]) => Future[Result]) = {
        request.session.get("id") match {
            case Some(id) => block(new UserRequest(Some(id),request))
            case None => Future.successful(Results.Unauthorized)
        }
    }
}