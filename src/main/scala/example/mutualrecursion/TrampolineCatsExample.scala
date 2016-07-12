package example.mutualrecursion

import cats.free.Trampoline
import cats.free.Trampoline._
import cats.std.function._


object TrampolineCatsExample {

  def even[A](list: List[A]): Trampoline[Boolean] = list match {
    case Nil =>
      done(true)
    case head :: tail =>
      suspend(odd(tail))
  }

  def odd[A](list: List[A]): Trampoline[Boolean] = list match {
    case Nil =>
      done(false)
    case head :: tail =>
      suspend(even(tail))
  }

  def main(args: Array[String]) {
    println(s"Has the List(0 to 10) odd number of elements? Answer: ")
    println(odd((0 to 10).toList).run)

    println(s"Has the List(0 to 100000) even number of elements? Answer: ")
    println(even((0 to 100000).toList).run)
  }

}
