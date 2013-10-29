package jousse
package blog

import scala.io.Source
import java.io.File
import play.api.libs.ws.WS
import play.api.libs.concurrent.Execution.Implicits._
import scala.concurrent._
import scala.concurrent.duration._

case class RemoteParser(remoteParserUrl: String) extends Parser {

  def parseMd(content: String): String = {
    val promise = WS.url(remoteParserUrl).post(content).map { response =>
      response.body
    }
    return Await.result(promise, 0 nanos)
  }

  def parseMd(file: File): String = {
    val lines = Source.fromFile(file).mkString
    parseMd(lines)
  }

  def parse(content: String): String = parseMd(content)

}
