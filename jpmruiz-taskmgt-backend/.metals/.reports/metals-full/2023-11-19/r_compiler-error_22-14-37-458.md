file:///D:/Practice/ScalaXVue/jpmruiz-pekko/jpmruiz-pekko/app/pekko/PingActor.scala
### file%3A%2F%2F%2FD%3A%2FPractice%2FScalaXVue%2Fjpmruiz-pekko%2Fjpmruiz-pekko%2Fapp%2Fpekko%2FPingActor.scala:5: error: ; expected but : found
object PingActor:
                ^

occurred in the presentation compiler.

action parameters:
uri: file:///D:/Practice/ScalaXVue/jpmruiz-pekko/jpmruiz-pekko/app/pekko/PingActor.scala
text:
```scala
package pekko

import org.apache.pekko.actor._

object PingActor:
    def props(out: ActorRef, manager: ActorRef) = 
        // Props(classOf[PingActor])
        // Props.create(classOf[PingActor], out)
        Props(new PingActor(out,manager))

    case class SayHello(name: String)
    case class FromManager(name: String)


class PingActor(out: ActorRef, manager: ActorRef) extends Actor:
    import PingActor._
    def receive: Actor.Receive = 
        case SayHello(name) => 
            manager ! PongActor.SayHello(name)
        case s: String =>
            self ! SayHello(s)
        case FromManager(name) =>
            out ! name
```



#### Error stacktrace:

```
scala.meta.internal.parsers.Reporter.syntaxError(Reporter.scala:16)
	scala.meta.internal.parsers.Reporter.syntaxError$(Reporter.scala:16)
	scala.meta.internal.parsers.Reporter$$anon$1.syntaxError(Reporter.scala:22)
	scala.meta.internal.parsers.Reporter.syntaxError(Reporter.scala:17)
	scala.meta.internal.parsers.Reporter.syntaxError$(Reporter.scala:17)
	scala.meta.internal.parsers.Reporter$$anon$1.syntaxError(Reporter.scala:22)
	scala.meta.internal.parsers.ScalametaParser.syntaxErrorExpected(ScalametaParser.scala:421)
	scala.meta.internal.parsers.ScalametaParser.expect(ScalametaParser.scala:423)
	scala.meta.internal.parsers.ScalametaParser.accept(ScalametaParser.scala:427)
	scala.meta.internal.parsers.ScalametaParser.acceptStatSep(ScalametaParser.scala:447)
	scala.meta.internal.parsers.ScalametaParser.acceptStatSepOpt(ScalametaParser.scala:451)
	scala.meta.internal.parsers.ScalametaParser.statSeqBuf(ScalametaParser.scala:4462)
	scala.meta.internal.parsers.ScalametaParser.bracelessPackageStats$1(ScalametaParser.scala:4681)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$batchSource$11(ScalametaParser.scala:4692)
	scala.meta.internal.parsers.ScalametaParser.autoEndPos(ScalametaParser.scala:368)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$batchSource$10(ScalametaParser.scala:4692)
	scala.meta.internal.parsers.ScalametaParser.tryParse(ScalametaParser.scala:216)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$batchSource$1(ScalametaParser.scala:4684)
	scala.meta.internal.parsers.ScalametaParser.atPos(ScalametaParser.scala:319)
	scala.meta.internal.parsers.ScalametaParser.autoPos(ScalametaParser.scala:365)
	scala.meta.internal.parsers.ScalametaParser.batchSource(ScalametaParser.scala:4652)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$source$1(ScalametaParser.scala:4645)
	scala.meta.internal.parsers.ScalametaParser.atPos(ScalametaParser.scala:319)
	scala.meta.internal.parsers.ScalametaParser.autoPos(ScalametaParser.scala:365)
	scala.meta.internal.parsers.ScalametaParser.source(ScalametaParser.scala:4645)
	scala.meta.internal.parsers.ScalametaParser.entrypointSource(ScalametaParser.scala:4650)
	scala.meta.internal.parsers.ScalametaParser.parseSourceImpl(ScalametaParser.scala:135)
	scala.meta.internal.parsers.ScalametaParser.$anonfun$parseSource$1(ScalametaParser.scala:132)
	scala.meta.internal.parsers.ScalametaParser.parseRuleAfterBOF(ScalametaParser.scala:59)
	scala.meta.internal.parsers.ScalametaParser.parseRule(ScalametaParser.scala:54)
	scala.meta.internal.parsers.ScalametaParser.parseSource(ScalametaParser.scala:132)
	scala.meta.parsers.Parse$.$anonfun$parseSource$1(Parse.scala:29)
	scala.meta.parsers.Parse$$anon$1.apply(Parse.scala:36)
	scala.meta.parsers.Api$XtensionParseDialectInput.parse(Api.scala:25)
	scala.meta.internal.semanticdb.scalac.ParseOps$XtensionCompilationUnitSource.toSource(ParseOps.scala:17)
	scala.meta.internal.semanticdb.scalac.TextDocumentOps$XtensionCompilationUnitDocument.toTextDocument(TextDocumentOps.scala:206)
	scala.meta.internal.pc.SemanticdbTextDocumentProvider.textDocument(SemanticdbTextDocumentProvider.scala:54)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$semanticdbTextDocument$1(ScalaPresentationCompiler.scala:356)
```
#### Short summary: 

file%3A%2F%2F%2FD%3A%2FPractice%2FScalaXVue%2Fjpmruiz-pekko%2Fjpmruiz-pekko%2Fapp%2Fpekko%2FPingActor.scala:5: error: ; expected but : found
object PingActor:
                ^