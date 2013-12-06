package jousse
package blog

import org.pegdown.{Extensions, PegDownProcessor}

case class PegDownParser() extends Parser {

  val transformer = new PegDownProcessor(Extensions.ALL)

  def parse(content: String): String = transformer.markdownToHtml(content)

}

