package jousse
package controllers

import java.io.File;

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.format.Formats._
import play.api.data.validation.Constraints._

import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._

import views.html._
import jousse.models._
import jousse.dao._
import jousse.form.PostForm._

object Blog extends Controller with Secured {

  val postForm = form

  def phpLove() = Action {
      implicit request =>
        Ok(blog.phpLove())
    }

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
    {
      //Upload file in any
      request.body.asMultipartFormData.map { formData =>
        formData.file("picture") map { picture =>
          picture.ref.moveTo(new File("/tmp/"+picture.filename))
        }
      }

      postForm.bindFromRequest.fold(
        formWithErrors => BadRequest(blog.admin.createPost(formWithErrors)),
        postData => Ok {
          PostDao insert postData.toPost
          blog.admin.createPost(postForm)
        }
      )
    }
  }

  def editPost(id: String) = IsAuthenticated { _ => implicit request =>
      PostDao.findOneByID(new ObjectId(id)) match {
        case Some(post) => Ok(blog.admin.editPost(post, postForm fill Data.fromPost(post)))
        case _          => Redirect(jousse.controllers.routes.Blog.list())
      }
  }


  def update(id: String) = IsAuthenticated { _ => implicit request =>
      PostDao.findOneByID(new ObjectId(id)) match {
        case Some(post) => postForm.bindFromRequest.fold(
          form => Ok(blog.admin.editPost(post, form)),
          data => updateAndRedirect(data toPost post)
        )
        case _ => Redirect(jousse.controllers.routes.Blog.list())
      }
  }

  private def updateAndRedirect(post: Post) = {

    val postDBObject = grater[Post].asDBObject(post)
    PostDao.update(DBObject("_id" -> post._id), postDBObject)

    Redirect(jousse.controllers.routes.Blog.editPost(post._id toString))
  }
}
