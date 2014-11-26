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

case class PostService(parser: Parser) {

  def findPostBySlug(slug: String, directory: Option[File] = None): Option[Post] = directory.flatMap { d =>
    val file = new File(d, slug + ".md")
    if (file.exists) {
      postFromFile(file).toOption
    } else {
      None
    }
  }

  def postFromFile(file: File, encoding: String = "utf-8"): Validation[Exception, Post] = {
    postFromMarkdown(Source.fromFile(file, encoding).getLines.toList,
        unsafeOption(file.getName.substring(0,file.getName.lastIndexOf('.'))))
  }

  def postList(directory: File): List[Post] = {

    def isMarkdownFile(file: File): Boolean = !file.isDirectory && file.getName().toLowerCase().endsWith(".md")

    val files: List[File] =
        (for {
          files <- Option(directory.listFiles)
          filteredFiles = files.filter(isMarkdownFile)
        } yield filteredFiles.toList) getOrElse Nil


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
