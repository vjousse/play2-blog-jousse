package jousse
package controllers

import java.io.File;

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.format.Formats._
import play.api.data.validation.Constraints._

import views.html._

object Blog extends CustomController {

  def phpLove() = Action {
      implicit request =>
        Ok(blog.phpLove())
    }

  def list() = Action {
      implicit request =>
        Ok(blog.list(env.postService.postList()))
    }

  def post(slug: String) = Action {
      implicit request => env.postService.findPostBySlug(slug) match {
        case Some(p) => Ok(blog.post(p))
        case None => NotFound(views.html.error404())
      }
    }
}
