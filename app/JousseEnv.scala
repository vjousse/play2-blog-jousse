package jousse

import play.api.Configuration
import java.io.File
import blog.{PostService, PegDownParser}

class JousseEnv(configuration: Configuration) {

  def remoteParserUrl: Option[String] = conf("blog.remoteParserUrl")
  def techPostsDirectory: Option[File] = conf("blog.techPostsDirectory").map { s => new File(s) }
  //def parser: RemoteParser = RemoteParser(remoteParserUrl getOrElse "http://localhost:9001/")
  //def parser: ActuariusParser = ActuariusParser()
  def parser: PegDownParser = PegDownParser()
  def postService: PostService = new PostService(parser)

  private def conf(key: String): Option[String] = configuration getString key
  private def confUnsafe(key: String): String = configuration.getString(key).get
}
