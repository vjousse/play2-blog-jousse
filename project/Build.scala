import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "jousse-blog-play"
    val appVersion      = "1.0"

    val appDependencies = Seq(
      "org.scalaz" %% "scalaz-core" % "6.0.4",
      "com.twitter" % "util-core" % "1.12.4",
      "org.clapper" %% "markwrap" % "0.5.3"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      resolvers ++= Seq(
        "twitter.com" at "http://maven.twttr.com/"
      )
    )

}
