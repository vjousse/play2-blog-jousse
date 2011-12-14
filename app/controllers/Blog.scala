package controllers

import play.api._
import play.api.mvc._
import play.api.data._

import views._
import jousse.models._

object Blog extends Controller with Secured {

  def list() = {
    Action {
      implicit request =>
      Ok(views.html.blog())
    }
  }

  def admin() = IsAuthenticated { _ => implicit request =>
      Ok(views.html.blogAdmin())
  }


  def createPost() = IsAuthenticated { _ => implicit request =>
      Ok(views.html.blogCreatePost())
  }
}
