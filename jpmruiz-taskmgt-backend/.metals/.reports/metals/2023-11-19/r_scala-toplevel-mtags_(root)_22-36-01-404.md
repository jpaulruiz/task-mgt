id: file:///D:/Practice/ScalaXVue/jpmruiz-taskmgt/jpmruiz-taskmgt-backend/app/pekko/BoardActor.scala:[271..272) in Input.VirtualFile("file:///D:/Practice/ScalaXVue/jpmruiz-taskmgt/jpmruiz-taskmgt-backend/app/pekko/BoardActor.scala", "package pekko

import org.apache.pekko.actor._
import play.api.libs.json._

object BoardActor {
    def props(out: ActorRef, manager: ActorRef, id: String) = Props(new BoardActor(out,manager,id))

    case class Message(js: JsValue, id: String)
    case class 
}

class BoardActor(out: ActorRef, manager: ActorRef, id: String) extends Actor {
    import BoardActor._
    def receive = {
        case data: JsValue => {
            manager ! Message(data,id)
        }
    }
}")
file:///D:/Practice/ScalaXVue/jpmruiz-taskmgt/jpmruiz-taskmgt-backend/app/pekko/BoardActor.scala
file:///D:/Practice/ScalaXVue/jpmruiz-taskmgt/jpmruiz-taskmgt-backend/app/pekko/BoardActor.scala:11: error: expected identifier; obtained rbrace
}
^
#### Short summary: 

expected identifier; obtained rbrace