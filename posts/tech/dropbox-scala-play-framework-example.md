title: "How to use the Dropbox API with scala and/or Play Framework"
date: "2014-12-03"
---

Recently, I had to connect a scala application to a Dropbox account. As I didn't found any good example on how to do so, I decided to write a blog post about it. Here we are.

## Standalone scala application using Dropbox4s

[Dropbox4s](https://github.com/Shinsuke-Abe/dropbox4s) is a scala DSL on top of the [Java SDK](https://www.dropbox.com/developers/core/sdks/java) for the [Dropbox API](https://www.dropbox.com/developers). As I had some troubles to understand how it was supposed to be working, so I decided to write a sample sbt application. I've reimplemented the [dropbox Java tutorial](https://www.dropbox.com/developers/core/start/java) using Dropbox4s. Here is the code :


```Scala
import com.dropbox.core.{DbxAppInfo, DbxAuthFinish, DbxWebAuthNoRedirect}
import com.dropbox.core.DbxEntry.WithChildren
import dropbox4s.core.CoreApi
import dropbox4s.core.model.DropboxPath
import scala.language.postfixOps

object TestDropbox extends CoreApi {

  // implements fields
  val applicationName = "YourApplicationName"
  val version = "1.0.0" // your application version(string)
  val appKey = "INSERT_APP_KEY"
  val appSecret = "INSERT_APP_SECRET"

  val appInfo = new DbxAppInfo(appKey, appSecret)

  val webAuth = new DbxWebAuthNoRedirect(requestConfig, appInfo)

  val authorizeUrl: String = webAuth.start()

  def main(args: Array[String]) = {
    println("1. Go to: " + authorizeUrl)
    println("2. Click \"Allow\" (you might have to log in first)")
    println("3. Copy the authorization code.")

    val code = readLine("Please, past the authorization code here: ")

    // This will fail if the user enters an invalid authorization code.
    implicit val auth: DbxAuthFinish = webAuth.finish(code)
    val accessToken: String = auth.accessToken

    println("Linked account: " + client(accessToken).getAccountInfo().displayName)

    val appPath = DropboxPath("/")

    // Upload a file
    val localFile = new java.io.File("working-draft.txt")
    val remoteFile = DropboxPath("/magnum-opus.txt")
    val uploadedFile = localFile uploadTo remoteFile

    // If you want to erase the uploaded file each time 
    // (without creating versions with numbers)
    // Be sure to set the isForced parameter to true
    // val uploadedFile = localFile uploadTo(remoteFile, true)


    // List directory
    val children: WithChildren = appPath children

    for (child <- children) {
      println(" " + child.name + ": " + child.toString())
    }

    // Download file
    val myFile = remoteFile downloadTo "magnum-opus.txt"

  }
}

```

With the correct `sbt` configuration and the correct app keys, it should be working out of the box. Don't forget to add the following lines to your `build.sbt` file:

```
resolvers += "bintray" at "http://dl.bintray.com/shinsuke-abe/maven"

libraryDependencies += "com.github.Shinsuke-Abe" %% "dropbox4s" % "0.2.0"

```

## Play application with oAuth redirection

Having to copy/paste a token to be connected to Dropbox is not very handy. So I decided to write a Play Application implementing the oAuth workflow like documented on the [Dropbox blog](https://www.dropbox.com/developers/blog/45/using-oauth-20-with-the-core-api).

You'll [find the code on my Github account](https://github.com/vjousse/dropbox-scala-play).

Enjoy and have fun!
