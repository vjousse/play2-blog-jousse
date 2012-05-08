import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

import jousse.blog.Parser
import jousse.blog.PostService

case class DummyParser() extends Parser {
  def parse(content: String): String = content
}

class PostSpec extends Specification with ScalazValidationMatchers {

  val postHeaders = """
title: Test title
date: "2012-05-02"
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

    "extract the title" in {
      post.toOption must beSome.which(_.title == "Test title")
    }

    "parse the content" in {
      post.toOption must beSome.which(_.content == postContent)
    }
  }
}
