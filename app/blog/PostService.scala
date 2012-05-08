package jousse
package blog

import Error.unsafeValidation

import java.util.Date
import java.io.File

import scala.io.Source
import scala.collection.JavaConversions._

import com.typesafe.config.{ Config, ConfigFactory }
import scalaz.Validation

case class PostService(parser: Parser) {

  def postList(directory: File): List[Post] = {

    val files: List[File] = Option(directory.listFiles) map { _ toList } getOrElse (Nil)

    val posts: List[Post] = (files.map { file ⇒
      postFromMarkdown(Source.fromFile(file).getLines.toList)
      }).map { postValidation =>
        postValidation.toOption
    }.flatten

    posts
  }

  //TODO: Use a scalaz validation or Either here
  def postFromMarkdown(lines: List[String]): Validation[Exception,Post] =  unsafeValidation {
    val (header, content) = lines.span(l ⇒ l.trim != "---")

    val conf: Config = ConfigFactory.parseString(header.mkString("\n"))

    Post("Test title", parser.parse(content.tail.mkString("\n")), new Date)
  }

}
