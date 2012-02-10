package jousse
package form

import play.api.data._
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.data.validation.Constraints._
import com.mongodb.casbah.Imports._
import java.util.Date

import jousse.models._


object PostForm {

  val form = Form(
    mapping(
      "title"   -> of[String].verifying(nonEmpty),
      "content" -> of[String].verifying(nonEmpty)
    )(Data.apply)(Data.unapply)
  )

  case class Data(title: String, content: String) {
    def toPost = Post(
      title,
      content,
      new Date,
      new Date,
      new ObjectId
    )

    def toPost(post: Post) = post.copy(
      title         = title,
      content       = content,
      updatedAtDate = new Date
    )

  }

  object Data {
    def fromPost(post: Post) = Data(
      post.title,
      post.content
    )
  }

}
