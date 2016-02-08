/**
  * Created by mordonez on 2/3/16.
  */
import scala.io.Source
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json._
import scala.util._

object FutureRecoverErrors {
  def execute(): Unit ={

    val jsonFuture1 = Future {
      blocking {
        val json = Source.fromURL("http://www.dol.gov/data.json")
        Json.parse(json.mkString) match {
          case j:JsValue => j.as[JsObject]
          case _ => Json.obj()
        }
      }
    } recoverWith  {
      case _ => Future { Json.obj() }
    }

    val jsonFuture2 = Future {
      blocking {
        val json = Source.fromURL("http://www.usda.gov/data.json")
        Json.parse(json.mkString) match {
          case j: JsValue => j.as[JsObject]
          case _ => Json.obj()
        }
      }
    } recover{
      case _ => Json.obj()
    }

    jsonFuture1
      .flatMap( j1 => jsonFuture2.map( j2 => j2 ++ j1 ))
      .onComplete{
        case Success(json) =>
          println(s"mixed json : ${Json.prettyPrint(json)}")
        case Failure(error) =>
          println(s"error => $error")
      }

    println("función execute() ya terminó")
  }
}
