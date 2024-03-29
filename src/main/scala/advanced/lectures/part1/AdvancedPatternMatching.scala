package main.scala.advanced.lectures.part1

object AdvancedPatternMatching extends App {
  val numbers = List(1)
  val description = numbers match {
    case head :: Nil => println(s"The only element is $head")
    case _ => "Hello"
  }

  /*
  constants
  wildcards
  case classes
  some special magic like above
  */

  class Person(val name: String, val age: Int) // assume for some reason you must not make is a case class

  object Person {
    def unapply(person: Person): Option[(String, Int)] =
      if (person.age < 21) None
      else Some(person.name, person.age) //(String Int) is a tuple of name, age

    def unapply(age: Int): Option[(String)] =
      Some(if (age < 21) "minor" else "major")
  }

  val bob = new Person("Bob", age = 25)
  val greeting = bob match
    case Person(n, a) => s"Hi, my name is $n and I am $a yo"

  println(greeting)

  val legalStatus = bob.age match
    case Person(status) => s"My legal status is $status"

  println(legalStatus)

  /*

  Exercise. Pattern match agains multiple conditions

  */


  object even {
    def unapply(arg: Int): Option[Boolean] =
      if (arg % 2 == 0) Some(true)
      else None
  }

  object singleDigit {
    def unapply(arg: Int): Option[Boolean] =
      if (arg > -10 && arg < 10) Some(true)
      else None
  }

  object evenShortened {
    def unapply(arg: Int): Boolean = arg % 2 == 0
  }

  object singleDigitShortened {
    def unapply(arg: Int): Boolean = arg > -10 && arg < 10
  }

  val n: Int = 45
  val mathPropertyBloat = n match {
    case x if x < 10 => "single digit"
    case x if x % 2 == 0 => "an even number"
    case _ => "no property"
  }

  val mathPropertySimplified = n match {
    case singleDigit(_) => "single digit"
    case even(_) => "an even number"
    case _ => "no property"
  }

  // pro - you can reuse this conditions in other patter matchings
  val mathPropertySimplified2 = n match {
    case singleDigitShortened() => "single digit"
    case evenShortened() => "an even number"
    case _ => "no property"
  }

  // advanced Pattern matching part 2

  // infix pattern
  case class Or[A, B](a: A, b: B)

  val either = Or(2, "Two")
  val humanDescription = either match {
    case number Or string => s"$number is written as $string"
  }

  // infix only works for 2 arguments

  // decomposing sequences
  var vararg = numbers match {
    case List(1, _*) => "start with 1"
  }

  abstract class MyList[+A] {
    def head: A = ???

    def tail: MyList[A] = ???
  }

  case object Empty extends MyList[Nothing]

  case class Cons[+A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  object MyList {
    def unapplySeq[A](list: MyList[A]): Option[Seq[A]] =
      if (list == Empty) Some(Seq.empty)
      else unapplySeq(list.tail).map(list.head +: _)
  }

  val myList: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))

  val decomposed = myList match
    case MyList(1, 2, _*) => "startin with 1,2" // Good for when you don't know how many elements you'll return in a pattern
    case _ => "something else"

  println(decomposed)

  // custom return types for unapply
  // isEmpty: Boolean, get: something

  abstract class Wrapper[T] {
    def isEmpty: Boolean

    def get: T
  }

  object PersonWrapper {
    def unapply(person: Person): Wrapper[String] = new Wrapper[String] {
      def isEmpty = false

      def get: String = person.name
    }
  }

  println(bob match {
    case PersonWrapper(n) => s"this person's name is $n"
    case _ => "An alien"
  })

}
