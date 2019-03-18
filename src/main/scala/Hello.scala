import io.vertx.core.json.JsonObject
import io.vertx.scala.core.Vertx
import io.vertx.scala.ext.web.Router
import io.vertx.scala.servicediscovery.types.HttpEndpoint
import io.vertx.scala.servicediscovery.{ServiceDiscovery, ServiceDiscoveryOptions}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Hello {

    val vertx = Vertx.vertx()

    def main(args: Array[String]): Unit = {

      val server = vertx.createHttpServer()
      val router = Router.router(vertx)

      val httpPort = sys.env.get ("PORT").getOrElse("8080").toInt

      // home page
      router.get("/").handler(context => {
        context
          .response()
          .putHeader("content-type", "text/html;charset=UTF-8")
          .end("Hello 🌍")
      })

      println(s" Listening on $httpPort")
      server.requestHandler(router.accept _).listen(httpPort)
    }
}
