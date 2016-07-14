package example.selfrecursion

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import scala.io.StdIn.{readLine ⇒ waitForUserToPressEnter}

object IncrementWithAccumulator {

  def incrementElements(xs: List[Int]): List[Int] = {
    @tailrec
    def rec(l: List[Int], acc: ListBuffer[Int]): ListBuffer[Int] = {
      l match {
        case Nil =>
          acc
        case head :: tail =>
          rec(tail, acc :+ (head + 1))
      }
    }
    rec(xs, ListBuffer.empty).toList
  }

  def main(args: Array[String]): Unit = {
    val upperBound = 10000

    println {
      s"""
        |Program that demonstrates using accumulator to make function tail-recursive.
        |
        |Press <Enter> to start calculation
      """.stripMargin
    }

    waitForUserToPressEnter()

    println("Calculation started...")

    val result = incrementElements((1 to upperBound).toList)

    println(result)
  }

}
