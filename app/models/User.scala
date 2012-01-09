package jousse
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
  def authenticate(username: String, password: String): Option[User] = for {
      adminUsername <- conf("blog.username")
      adminPassword <- conf("blog.password")
      secretKey     <- conf("application.secret")
      hashPass      <- md5AsString(password)
      hashSecret    <- md5AsString(secretKey)
      hash          <- md5AsString(hashPass + hashSecret)
      if adminUsername == username
      if adminPassword == hash
    } yield User(username, password)

  private def conf(key: String) = Play.unsafeApplication.configuration getString key

  private def md5AsString(s: String) = Error.unsafeOption {

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

