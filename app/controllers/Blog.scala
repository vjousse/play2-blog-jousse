package controllers

import play.api._
import play.api.mvc._

object Blog extends Controller {

  def list() = {
    Action {
      implicit request =>
      Ok(views.html.blog())
    }
  }

}
