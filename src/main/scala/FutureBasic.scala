/**
  * Created by mordonez on 2/3/16.
  */

import scala.concurrent.{Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure}

object FutureBasic {
  def execute(): Unit = {

    for(x <- 0 to 10 ){

      Future{
        Utils.fibonacci(999999999)
      }.onComplete{
        case Success(fib) =>
          println(s"terminó correctamente ${Thread.currentThread().getName} $x $fib")
        case Failure(error) =>
          println(s"terminó con error $error")
      }
    }

    println("función execute() ya terminó")
  }

}
