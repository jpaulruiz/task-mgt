package models.domain

import play.api.libs.json._
import play.api.libs.functional.syntax._
import java.util.UUID

case class Comment(id: UUID, message: String, taskId: UUID)

object Comment {
    val tupled = (apply: (UUID,String,UUID) => Comment).tupled

    implicit val commentWrites: Writes[Comment] = Json.writes[Comment]
}