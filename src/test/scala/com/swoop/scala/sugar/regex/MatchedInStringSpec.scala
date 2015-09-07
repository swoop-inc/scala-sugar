package com.swoop.scala.sugar.regex

import org.scalatest.{Matchers, WordSpec}

import scala.util.matching.Regex.Match

class MatchedInStringSpec extends WordSpec with Matchers {
  type Klass = MatchedInString

  val fixtures = new {
    val reMatch: Option[Match] = "a+(b+)".r.findFirstMatchIn("aabbcc")
  }

  "MatchedInString" when {
    "there is a match" should {
      "proxy to the methods of Match" in {
        val m = new Klass(fixtures.reMatch)
        m.group(1) should equal(Some("bb"))
        m.groupCount should equal(1)
        m.groupNames should equal(Seq[String]())
        m.force shouldBe a [Klass]
        m.subgroups should equal(List("bb"))
        m.toString should equal(fixtures.reMatch.toString)
      }
      "proxy to the methods of the Option around Match" in {
        val m = new Klass(fixtures.reMatch)
        m.get should equal(fixtures.reMatch.get)
        m.isDefined should equal(true)
        m.isEmpty should equal(false)
      }
    }
    "there is no match" should {
      "return reasonable 'empty' values for methods of Match" in {
        val m = new Klass(None)
        m.group(1) should equal(None)
        m.groupCount should equal(0)
        m.groupNames should equal(Seq[String]())
        m.force shouldBe a [Klass]
        m.subgroups should equal(List())
        m.toString should startWith("com.swoop.scala.sugar.regex.MatchedInString")
        m.toString should endWith(":None")
      }
      "proxy to the methods of the Option around Match" in {
        val m = new Klass(None)
        intercept[NoSuchElementException] {
          m.get
        }
        m.isDefined should equal(false)
        m.isEmpty should equal(true)
      }
    }
  }

}
