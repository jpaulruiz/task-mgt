package models.repo

import javax.inject._
import java.util._
import slick.jdbc.JdbcProfile
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import models.domain._
import models.repo._
import scala.concurrent._

@Singleton
class TaskRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,
    val user: UserRepo,
    val board: BoardRepo)
    (implicit val ec: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

    import profile.api._

    case class TaskTable(tag: Tag) extends Table[Task](tag, "task") {
        def id = column[UUID]("id", O.PrimaryKey)
        def name = column[String]("name")
        def boardId = column[UUID]("board_id")
        def assignedUser = column[Option[String]]("assigned_user")
        def isComplete = column[Boolean]("is_complete")

        def * = (id, name, boardId,assignedUser,isComplete).mapTo[Task]

        def boardfk = foreignKey("board_fk", boardId, board.query)(_.id, onDelete=ForeignKeyAction.Cascade)
        // def userfk = foreignKey("user_fk", assignedUser, user.query)(_.username)
    }

    val query = TableQuery[TaskTable]

    db.run(
        query.schema.createIfNotExists
    )

    def create(param: Task) = db.run(
        query += param
    )

    def get = db.run(
        query.result
    )

    def get(id: UUID) = db.run(
        query.filter(u => u.boardId === id).result
    )

    def update(id: UUID, param: Task) = db.run(
        query.filter(_.id === id).delete andThen (query += param.copy(id = id)).map(_ > 0)
    )

    def delete(id: UUID) = db.run(
        query.filter(_.id === id).delete.map(_ > 0)
    )
}