package models.domain

import java.util.UUID
import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Task(id: UUID, name: String, boardId: UUID, assignedUser: Option[String], isComplete: Boolean)

object Task {
    val tupled = (apply: (UUID,String,UUID,Option[String],Boolean) => Task).tupled

    implicit val taskWrites: Writes[Task] = Json.writes[Task]
}