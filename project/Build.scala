import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "jousse-blog-play"
    val appVersion      = "1.0"

    val appDependencies = Seq(
      "org.scalaz" %% "scalaz-core" % "6.0.4",
      "com.twitter" % "util-core" % "1.12.4",
      "eu.henkelmann" % "actuarius_2.9.2" % "0.2.4"
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
      resolvers ++= Seq(
        "twitter.com" at "http://maven.twttr.com/",
        "sonatype" at "http://oss.sonatype.org/content/repositories/releases",
        "sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots"
      )
    )

}
