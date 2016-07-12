package example.selfrecursion

import cats.free.{Free, Trampoline}
import cats.implicits._

import scala.io.StdIn.{readLine => waitForUserToPressEnter}

object TrampolineCatsExample {

  def nonTailRecursiveFunction(xs: List[Int]): Free[Function0, List[Int]] = xs match {
    case Nil =>
      Trampoline.done(Nil)
    case head :: tail =>
      Trampoline.suspend(nonTailRecursiveFunction(tail))
        .map(tailResult => head + 1 :: tailResult)
  }

  def main(args: Array[String]): Unit = {
    val upperBound = 10000

    println {
      s""" |Program that demonstrates how to avoid stack overflow
           |in a non-tail recursive function by using trampolines (from Cats).
           |
           |Press <Enter> to start calculation.
           |""".stripMargin
    }

    waitForUserToPressEnter()

    println("Calculation started...")

    val trampoline = nonTailRecursiveFunction((1 to upperBound).toList)
    val result = trampoline.run

    println(result)
  }

}
