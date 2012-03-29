package jousse
package blog

import org.clapper.markwrap._
import scala.io.Source
import java.io.File

object Parser {

  def parseMd(file: File): String = {
    val parser = MarkWrap.parserFor(file)
    parser.parseToHTML(Source.fromFile(file))
  }
}
