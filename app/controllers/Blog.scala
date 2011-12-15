package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.format.Formats._
import play.api.data.validation.Constraints._

import com.mongodb.casbah.Imports._

import views.html._
import jousse.models._
import jousse.dao._

object Blog extends Controller with Secured {

  val postForm = Form(
    of(Post.apply _, Post.unapply _)(
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
    postForm.bindFromRequest.fold(
      formWithErrors => BadRequest(blog.admin.createPost(formWithErrors)),
      post => Ok {
        PostDao insert post
        blog.admin.createPost(postForm)
      }
    )
  }
}
