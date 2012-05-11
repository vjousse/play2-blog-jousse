package jousse
package blog

import util.StringHelper

import java.util.Date

case class Post(title: String, content: String, createdAt: Date, description: Option[String] = None, slug: Option[String] = None, id: Option[String] = None) extends Ordered[Date] {

  def compare(that: Date): Int = {
    createdAt.compare(that)
  }

  def uniqueId: String = id match {
    case Some(i) => i
    case None => slug match {
      case Some(s) => s
      case None => StringHelper.slugify(title)
    }
  }

}
