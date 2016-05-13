import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server.Directives._
import spray.json.DefaultJsonProtocol
import akka.http.scaladsl.model.StatusCodes._
import org.scalatest.{FlatSpec, Matchers}

trait Api extends DefaultJsonProtocol {

  val routes = {
    path("" | "abc") {
      get {
        complete {
          OK
        }
      }
    }
  }

  val routesReversed = {
    path("abc" | "") {
      get {
        complete {
          OK
        }
      }
    }
  }
}

class ASpec extends FlatSpec with ScalatestRouteTest with Matchers with Api {
  "routing" should "work OK" in {
    Get("/abc") ~> routes ~> check { //////////// This test fails
      response.status shouldBe OK
    }

    Get("/") ~> routes ~> check {
      response.status shouldBe OK
    }
  }

  "routing - reversed" should "work OK" in {
    Get("/abc") ~> routesReversed ~> check {
      response.status shouldBe OK
    }

    Get("/") ~> routesReversed ~> check {
      response.status shouldBe OK
    }
  }
}