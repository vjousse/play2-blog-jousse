package jousse
package models

import com.novus.salat.annotations._
import com.mongodb.casbah.Imports._

case class Post (title: String, content: String, _id: ObjectId)
