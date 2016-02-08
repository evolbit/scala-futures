/**
  * Created by mordonez on 2/3/16.
  */

import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object FutureCustomExecutor {
  def execute(): Unit = {

    implicit val ec = ExecutionContext.fromExecutor{
      Executors.newFixedThreadPool(10)
//      Executors.newWorkStealingPool()
    }

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
