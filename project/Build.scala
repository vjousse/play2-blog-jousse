import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "jousse-blog-play"
    val appVersion      = "1.0"

    val appDependencies = Seq(
      "com.mongodb.casbah" %% "casbah" % "2.1.5-1",
      "com.novus" %% "salat-core" % "0.0.8-SNAPSHOT"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      resolvers ++= Seq(
        "repo.novus rels" at "http://repo.novus.com/releases/",
        "repo.novus snaps" at "http://repo.novus.com/snapshots/"
      )
    )

}
