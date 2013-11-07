package jousse
import scalaz.{Validation, Failure, Success}

case class Error(msg: String, exception: String = "") {

  override def toString = "%s - %s".format(msg, exception)
}

object Error {

  // returns None if an exception is thrown, Some[A] otherwise
  def unsafeOption[A](op: => A): Option[A] = try {
    Some(op)
  } catch {
    case exception: Throwable => None
  }

  def unsafeValidation[A](op: => A): Validation[Exception,A] = try {
    Success(op)
  } catch {
    case e: Exception => {
      println(e)
      Failure(e)}
  }
}
