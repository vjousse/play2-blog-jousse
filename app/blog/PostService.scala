package jousse
package blog

import Error.{unsafeValidation, unsafeOption}

import java.util.Date
import java.io.File
import java.text.SimpleDateFormat

import scala.io.Source
import scala.collection.JavaConversions._

import com.typesafe.config.{ Config, ConfigFactory }
import scalaz.Validation

case class PostService(parser: Parser) {

  def postList(directory: File): List[Post] = {

    val files: List[File] = Option(directory.listFiles) map { _ toList } getOrElse (Nil)

    (files.map { file ⇒
      postFromMarkdown(Source.fromFile(file).getLines.toList)
    }).map { postValidation ⇒
      postValidation.toOption
    }.flatten

  }

  //TODO: Use a scalaz validation or Either here
  def postFromMarkdown(lines: List[String]): Validation[Exception, Post] = unsafeValidation {
    val (header, content) = lines.span(l ⇒ l.trim != "---")

    val conf: Config = ConfigFactory.parseString(header.mkString("\n"))
    val formatter = new SimpleDateFormat("dd-MM-yyyy")

    Post(conf.getString("title"),
      parser.parse(content.tail.mkString("\n")),
      formatter.parse(conf.getString("date")),
      unsafeOption(conf.getString("description")))
  }

}
