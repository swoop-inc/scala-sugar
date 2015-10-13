package com.swoop.scala.sugar.regex

import scala.util.matching.Regex.Match

/**
 * Wrapper class around an Option[Match] providing implicit conversions to Option and 
 * helper methods to access the underlying match's group and subgroups
 *
 * @example {{{
 * import com.swoop.scala.sugar.Implicits._
 * "a+(b+)".r.findFirstMatchIn("no match here").map(_.group(1)).getOrElse("nope")
 * }}}
 *
 * @param result an Option containing a Match
 */
class MatchedInString(val result: Option[Match]) extends AnyVal {
  def group(id: Int) = result.map(_.group(id))

  def group(id: String) = result.map(_.group(id))

  def groupCount = result match {
    case Some(m) => m.groupCount
    case None => 0
  }

  def groupNames = result match {
    case Some(m) => m.groupNames
    case None => Seq[String]()
  }

  def force = new MatchedInString(result.map(_.force))

  def subgroups = result match {
    case Some(m) => m.subgroups
    case None => List[String]()
  }

  override def toString = result match {
    case Some(m) => result.toString
    case None => s"${super.toString}:None"
  }

  /** Allow an instance of MatchedInString to be used where an Option is expected */
  implicit def toOption = result
}
