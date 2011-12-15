package jousse
package dao

import com.novus.salat._
import com.novus.salat.global._
import com.novus.salat.dao._

import com.mongodb.casbah.MongoCollection
import com.mongodb.casbah.Imports._

import jousse.models._

object PostDao extends SalatDAO[Post, ObjectId](
  collection = MongoConnection()("jousse_blog")("posts"))
