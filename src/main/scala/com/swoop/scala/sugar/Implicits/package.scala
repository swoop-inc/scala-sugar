package com.swoop.scala.sugar

import com.swoop.scala.sugar.regex.MatchedInString

import scala.util.matching.Regex

package object Implicits {

  implicit class ApOp[A](val x: A) extends AnyVal {
    def ap[B](f: A => B): B = f(x)
  }

  implicit class PipelineOperator[A](val value: A) extends AnyVal {
    def |>[B] (f: A => B) = f(value)
  }

  implicit class RegexOps(val re: Regex) extends AnyVal {
    def test(source: CharSequence) = {
      re.findFirstIn(source) match {
        case Some(_) => true
        case _ => false
      }
    }

    def matchIn(source: CharSequence) = {
      new MatchedInString(re.findFirstMatchIn(source))
    }
  }

  implicit class StringRegexOps(val source: CharSequence) extends AnyVal {
    def extract(re: Regex) = re.matchIn(source)
  }

}
