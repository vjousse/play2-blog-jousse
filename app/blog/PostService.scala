package jousse
package blog

import Error.{unsafeValidation, unsafeOption}

import java.util.Date
import java.io.File
import java.io.FilenameFilter
import java.text.SimpleDateFormat

import scala.io.Source
import scala.util.Sorting
import scala.collection.JavaConversions._

import com.typesafe.config.{ Config, ConfigFactory }
import scalaz.{Validation, Failure}

case class PostService(parser: Parser, directory: Option[File] = None) {

  def findPostBySlug(slug: String): Option[Post] = directory.flatMap { d =>
    val file = new File(d, slug + ".md")
    if (file.exists) {
      postFromFile(file).toOption
    } else {
      None
    }
  }

  def postFromFile(file: File): Validation[Exception, Post] = {
    postFromMarkdown(Source.fromFile(file).getLines.toList,
        unsafeOption(file.getName.substring(0,file.getName.lastIndexOf('.'))))
  }

  def postList(): List[Post] = {

    //create a FilenameFilter and override its accept-method
    val filefilter = new FilenameFilter() {
      //only accept files ending by .md
      def accept(dir: File, name: String) = name.endsWith(".md")
    }

    val files: List[File] = directory.map(d =>
        d.listFiles.filter(
      file => (!file.isDirectory && file.getName().endsWith(".md"))
    )) map { _ toList } getOrElse (Nil)

    (files.map { file ⇒
      postFromFile(file)
    }).map { postValidation ⇒
      postValidation.toOption
    }.flatten.sortWith((p1, p2) => p1.createdAt.compareTo(p2.createdAt) > 0)

  }

  def postFromMarkdown(lines: List[String], slug: Option[String] = None): Validation[Exception, Post] = unsafeValidation {
    val (header, content) = lines.span(l ⇒ l.trim != "---")

    val conf: Config = ConfigFactory.parseString(header.mkString("\n"))
    val formatter = new SimpleDateFormat("yyyy-MM-dd")

    Post(conf.getString("title"),
      parser.parse(content.tail.mkString("\n")),
      formatter.parse(conf.getString("date")),
      unsafeOption(conf.getString("description")) map parser.parse,
      unsafeOption(conf.getString("description")),
      slug,
      unsafeOption(conf.getString("id")))
  }

}
