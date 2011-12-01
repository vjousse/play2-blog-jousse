package models

case class User(email: String, name: String, password: String)

object User {

  /**
   * Authenticate a User.
   */
  def authenticate(email: String, password: String): Option[User] = {
    //What an authentication
    Some(User(email, email, password))
  }


}

