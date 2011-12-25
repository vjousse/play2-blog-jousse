package jousse
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
import jousse.form.PostForm._

object Blog extends Controller with Secured {

  val postForm = form

  def list() = Action {
      implicit request =>
        Ok(blog.list(PostDao.findAll))
    }

  def admin() = IsAuthenticated { _ => implicit request => {
      val posts = PostDao.findAll
      Ok(blog.admin.index(posts))
    }
  }


  def newPost() = IsAuthenticated { _ => implicit request =>
      Ok(blog.admin.createPost(postForm))
  }

  def createPost() = IsAuthenticated { _ => implicit request =>
    postForm.bindFromRequest.fold(
      formWithErrors => BadRequest(blog.admin.createPost(formWithErrors)),
      postData => Ok {
        PostDao insert postData.toPost
        blog.admin.createPost(postForm)
      }
    )
  }

  def editPost(id: String) = IsAuthenticated { _ => implicit request =>
      PostDao.findOneByID(new ObjectId(id)) match {
        case Some(post) => Ok(blog.admin.editPost(post))
        case _          => Redirect(jousse.controllers.routes.Blog.list())
      }
  }

}
