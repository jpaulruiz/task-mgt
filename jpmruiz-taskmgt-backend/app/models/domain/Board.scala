package models.domain

import java.util.UUID
import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Board(id: UUID, name: String, owner: String)

object Board {
    val tupled = (apply: (UUID,String,String) => Board).tupled

    implicit val boardWrites: Writes[Board] = Json.writes[Board]
}