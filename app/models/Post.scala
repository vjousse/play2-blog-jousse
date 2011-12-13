package jousse
package models

import com.mongodb.casbah.Imports._

case class Post (_id: ObjectId = new ObjectId, title: String, content: String)
