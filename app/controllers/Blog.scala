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

  def markdown() = Action {
      implicit request => {
        env.remoteParserUrl.map { remoteParserUrl =>
          //Ok(blog.markdown("Trust the h^Wtype", new java.util.Date(), Parser.parseMd(app.getFile("posts/2012-05-02-trust-the-htype.md"), remoteParserUrl), "trust_the_htype"))
          Ok("Test")
        } getOrElse(InternalServerError("No remote parser available."))
      }
    }

  def phpLove() = Action {
      implicit request =>
        Ok(blog.phpLove())
    }

  def list() = Action {
      implicit request =>
        Ok(blog.list(env.postService.postList(env.postsDirectory)))
    }

}
