package models.repo

import javax.inject._
import java.util._
import slick.jdbc.JdbcProfile
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import models.domain._
import models.repo._
import scala.concurrent._

@Singleton
class BoardRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,
    val user: UserRepo)
    (implicit val ec: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

    import profile.api._

    case class BoardTable(tag: Tag) extends Table[Board](tag, "board") {
        def id = column[UUID]("id", O.PrimaryKey)
        def name = column[String]("name")
        def owner = column[String]("owner")

        def * = (id, name, owner).mapTo[Board]

        def ownerfk = foreignKey("owner_fk", owner, user.query)(_.username)
    }

    val query = TableQuery[BoardTable]

    db.run(
        query.schema.createIfNotExists
    )

    def create(param: Board) = db.run(
        query += param
    )

    def get = db.run(
        query.result
    )

    def get(id: UUID) = db.run(
        query.filter(u => u.id === id).result.headOption
    )
    
    def update(id: UUID, param: Board) = db.run(
        // query.filter(_.id === id).delete andThen (query += param.copy(id = id)).map(_ > 0)
        query.filter(_.id === id).update(param).map(_>0)
    )

    def delete(id: UUID) = db.run(
        query.filter(_.id === id).delete.map(_ > 0)
    )
}