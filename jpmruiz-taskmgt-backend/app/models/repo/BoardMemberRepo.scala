package models.repo

import javax.inject._
import java.util._
import slick.jdbc.JdbcProfile
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import models.domain._
import models.repo._


@Singleton
class BoardMemberRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,
    val user: UserRepo,
    val board: BoardRepo)
    () extends HasDatabaseConfigProvider[JdbcProfile] {

    import profile.api._

    case class BoardMemberTable(tag: Tag) extends Table[BoardMember](tag, "board_member") {
        def id = column[UUID]("id", O.PrimaryKey)
        def boardId = column[UUID]("board_id")
        def username = column[String]("user")

        def * = (id, boardId, username).mapTo[BoardMember]

        def boardfk = foreignKey("owner_fk", boardId, board.query)(_.id, onDelete=ForeignKeyAction.Cascade)
        def userfk = foreignKey("user_fk", username, user.query)(_.username)
    }

    val query = TableQuery[BoardMemberTable]

    db.run(
        query.schema.createIfNotExists
    )

    def create(param: BoardMember) = db.run(
        query += param
    )

    def get = db.run(
        query.result
    )

    def get(id: UUID) = db.run(
        query.filter(u => u.boardId === id).result
    )
}