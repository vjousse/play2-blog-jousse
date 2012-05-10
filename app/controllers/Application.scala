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
          case "index"    ⇒ Redirect(routes.Blog.list())
          case "contact"  ⇒ Ok(contact())
          case "cv"       ⇒ Ok(cv())
          case "research" ⇒ Ok(research())
          case _          ⇒ NotFound(views.html.error404())
        }
    }
  }

}
