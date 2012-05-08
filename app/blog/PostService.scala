package jousse
package blog

import java.util.Date
import java.io.File

import scala.io.Source
import scala.collection.JavaConversions._

case class PostService(parser: Parser) {

  def postList(directory: File): List[Post] = {

    val files: List[File] = Option(directory.listFiles) map { _ toList } getOrElse (Nil)

    val posts: List[Post] = (files.map { file ⇒
      postFromMarkdown(Source.fromFile(file).getLines.toList)
    } toList).flatten

    posts
  }

  def postFromMarkdown(lines: List[String]): Option[Post] =  {
    val (header, content) = lines.span(l ⇒ l.trim != "---")

    if (!header.isEmpty && !content.isEmpty) {
      Some(Post("Test title", parser.parse(content.tail.mkString("\n")), new Date))
    } else {
      None
    }
  }

}
