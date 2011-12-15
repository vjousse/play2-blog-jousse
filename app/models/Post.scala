package jousse
package models

import com.novus.salat.annotations._
import com.mongodb.casbah.Imports._

case class Post (@Key("_id") title: String, content: String)
