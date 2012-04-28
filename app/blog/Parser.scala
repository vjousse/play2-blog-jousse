package jousse
package blog

import scala.io.Source
import java.io.File
import play.api.libs.ws.WS

object Parser {

  def parseMd(file: File, remoteParserUrl: String): String = {
    val lines = Source.fromFile(file).mkString
    val promise = WS.url(remoteParserUrl).post(lines).map { response =>
      response.body
    }
    return promise.await.get
  }
}
