package example.selfrecursion

import scala.annotation.tailrec
import scala.io.StdIn.{readLine ⇒ waitForUserToPressEnter}

object IncrementWithFoldLeft {

  @tailrec
  def foldLeft[A, B](xs: List[A], b: B)(f: (B, A) ⇒ B): B = xs match {
    case Nil =>
      b
    case head :: tail =>
      foldLeft(tail, f(b, head))(f)
  }

  def incrementUsingFoldLeft(xs: List[Int]): List[Int] =
    foldLeft(xs, List.empty[Int])((acc, elem) ⇒ (elem + 1) :: acc).reverse

  def main(args: Array[String]): Unit = {
    val upperBound = 100000

    println {
      s"""
        |Program that demonstrates that foldLeft is a stack-safe
        |as it takes advantage of the tail recursion optimization.
        |
        |Press <Enter> to start calculation.
      """.stripMargin
    }

    waitForUserToPressEnter()

    println("Calculation started...")

    val result = incrementUsingFoldLeft((1 to upperBound).toList)

    println(result)
  }

}
