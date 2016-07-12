package example

import scala.annotation.tailrec

object trampoline {

  sealed trait Trampoline[+A] {
    @tailrec
    final def run: A = this match {
      case More(continuation) =>
        continuation().run
      case Done(result) =>
        result
      case More(continuation) FlatMap f =>
        continuation().flatMap(f).run
      case Done(result) FlatMap f =>
        f(result).run
      case (x FlatMap g) FlatMap (f) =>
        x.flatMap((a: Any) => g(a).flatMap(f)).run
    }

    def map[B](f: A => B): Trampoline[B] =
      flatMap(a => Done(f(a)))

    def flatMap1[B](f: A => Trampoline[B]): Trampoline[B] =
      More(() => f(run))

    def flatMap[B](f: A => Trampoline[B]): Trampoline[B] = this match {
      case FlatMap(a, g) =>
        FlatMap(a, (x: Any) => g(x).flatMap(f))
      case x =>
        FlatMap(x, f)
    }
  }

  // More(() => More(() => More(() => More(() => Done(a)))))
  case class More[+A](continuation: () => Trampoline[A]) extends Trampoline[A]
  case class Done[+A](result: A) extends Trampoline[A]
  case class FlatMap[A, B](a: Trampoline[A], f: A => Trampoline[B]) extends Trampoline[B]

}

