package jousse
package blog

import util.StringHelper

import java.util.Date

case class Post(title: String, content: String, createdAt: Date, description: Option[String] = None, slug: Option[String] = None) extends Ordered[Date] {

  def compare(that: Date): Int = {
    createdAt.compare(that)
  }

}
