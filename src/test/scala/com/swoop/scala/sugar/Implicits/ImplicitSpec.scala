package com.swoop.scala.sugar.Implicits

import org.scalatest.{WordSpec, MustMatchers}

class ImplicitSpec extends WordSpec with MustMatchers {

  "Implicit" must {
    "expose implicit #ap" must {
      "allow a function to be chained" in {
        5.ap(x => x + x) must equal(10)
      }
    }
  }

}
