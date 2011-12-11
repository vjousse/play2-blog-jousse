package controllers
import play.api.mvc._

/**
 * Provide security features
 */
trait Secured {

  /**
   * Provide a default secured action
   *
   * @see Authenticated
   */
  def SecuredAction(block: Request[AnyContent] => Result): Action[AnyContent] = {
    Security.Authenticated(
      onUnauthorized = (
        reqest => Results.Redirect(routes.Blog.login).withNewSession.flashing(
          "error" -> "You have to be logged in to access this page"
        )
      )
    )(Action[AnyContent](play.api.mvc.BodyParsers.parse.anyContent)(block))
  }

}


