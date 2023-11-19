id: file:///D:/Practice/ScalaXVue/jpmruiz-taskmgt/jpmruiz-taskmgt-backend/app/pekko/BoardActor.scala:[207..208) in Input.VirtualFile("file:///D:/Practice/ScalaXVue/jpmruiz-taskmgt/jpmruiz-taskmgt-backend/app/pekko/BoardActor.scala", "package pekko

import org.apache.pekko.actor._
import play.api.libs.json._

object BoardActor {
    def props(out: ActorRef, manager: ActorRef) = Props(new BoardActor(out,manager))

    case class 
}

class BoardActor(out: ActorRef, manager: ActorRef) extends Actor {
    def receive = {
        case pong: JsValue => {
            manager ! pong
        }
    }
}")
file:///D:/Practice/ScalaXVue/jpmruiz-taskmgt/jpmruiz-taskmgt-backend/app/pekko/BoardActor.scala
file:///D:/Practice/ScalaXVue/jpmruiz-taskmgt/jpmruiz-taskmgt-backend/app/pekko/BoardActor.scala:10: error: expected identifier; obtained rbrace
}
^
#### Short summary: 

expected identifier; obtained rbrace