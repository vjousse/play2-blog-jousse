package jousse

import play.api.Configuration

class JousseEnv(configuration: Configuration) {

  def remoteParserUrl: Option[String] = conf("blog.remoteParserUrl")

  private def conf(key: String): Option[String] = configuration getString key
}
