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
    def unapply(person: Person): Option[(String, Int)] = Some(person.name, person.age) //(String Int) is a tuple of name, age
  }

  val bob = new Person("Bob", age = 25)
  val greeting = bob match
    case Person(n, a) => s"Hi, my name is $n and I am $a yo"

  println(greeting)
}
