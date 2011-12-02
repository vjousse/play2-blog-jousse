package models
import java.security.MessageDigest
import java.math.BigInteger
import play.api.Play

case class User(username: String, password: String)

object User {

  /**
   * Authenticate a User.
   *
   * The values are taken from the global conf/application.con file
   */
  def authenticate(username: String, password: String): Option[User] = {

    val adminUsername = conf("blog.username") getOrElse("")
    val adminPassword = conf("blog.password") getOrElse("")
    val secretKey = conf("application.secret") getOrElse("")

    if (
      adminUsername == username &&
      adminPassword == md5AsString(md5AsString(password) + md5AsString(secretKey))
    )
      Some(User(username, password))
    else
      None
  }

  private def conf(key: String) = Play.unsafeApplication.configuration get key map (_.value)

  private def md5AsString(s: String) = {

    //We love JAVA, yes we do. Repeat after me
    val digest = MessageDigest.getInstance("MD5").digest(s.getBytes("UTF-8"))

    val bigInt = new BigInteger(1,digest)
    var hashtext = bigInt.toString(16)
    // Now we need to zero pad it if we actually want the full 32 chars.
    while(hashtext.length() < 32 ){
      hashtext = "0"+hashtext;
    }

    hashtext
  }

}

