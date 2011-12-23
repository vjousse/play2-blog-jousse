package jousse
package form

import play.api.data._
import play.api.data.format.Formats._
import validation.Constraints._
import com.mongodb.casbah.Imports._
import java.util.Date

import jousse.models._


object PostForm {

  val form = Form(
    of(Data.apply _, Data.unapply _)(
      "title"   -> of[String].verifying(required),
      "content" -> of[String].verifying(required)
    )
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
