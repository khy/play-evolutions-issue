package controllers

import play.api._
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import daos.PeopleDao

class Application extends Controller {

  def createPerson(name: String) = Action.async {
    PeopleDao.createPerson(name).map { id =>
      Created(id.toString)
    }
  }

}
