import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

import jousse.blog.Parser
import jousse.blog.Post
import jousse.blog.PostService

import java.text.SimpleDateFormat

case class DummyParser() extends Parser {
  def parse(content: String): String = content
}

class PostSpec extends Specification with ScalazValidationMatchers {

  val postHeaders = """
title: New Test title
date: "02-05-2012"
---
"""
  val postContent = """
# Test title

And _some_ random *content*.

## Second level"""

  val postValue = postHeaders + postContent

  val postService = new PostService(new DummyParser)

  val post = postService.postFromMarkdown(postValue.lines toList)

  "The post parsing" should {
    "be successful" in {
      post must beSuccess
    }


    "extract all informations" in {
      val formatter = new SimpleDateFormat("dd-MM-yyyy");
      post must succeedWith(Post("New Test title", postContent, formatter.parse("02-05-2012")))
    }

  }
}
