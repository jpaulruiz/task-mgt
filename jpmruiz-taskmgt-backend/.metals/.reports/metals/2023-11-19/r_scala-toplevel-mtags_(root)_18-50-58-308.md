id: file:///D:/Practice/ScalaXVue/jpmruiz-taskmgt/jpmruiz-taskmgt-backend/app/controllers/PekkoController.scala:[974..975) in Input.VirtualFile("file:///D:/Practice/ScalaXVue/jpmruiz-taskmgt/jpmruiz-taskmgt-backend/app/controllers/PekkoController.scala", "package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

//pekko/akka imports
import pekko._
import pekko.ActorManager._
import pekko.BoardActor._
import org.apache.pekko.actor._
import org.apache.pekko.stream.Materializer
import org.apache.pekko.pattern.ask
import org.apache.pekko.util.Timeout
import concurrent.duration.DurationInt
import play.api.libs.streams.ActorFlow


@Singleton
class PekkoController @Inject()(
  @Named("pong-actor") manager: ActorRef,
  val controllerComponents: ControllerComponents)
  (implicit val system: ActorSystem,
  val materializer: Materializer) extends BaseController {

    def boardSocket(): WebSocket =
        WebSocket.accept[String, String] { request =>
            ActorFlow.actorRef (out => 
                BoardActor.props(out, manager)
            )
        }
    
    def 
}")
file:///D:/Practice/ScalaXVue/jpmruiz-taskmgt/jpmruiz-taskmgt-backend/app/controllers/PekkoController.scala
file:///D:/Practice/ScalaXVue/jpmruiz-taskmgt/jpmruiz-taskmgt-backend/app/controllers/PekkoController.scala:36: error: expected identifier; obtained rbrace
}
^
#### Short summary: 

expected identifier; obtained rbrace