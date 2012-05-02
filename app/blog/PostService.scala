package jousse
package blog

import java.util.Date
import java.io.File

import scala.io.Source
import scala.collection.JavaConversions._

case class PostService(parser: RemoteParser) {

  def postList(directory: File): List[Post] = {

    val files: List[File] = Option(directory.listFiles) map { _ toList } getOrElse (Nil)

    val posts: List[Post] = files.map { file ⇒
      postFromMarkdown(Source.fromFile(file).getLines.toList)
    } toList

    posts
  }

  def postFromMarkdown(lines: List[String]): Post = {
    val header = lines.takeWhile(l ⇒ l.trim != "---")
    val content = lines.dropWhile(l ⇒ l.trim != "---").tail.mkString

    Post("Test title", parser.parseMd(content), new Date)
  }

}
