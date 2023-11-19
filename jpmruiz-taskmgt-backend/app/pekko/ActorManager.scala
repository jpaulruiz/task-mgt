package pekko

import org.apache.pekko.actor._
import javax.inject._
import services._
import play.api.libs.json._
import play.api.mvc._
import scala.concurrent.ExecutionContext

object ActorManager {
    def props = Props(classOf[ActorManager])
}

@Singleton
class ActorManager @Inject()
    (val service: Services)
    (implicit val ec: ExecutionContext) extends Actor {
    import BoardActor.Message
    import BoardActor.Response
    def receive = {
        case Message(js: JsValue, id: String) => {
            val send = sender()
            service.getBoardsFromUser(id).map { list =>
                if (list.size > 0) send ! Response(Json.toJson(list.toSet))
                else send ! Response(JsObject(Seq("message" -> JsString("No boards found."))))
            }
        }
    }
}