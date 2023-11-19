id: file://<WORKSPACE>/app/services/Services.scala:[224..225) in Input.VirtualFile("file://<WORKSPACE>/app/services/Services.scala", "package services

import javax.inject._
import java.util._
import slick.jdbc.JdbcProfile
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import models.domain._
import models.repo._

case class 

@Singleton
class Services @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,
    val user: UserRepo,
    val board: BoardRepo,
    val boardMember: BoardMemberRepo)
    () extends HasDatabaseConfigProvider[JdbcProfile] {

    def get(username: String) = db.run(
        board.query
            .join(boardMember.query)
            .filter( bm => bm._2.userId === username || bm._1.owner === username)
            .result
    ).map(_.map{
        case (b, m) => 
            Board(
                b._1,
                b._2,
                b._3
            )
    })
}")
file://<WORKSPACE>/app/services/Services.scala
file://<WORKSPACE>/app/services/Services.scala:12: error: expected identifier; obtained at
@Singleton
^
#### Short summary: 

expected identifier; obtained at