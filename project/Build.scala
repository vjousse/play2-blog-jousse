import sbt._
import Keys._
import play.Play.autoImport._

object ApplicationBuild extends Build {

  val appName         = "jousse-blog-play"
  val appVersion      = "1.0"

  val appDependencies = Seq(
    "org.scalaz" %% "scalaz-core" % "7.1.0",
    "com.twitter" % "util-core" % "1.12.4",
    "org.pegdown" % "pegdown" % "1.4.1",
    "eu.henkelmann" % "actuarius_2.10.0" % "0.2.6",
    ws
  )

  val main = Project(appName, file(".")).enablePlugins(play.PlayScala).settings(
    version := appVersion,
    libraryDependencies ++= appDependencies,
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
    resolvers ++= Seq(
      "twitter.com" at "http://maven.twttr.com/",
      "sonatype" at "http://oss.sonatype.org/content/repositories/releases",
      "sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots"
    )
  )

}
