package jousse
package models

import com.novus.salat.annotations._
import com.mongodb.casbah.Imports._
import java.util.Date

case class Post (
  title: String,
  content: String,
  createdAtDate: Date,
  updatedAtDate: Date,
  _id: ObjectId
)
