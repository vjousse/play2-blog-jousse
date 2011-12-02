package jousse

case class Error(msg: String, exception: String = "") {

  override def toString = "%s - %s".format(msg, exception)
}

object Error {

  // returns None if an exception is thrown, Some[A] otherwise
  def unsafeOption[A](op: => A): Option[A] = try {
    Some(op)
  } catch {
    case exception => None
  }

}
