package models.domain

import java.util.UUID
import play.api.libs.json._
import play.api.libs.functional.syntax._

case class BoardMember(id: UUID, boardId: UUID, userId: String)

object BoardMember{
    val tupled = (apply: (UUID,UUID,String) => BoardMember).tupled

    implicit val boardMemberWrites: Writes[BoardMember] = Json.writes[BoardMember]
}