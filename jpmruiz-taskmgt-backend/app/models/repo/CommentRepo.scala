package models.repo

import javax.inject._
import java.util._
import slick.jdbc.JdbcProfile
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import models.domain.Comment
import models.repo._

@Singleton
class CommentRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,
    val task: TaskRepo)
    () extends HasDatabaseConfigProvider[JdbcProfile] {

    import profile.api._

    case class CommentTable(tag: Tag) extends Table[Comment](tag, "comment") {
        def id = column[UUID]("id", O.PrimaryKey)
        def message = column[String]("message")
        def taskId = column[UUID]("task_id")

        def * = (id, message, taskId).mapTo[Comment]
        def taskfk = foreignKey("task_fk", taskId, task.query)(_.id, onDelete=ForeignKeyAction.Cascade)
    }

    val query = TableQuery[CommentTable]

    db.run(
        query.schema.createIfNotExists
    )

    def create(param: Comment) = db.run(
        query += param
    )

    def get = db.run(
        query.result
    )

    def get(id: UUID) = db.run(
        query.filter(u => u.id === id).result.headOption
    )
}