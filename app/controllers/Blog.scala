package controllers

import play.api._
import play.api.mvc._
import play.api.data._

import views.html._
import jousse.models._

object Blog extends Controller with Secured {

  def list() = {
    Action {
      implicit request =>
      Ok(blog.list())
    }
  }

  def admin() = IsAuthenticated { _ => implicit request =>
      Ok(blog.admin.index())
  }


  def createPost() = IsAuthenticated { _ => implicit request =>
      Ok(blog.admin.createPost())
  }
}
