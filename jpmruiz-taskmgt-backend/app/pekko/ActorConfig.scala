package pekko

import pekko.ActorManager
import com.google.inject.AbstractModule
import play.api.libs.concurrent.PekkoGuiceSupport

class ActorConfig extends AbstractModule with PekkoGuiceSupport {
  override def configure = {
    bindActor[ActorManager]("manager-actor")
  }
}