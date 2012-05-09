package jousse

import play.api.Configuration
import java.io.File
import blog.{PostService, RemoteParser}

class JousseEnv(configuration: Configuration) {

  def remoteParserUrl: Option[String] = conf("blog.remoteParserUrl")
  def postsDirectory: File = new File("posts/")
  def parser: RemoteParser = RemoteParser(remoteParserUrl getOrElse "http://localhost:9001/")

  def postService: PostService = new PostService(parser)

  private def conf(key: String): Option[String] = configuration getString key
}
