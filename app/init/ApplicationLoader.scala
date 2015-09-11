package init

import play.api.BuiltInComponentsFromContext
import play.api.{ApplicationLoader => PlayApplicationLoader}
import play.api.ApplicationLoader.Context
import play.api.routing.Router
import play.api.inject.{Injector, SimpleInjector, NewInstanceInjector}
import router.Routes
import play.api.db.slick.SlickComponents
import controllers._

class ApplicationLoader extends PlayApplicationLoader {

  def load(context: Context) = {
    (new ApplicationComponents(context)).application
  }

}

class ApplicationComponents(context: Context) extends BuiltInComponentsFromContext(context) with SlickComponents {

  lazy val applicationController = new Application

  lazy val assetsController = new Assets(httpErrorHandler)

  lazy val router: Router = new Routes(httpErrorHandler, applicationController, assetsController)

  override lazy val injector: Injector = new SimpleInjector(NewInstanceInjector) +
    router + crypto + httpConfiguration + configuration + api

}
