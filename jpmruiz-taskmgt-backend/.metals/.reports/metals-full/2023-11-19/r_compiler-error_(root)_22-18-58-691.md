file:///D:/Practice/ScalaXVue/jpmruiz-taskmgt/jpmruiz-taskmgt-backend/app/services/Services.scala
### scala.reflect.internal.Types$TypeError: illegal cyclic reference involving trait JdbcAPI

occurred in the presentation compiler.

action parameters:
uri: file:///D:/Practice/ScalaXVue/jpmruiz-taskmgt/jpmruiz-taskmgt-backend/app/services/Services.scala
text:
```scala
package services

import javax.inject._
import java.util._
import slick.jdbc.JdbcProfile
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import models.domain._
import models.repo._
import scala.concurrent._


@Singleton
class Services @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,
    val user: UserRepo,
    val board: BoardRepo,
    val boardMember: BoardMemberRepo)
    (implicit val ec: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
   
    import profile.api._
    
    def getBoardsFromUser(username:String) = db.run(
        boardMember.query
            .joinRight(board.query)
            .on(_.boardId === _.id)
            .filter { case (joinedBoardMember, joinedBoard) => (
                joinedBoardMember.map { caseBoardMember =>
                    caseBoardMember.username === username
                }.getOrElse(false)
            ) || joinedBoard.owner === username
            }
            .result
    ).map(_.map{
        case (caseBoardMember, caseBoard) =>
            Board(
                caseBoard.id,
                caseBoard.name,
                caseBoard.owner
            )
    })
}

```



#### Error stacktrace:

```

```
#### Short summary: 

scala.reflect.internal.Types$TypeError: illegal cyclic reference involving trait JdbcAPI