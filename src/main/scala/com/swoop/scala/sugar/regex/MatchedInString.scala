package com.swoop.scala.sugar.regex

import scala.util.matching.Regex.Match

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

  implicit def toOption = result
}
