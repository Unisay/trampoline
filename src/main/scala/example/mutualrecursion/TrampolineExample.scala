package example.mutualrecursion

import example.trampoline._

object TrampolineExample {

  def even[A](list: List[A]): Trampoline[Boolean] = list match {
    case Nil =>
      Done(true)
    case head :: tail =>
      More(() => odd(tail))
  }

  def odd[A](list: List[A]): Trampoline[Boolean] = list match {
    case Nil =>
      Done(false)
    case head :: tail =>
      More(() => even(tail))
  }

  def main(args: Array[String]) {
    println(s"Has the List(0 to 10) odd number of elements? Answer: ")
    println(odd((0 to 10).toList).run)

    println(s"Has the List(0 to 100000) even number of elements? Answer: ")
    println(even((0 to 100000).toList).run)
  }

}
