package main.scala.patterMatching

import scala.util.Random

object PatternMatching extends App {
  // switch on steroids
  val random = new Random
  val x = random.nextInt(10)

  val description = x match
    case 1 => "The one"
    case 2 => "Double or nothing"
    case 3 => "Thrid time is the charm"
    case _ => "Something else" // _ is the wildcard that will match anything

  println(x)
  println(description)

  // use cases of pattern matching
  // 1. decompose values
  case class Person(name: String, age: Int)

  val bob = Person("Bob", 20)

  val greeting = bob match
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I can't drink in the U.S." // guard
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
    case _ => "IDK who I am"

  println(greeting)

  /*
  1. cases are matched in order
  2. what if no cases match?
  3. Type of the PM expression? unified type of all the types in all the cases
  4. Pattern matching works very well with case classes
  */

  // returns an error if matcher matches nothing
  //val i = 4
  //val greeting2 = i match
  //  case 1 => s"Hi, my name is U.S." // guard
  //  case 2 => s"Hi, my name is years old"
  /////  // case _ => "IDK who I am"

  // PM on sealed hierarchies
  sealed class Animal

  case class Dog(breed: String) extends Animal

  case class Parrot(greet: String) extends Animal

  val animal: Animal = Dog("Terra Nova")

  // You have to match all the derivations of animal when your class is sealed
  // unless you make a case class
  animal match
    case Dog(someBreed) => println(s"Matrched a dog of the $someBreed breed")

  // match everything
  val isEven = x % 2 == 0

  // This is overkill
  val isEvenByStudents = x match
    case n if n % 2 == 0 => true
    case _ => false

  // WHY?!
  val isEvenCond = if (x % 2 == 0) true else false

  val isEvenNormal = x % 2 == 0

  trait Expr

  case class Number(n: Int) extends Expr

  case class Sum(e1: Expr, e2: Expr) extends Expr

  case class Prod(e1: Expr, e2: Expr) extends Expr

  /* Exercise - simple function PM takes an EXPR in human readable form
  Sum(Number(2), Number(3)) => 2 + 3
  Sum(Number(2), Number(3), Number(4)) => 2 + 3 + 4
  Prod(Sum(Number(2), Number(1)), Number(3) => (2 + 1) * 3
  Sum(Prod(Number(2), Number(1)), Number(3) => 2 * 1 + 3
  * */

  def show(e: Expr): String = e match
    case Number(n) => s"$n"
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
    case Prod(e1, e2) => {
      def maybeShowParanthesis(exp: Expr) = exp match
        case Prod(_, _) => show(exp)
        case Number(_) => show(exp)
        case _ => "(" + show(exp) + ")"

      maybeShowParanthesis(e1) + " * " + maybeShowParanthesis(e2)
    }

  println(show(Sum(Number(2), Number(3))))
  println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
  println(show(Prod(Sum(Number(2), Number(1)), Number(3))))
  println(show(Sum(Prod(Number(2), Number(1)), Number(3))))
}
