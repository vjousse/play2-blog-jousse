resolvers ++= Seq(
    DefaultMavenRepository,
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

// Use the Play sbt plugin for Play projects
addSbtPlugin("play" % "sbt-plugin" % Option(System.getProperty("play.version")).getOrElse("2.0"))
