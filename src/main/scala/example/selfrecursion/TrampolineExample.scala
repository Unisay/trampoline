package example.selfrecursion

import example.trampoline._

import scala.io.StdIn.{readLine => waitForUserToPressEnter}

object TrampolineExample {

  def nonTailRecursiveFunction(xs: List[Int]): Trampoline[List[Int]] = xs match {
    case Nil =>
      Done(Nil)
    case head :: tail =>
      More(() => nonTailRecursiveFunction(tail)).map(tailResult => head + 1 :: tailResult)
  }

  def main(args: Array[String]): Unit = {
    val upperBound = 10000

    println {
      s""" |Program that demonstrates how to avoid stack overflow
           |in a non-tail recursive function by using trampolines.
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
