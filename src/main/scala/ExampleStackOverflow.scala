import scala.io.StdIn.{readLine ⇒ waitForUserToPressEnter}

object ExampleStackOverflow {

  def nonTailRecursiveFunction(xs: List[Int]): List[Int] = xs match {
    case Nil =>
      Nil
    case head :: tail =>
      head + 1 :: nonTailRecursiveFunction(tail)
  }

  def main(args: Array[String]): Unit = {
    val upperBound = 10000

    println {
      s"""
        |Program that demonstrates stack overflow on a non-tail recursive function.
        |
        |Press <Enter> to start calculation that throws java.lang.StackOverflowError.
      """.stripMargin
    }

    waitForUserToPressEnter()

    println("Calculation started...")

    val result = nonTailRecursiveFunction((1 to upperBound).toList)

    println(result)
  }

}
