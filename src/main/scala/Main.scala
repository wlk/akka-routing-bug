import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{FlatSpec, Matchers}
import spray.json.DefaultJsonProtocol

trait Api extends DefaultJsonProtocol {

  val routeRootThenABC = {
    path("" | "abc") {
      get {
        complete {
          OK
        }
      }
    }
  }

  val routeABCThenRoot = {
    path("abc" | "") {
      get {
        complete {
          OK
        }
      }
    }
  }

  val routeABCThenDEF = {
    path("abc" | "def") {
      get {
        complete {
          OK
        }
      }
    }
  }

  val routeDEFThenABC = {
    path("def" | "abc") {
      get {
        complete {
          OK
        }
      }
    }
  }
}

class ASpec extends FlatSpec with ScalatestRouteTest with Matchers with Api {
  """
    |routing "" | "abc"
  """.stripMargin should "work OK" in {
    Get("/abc") ~> routeRootThenABC ~> check {
      //////////// This test fails
      response.status shouldBe OK
    }

    Get("/") ~> routeRootThenABC ~> check {
      response.status shouldBe OK
    }
  }

  """
    |routing "abc" | ""
  """.stripMargin should "work OK" in {
    Get("/abc") ~> routeABCThenRoot ~> check {
      response.status shouldBe OK
    }

    Get("/") ~> routeABCThenRoot ~> check {
      response.status shouldBe OK
    }
  }

  """
    |routing "abc" | "def"
  """.stripMargin should "work OK" in {
    Get("/abc") ~> routeABCThenDEF ~> check {
      response.status shouldBe OK
    }

    Get("/def") ~> routeABCThenDEF ~> check {
      response.status shouldBe OK
    }
  }

  """
    |routing "def" | "abc"
  """.stripMargin should "work OK" in {
    Get("/abc") ~> routeDEFThenABC ~> check {
      response.status shouldBe OK
    }

    Get("/def") ~> routeDEFThenABC ~> check {
      response.status shouldBe OK
    }
  }
}