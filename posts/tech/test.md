title: "Test post"
date: "2012-12-06"
description: "Description de test"
---

Il y a de ça 13 ans (que le temps passe vite !), je sortais d'un BAC Scientifique avec le choix entre une école prépa Math, ou un DUT informatique. J'ai __choisi en écountant mon instinct__ au lieu d'écouter la pression sociale et ça a été le premier choix qui m'a vraiment fait entrer dans « ma vie ». Si vous suivez votre instinct (ou que vous lui permettez au moins de s'exprimer) la seule chose que vous pourrez « regretter » _a posteriori_, c'est de __ne pas l'avoir suivi plus tôt__. Voici quelques conseils et mon expérience personnelle pour vous permettre de __construire votre propre vie__.

Un peu de sauts de lignes.

```scala
package jousse
package controllers

import java.io.File;

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.format.Formats._
import play.api.data.validation.Constraints._

import views.html._

object Blog extends CustomController {

  def rss() = Action {
      implicit request =>
        Ok(blog.rss(env.postService.postList())).as("application/rss+xml")
    }

  def tech() = Action {
      implicit request =>
        Ok(blog.list(env.postService.postList(Some(env.techPostsDirectory))))
    }

  def post(slug: String, year: String, month: String) = Action {
      implicit request => env.postService.findPostBySlug(slug) match {
        case Some(p) => Ok(blog.post(p))
        case None => NotFound(views.html.error404())
      }
    }
}
```
