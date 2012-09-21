package jousse
package blog

import eu.henkelmann.actuarius.ActuariusTransformer

case class ActuariusParser() extends Parser {

  val transformer = new ActuariusTransformer()

  def parse(content: String): String = transformer(content)

}

