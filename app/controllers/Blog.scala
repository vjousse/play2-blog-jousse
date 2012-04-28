package jousse
package controllers

import java.io.File;

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.format.Formats._
import play.api.data.validation.Constraints._

import views.html._
import jousse.blog.Parser

object Blog extends CustomController {

  def markdown() = Action {
      implicit request => {
        conf("blog.remoteParserUrl").map { remoteParserUrl =>
          Ok(blog.markdown("Trust the h^Wtype", new java.util.Date(), Parser.parseMd(app.getFile("posts/trust_the_htype.md"), remoteParserUrl), "trust_the_htype"))
        } getOrElse(InternalServerError("No remote parser available."))
      }
    }

  def phpLove() = Action {
      implicit request =>
        Ok(blog.phpLove())
    }

  def list() = Action {
      implicit request =>
        Ok(blog.list())
    }

}
