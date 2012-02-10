package jousse
package form

import play.api.data._
import play.api.data.Forms._
import validation.Constraints._

import jousse.models._

class LoginForm {

  val form = Form(
    tuple(
      "username" -> text,
      "password" -> text
    ) verifying ("Invalid username or password", result => result match {
      case (username, password) => User.authenticate(username, password).isDefined
    })
  )

}
