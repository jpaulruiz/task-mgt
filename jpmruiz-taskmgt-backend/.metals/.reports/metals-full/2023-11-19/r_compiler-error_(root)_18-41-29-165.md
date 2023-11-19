file:///D:/Practice/ScalaXVue/jpmruiz-taskmgt/jpmruiz-taskmgt-backend/app/pekko/ActorConfig.scala
### scala.reflect.internal.Types$TypeError: illegal cyclic inheritance involving class AbstractModule

occurred in the presentation compiler.

action parameters:
offset: 35
uri: file:///D:/Practice/ScalaXVue/jpmruiz-taskmgt/jpmruiz-taskmgt-backend/app/pekko/ActorConfig.scala
text:
```scala
package pekko

import pekko.PongA@@ctor
import com.google.inject.AbstractModule
import play.api.libs.concurrent.PekkoGuiceSupport

class ActorConfig extends AbstractModule with PekkoGuiceSupport {
  override def configure = {
    bindActor[PongActor]("pong-actor")
  }
}
```



#### Error stacktrace:

```

```
#### Short summary: 

scala.reflect.internal.Types$TypeError: illegal cyclic inheritance involving class AbstractModule