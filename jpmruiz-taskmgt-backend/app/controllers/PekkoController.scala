package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

//pekko/akka imports
import pekko._
import pekko.ActorManager._
import pekko.BoardActor._
import pekko.TaskActor._
import org.apache.pekko.actor._
import org.apache.pekko.stream.Materializer
import org.apache.pekko.pattern.ask
import org.apache.pekko.util.Timeout
import concurrent.duration.DurationInt
import play.api.libs.streams.ActorFlow

//js
import play.api.libs.json._


@Singleton
class PekkoController @Inject()(
  @Named("manager-actor") manager: ActorRef,
  val controllerComponents: ControllerComponents)
  (implicit val system: ActorSystem,
  val materializer: Materializer) extends BaseController {

    def boardSocket(): WebSocket =
        WebSocket.acceptOrResult[JsValue, JsValue] { request =>
            Future.successful(request.session.get("id") match {
                case None => Left(Forbidden)
                case Some(id) => {
                    Right(ActorFlow.actorRef { out =>
                        BoardActor.props(out, manager, id)
                    })
                }
            })
        }
    
    def taskSocket(): WebSocket = 
        WebSocket.accept[JsValue, JsValue] { request =>
            ActorFlow.actorRef (out => 
                TaskActor.props(out, manager)
            )
        }
}