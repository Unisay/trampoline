package example.mutualrecursion

object StackOverflowExample {

  def even[A](list: List[A]): Boolean = list match {
    case Nil =>
      true
    case head :: tail =>
      odd(tail)
  }

  def odd[A](list: List[A]): Boolean = list match {
    case Nil =>
      false
    case head :: tail =>
      even(tail)
  }

  def main(args: Array[String]) {
    println(s"Has the List(0 to 10) odd number of elements? Answer: ")
    println(odd((0 to 10).toList))

    println(s"Has the List(0 to 100000) even number of elements? Answer: ")
    println(even((0 to 100000).toList))
  }

}
