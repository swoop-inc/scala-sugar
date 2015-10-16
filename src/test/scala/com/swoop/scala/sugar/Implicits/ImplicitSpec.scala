package com.swoop.scala.sugar.Implicits

import com.swoop.scala.sugar.regex.MatchedInString
import org.scalatest.{WordSpec, Matchers}

class ImplicitSpec extends WordSpec with Matchers {

  "Implicit" should {
    "expose #ap" which {
      "allows a function to be chained" in {
        5.ap(x => x + x) should equal(10)
      }
    }
    "expose the |> operator" which {
      "allows the rhs function to the applied to the lhs expression" in {
        (5 |> identity) should equal(5)
      }
    }
    "include regex extensions" which {
      "expose #test" which {
        "checks whether a regex partially matches a string" in {
          "a+b+".r.test("xyz aabbb") should equal(true)
        }
        "returns false when there is no match" in {
          "a+b+c+".r.test("ab") should equal(false)
        }
      }
      "expose #matchIn" which {
        "returns a MatchedInString instance" in {
          "a+b+".r.matchIn("xyz aabbb") shouldBe a[MatchedInString]
        }
      }
    }
    "include string extensions" which {
      "expose #extract" which {
        "returns a MatchedInString instance" in {
          "aabb".extract("a+(b+)".r) shouldBe a[MatchedInString]
        }
      }
    }
  }

}
