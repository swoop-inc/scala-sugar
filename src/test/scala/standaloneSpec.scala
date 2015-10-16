import org.scalatest.{WordSpec, MustMatchers}

class standaloneSpec extends WordSpec with MustMatchers {

  "Standalone" must {
    "verify implicit imports outside of the package" must {
      import com.swoop.scala.sugar.Implicits._
      5.ap(x => x + x) must equal(10)
    }
  }

}
