file:///D:/Practice/ScalaXVue/jpmruiz-taskmgt/jpmruiz-taskmgt-backend/app/controllers/PekkoController.scala
### java.lang.NullPointerException

occurred in the presentation compiler.

action parameters:
offset: 984
uri: file:///D:/Practice/ScalaXVue/jpmruiz-taskmgt/jpmruiz-taskmgt-backend/app/controllers/PekkoController.scala
text:
```scala
package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

//pekko/akka imports
import pekko._
import pekko.ActorManager._
import pekko.BoardActor._
import org.apache.pekko.actor._
import org.apache.pekko.stream.Materializer
import org.apache.pekko.pattern.ask
import org.apache.pekko.util.Timeout
import concurrent.duration.DurationInt
import play.api.libs.streams.ActorFlow


@Singleton
class PekkoController @Inject()(
  @Named("pong-actor") manager: ActorRef,
  val controllerComponents: ControllerComponents)
  (implicit val system: ActorSystem,
  val materializer: Materializer) extends BaseController {

    def boardSocket(): WebSocket =
        WebSocket.accept[String, String] { request =>
            ActorFlow.actorRef (out => 
                BoardActor.props(out, manager)
            )
        }
    
    def taskSocket()@@
}
```



#### Error stacktrace:

```
scala.reflect.internal.Definitions$DefinitionsClass.isByNameParamType(Definitions.scala:428)
	scala.reflect.internal.TreeInfo.isStableIdent(TreeInfo.scala:140)
	scala.reflect.internal.TreeInfo.isStableIdentifier(TreeInfo.scala:113)
	scala.reflect.internal.TreeInfo.isPath(TreeInfo.scala:102)
	scala.tools.nsc.interactive.Global.stabilizedType(Global.scala:974)
	scala.tools.nsc.interactive.Global.typedTreeAt(Global.scala:822)
	scala.meta.internal.pc.SignatureHelpProvider.signatureHelp(SignatureHelpProvider.scala:23)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$signatureHelp$1(ScalaPresentationCompiler.scala:282)
```
#### Short summary: 

java.lang.NullPointerException