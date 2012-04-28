package jousse
package controllers

import play.api._
import play.api.mvc._

import models._

trait CustomController extends Controller {

  val app: Application = Play.unsafeApplication

  def conf(key: String) = Play.unsafeApplication.configuration getString key

  protected def get(request: Request[_], name: String) =
    request.queryString get name flatMap { _.headOption }

  protected def getInt(request: Request[_], name: String) =
    get(request, name) map (_.toInt)
}
