package jousse
package blog

import scala.io.Source
import java.io.File
import play.api.libs.ws.WS

case class RemoteParser(remoteParserUrl: String) {

  def parseMd(content: String): String = {
    val promise = WS.url(remoteParserUrl).post(content).map { response =>
      response.body
    }
    return promise.await.get
  }

  def parseMd(file: File): String = {
    val lines = Source.fromFile(file).mkString
    parseMd(lines)
  }
}
