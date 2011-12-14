package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def show(page: String) = {
    import views.html._
    Action {
      implicit request =>
      Ok(
        page match {
          case "index"    => index()
          case "contact"  => contact()
          case "cv"       => cv()
          case "research" => research()
          case "blog"     => blog.list()
          case _          => error404()
        }
      )
    }
  }

}
