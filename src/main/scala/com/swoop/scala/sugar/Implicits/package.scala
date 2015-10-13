package com.swoop.scala.sugar

import com.swoop.scala.sugar.regex.MatchedInString

import scala.util.matching.Regex

package object Implicits {

  /** 
   * ap() allows arbitrary function chaining. The name comes from ap(plying) a function.
   * This is similar to one use of Ruby's Object#try (the other being taken care of by Option).
   * It is also convenient for injecting logic into a method chain, similar to Ruby's Object#tap.
   *
   * @example {{{
   * import com.swoop.scala.sugar.Implicits._
   * 5.ap(x => x + x) // res0: Int = 10
   * // res0: Int = 10
   *
   * 5.ap{x => println(s"I got \${x}"); x}.toFloat
   *  I got 5
   * res1: Float = 5.0
   * }}}     
   *
   * @tparam A The Type of x
   * @tparam x Value to be applied on by the function ap
   */
  implicit class ApOp[A](val x: A) extends AnyVal {
    /** ap(ply) a function to the class constructor's x value
     *
     *
     * @tparam B Return type of the function being applied
     * @param f Function to be applied to x
     * @return The result of type B from applying the function f to this instance's x
     */
    def ap[B](f: A => B): B = f(x)
  }

  /** 
   * The |> (pipe) operator is borrowed from F# (inspired by Unix pipes).
   * It allows function composition to become akin to method chaining.
   * x |> f |> g is equivalent to g(f(x))
   *
   * @example {{{
   * import com.swoop.scala.sugar.Implicits._
   * def f(x: Int) = x * 10
   * def g(x: Int) = x + 10
   *
   * g(f(5))
   * res0: Int = 60
   * 5 |> f |> g
   * res1: Int = 60
   * }}}
   * @tparam A The type of the constructor argument value
   * @param value The value which will be passed to the piped function as an argument
   */
  implicit class PipelineOperator[A](val value: A) extends AnyVal {
    /** 
     * compose a function using method chaining
     *
     *
     * @tparam B Return type of the function being chained
     * @param f Function to be chained and applied to this instance's value
     */
    def |>[B] (f: A => B) = f(value)
  }

  /** 
   * regex.test(str) checks for a partial match and returns a Boolean (inspired by JavaScript)
   * This is considerably simpler than !regex.findFirstIn(str).isEmpty and semantically less
   * awkward than regex.findFirstIn(str).isDefined.
   *
   * @example {{{
   * import com.swoop.scala.sugar.Implicits._
   *
   * "a+b+".r.test("xyz aabbb")
   * // res0: Boolean = true
   *
   * }}}
   *
   * @param re The regular expression to be pimped
   */
  implicit class RegexOps(val re: Regex) extends AnyVal {
    /**
     * Perform a boolean test for a regular expression in a Character Sequence
     * 
     * @param source The CharSequence to match the regular expression in this instance's re against
     * @return True or False if the expression exists in the source
     */
    def test(source: CharSequence) = re.findFirstIn(source).isDefined

    /** 
     * regex.matchIn(str) returns the first match in an object that makes optional extraction easy.
     * @param source The CharSequence to match the regular expression in this instance's re against
     * @return a [[com.swoop.scala.sugar.regex.MatchedInString]] of the first match in this instance's source
     */
    def matchIn(source: CharSequence) = {
      new MatchedInString(re.findFirstMatchIn(source))
    }
  }

  /** 
   * str.extract(regex) is sugar for regex.matchIn(str)
   *
   * @example {{{
   * import com.swoop.scala.sugar.Implicits._
   *
   * val m = "aabb".extract("a+(b+)".r)
   * // m: com.swoop.scala.sugar.regex.MatchedInString = Some(aabb)
   * 
   * m.group(1).get
   * // res1: String = bb
   * 
   * "no match here".extract("a+(b+)".r).group(1).getOrElse("nope")
   * // res2: String = nope
   * }}}
   *
   * @param source The CharSequence to match the regular expression re against
   */
  implicit class StringRegexOps(val source: CharSequence) extends AnyVal {
    /** 
     * extracts a MatchedInString from the source
     * @param re A regular expression to match against the source
     * @return A MatchedInString of the first match of the regular expression re in this instance's source
     */
    def extract(re: Regex) = re.matchIn(source)
  }

}
