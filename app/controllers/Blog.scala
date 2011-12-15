package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.format.Formats._
import play.api.data.validation.Constraints._

import views.html._
import jousse.models._

object Blog extends Controller with Secured {

  val postForm = Form(
    of(
      "title"   -> of[String].verifying(required),
      "content" -> of[String].verifying(required)
    )
  )

  def list() = {
    Action {
      implicit request =>
      Ok(blog.list())
    }
  }

  def admin() = IsAuthenticated { _ => implicit request =>
      Ok(blog.admin.index())
  }


  def createPostForm() = IsAuthenticated { _ => implicit request =>
      Ok(blog.admin.createPost(postForm))
  }

  def createPost() = IsAuthenticated { _ => implicit request =>
      Ok(blog.admin.createPost(postForm))
  }
}
