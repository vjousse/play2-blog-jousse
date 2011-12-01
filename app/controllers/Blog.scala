package controllers

import play.api._
import play.api.mvc._
import play.api.data._

import models._
import views._

object Blog extends Controller {

  // -- Authentication

  val loginForm = Form(
    of(
      "username" -> text,
      "password" -> text
    ) verifying ("Invalid username or password", result => result match {
      case (username, password) => User.authenticate(username, password).isDefined
    })
  )

  /**
   * Login page.
   */
  def login = Action { implicit request =>
    Ok(html.login(loginForm))
  }

  /**
   * Handle login form submission.
   */
  def authenticate = Action { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.login(formWithErrors)),
      user => Redirect(routes.Blog.list).withSession("email" -> user._1)
    )
  }

  /**
   * Logout and clean the session.
   */
  def logout = Action {
    Redirect(routes.Blog.login).withNewSession.flashing(
      "success" -> "You've been logged out"
    )
  }

  def list() = {
    Action {
      implicit request =>
      Ok(views.html.blog())
    }
  }

}