package jousse

import play.api.Configuration
import java.io.File
import blog.{PostService, ActuariusParser}

class JousseEnv(configuration: Configuration) {

  def remoteParserUrl: Option[String] = conf("blog.remoteParserUrl")
  def postsDirectory: File = new File("posts/")
  //def parser: RemoteParser = RemoteParser(remoteParserUrl getOrElse "http://localhost:9001/")
  def parser: ActuariusParser = ActuariusParser()
  def postService: PostService = new PostService(parser, postsDirectory)

  private def conf(key: String): Option[String] = configuration getString key
}
