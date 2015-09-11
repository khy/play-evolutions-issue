package daos

import scala.concurrent.Future
import play.api.Play
import slick.driver.JdbcProfile
import slick.driver.PostgresDriver.api._
import play.api.db.slick.DatabaseConfigProvider

object PeopleDao {

  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

  case class PersonRecord(id: Long, name: String)

  class PeopleTable(tag: Tag)
    extends Table[PersonRecord](tag, "people")
  {
    def id = column[Long]("id")
    def name = column[String]("name")

    def * = (id, name) <> (PersonRecord.tupled, PersonRecord.unapply)
  }

  object People extends TableQuery(new PeopleTable(_))

  def createPerson(name: String): Future[Long] = {
    val people = People.map(_.name).returning(People.map(_.id))
    val insert = people += name
    dbConfig.db.run(insert)
  }

}
