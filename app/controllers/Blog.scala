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

  def rss() = Action {
      implicit request =>
        Ok(blog.rss(env.postService.postList(Some(env.techPostsDirectory)))).as("application/rss+xml")
    }

  def tech() = Action {
      implicit request =>
        Ok(blog.list(env.postService.postList(Some(env.techPostsDirectory))))
    }

  def post(slug: String) = Action {
      implicit request => env.postService.findPostBySlug(slug, Some(env.techPostsDirectory)) match {
        case Some(p) => Ok(blog.post(p))
        case None => NotFound(views.html.error404())
      }
    }
}
