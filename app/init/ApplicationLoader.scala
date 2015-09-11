package init

import play.api.BuiltInComponentsFromContext
import play.api.{ApplicationLoader => PlayApplicationLoader}
import play.api.ApplicationLoader.Context
import router.Routes
import controllers._

class ApplicationLoader extends PlayApplicationLoader {

  def load(context: Context) = {
    (new ApplicationComponents(context)).application
  }

}

class ApplicationComponents(context: Context) extends BuiltInComponentsFromContext(context) {

  lazy val applicationController = new Application

  lazy val assetsController = new Assets(httpErrorHandler)

  lazy val router = new Routes(httpErrorHandler, applicationController, assetsController)

}
