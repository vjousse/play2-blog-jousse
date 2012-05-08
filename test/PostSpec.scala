import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

import jousse.blog.Parser
import jousse.blog.PostService

case class DummyParser() extends Parser {
  def parse(content: String): String = content
}

class PostSpec extends Specification {

  val postHeaders = """
title: Test title
date: 2012-05-02
---
"""
  val postContent = """
# Test title

And _some_ random *content*.

## Second level"""

  val postValue = postHeaders + postContent

  val postService = new PostService(new DummyParser)

  val post = postService.postFromMarkdown(postValue.lines toList)

  "The post title" should {
    "be 'Test title'" in {
      post must beSome.which(_.title == "Test title")
    }
  }

  "The post content" should {
    "be not be changed" in {
      post must beSome.which(_.content == postContent)
    }
  }
}
