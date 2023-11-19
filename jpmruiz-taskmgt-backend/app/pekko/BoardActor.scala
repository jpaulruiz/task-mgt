package pekko

import org.apache.pekko.actor._
import play.api.libs.json._

object BoardActor {
    def props(out: ActorRef, manager: ActorRef, id: String) = Props(new BoardActor(out,manager,id))

    case class Message(js: JsValue, id: String)
    case class Response(js: JsValue)
}

class BoardActor(out: ActorRef, manager: ActorRef, id: String) extends Actor {
    import BoardActor._
    def receive = {
        case data: JsValue => {
            manager ! Message(data,id)
        }
        case Response(js: JsValue) => out ! js
    }
}