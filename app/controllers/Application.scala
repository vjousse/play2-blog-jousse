package jousse
package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def show(page: String) = {
    import views.html._
    Action {
      implicit request ⇒
        page match {
          case "index"    ⇒ Ok(views.html.index())
          case "about"    ⇒ Ok(about())
          case "contact"  ⇒ Ok(contact())
          case "cv"       ⇒ Ok(cv())
          case "research" ⇒ Ok(research())
          case _          ⇒ NotFound(views.html.error404())
        }
    }
  }
  def index() = Action { Ok(views.html.index()) }

}
