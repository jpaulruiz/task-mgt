package pekko

import org.apache.pekko.actor._

object TaskActor {
    def props(out: ActorRef, manager: ActorRef) = Props(new TaskActor(out,manager))
}

class TaskActor(out: ActorRef, manager: ActorRef) extends Actor {
    def receive = ???
}