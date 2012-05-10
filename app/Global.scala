import play.api._
import play.api.mvc._
import play.api.mvc.Results._

object Global extends GlobalSettings {
  override def onHandlerNotFound(requestHeader: RequestHeader): Result = {
    NotFound(views.html.error404())
  }
}
