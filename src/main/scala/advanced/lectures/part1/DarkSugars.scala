package main.scala.advanced.lectures.part1

import java.awt.Composite
import scala.util.Try

object DarkSugars extends App {
  // syntax sugar #1: Methods with single param
  def singleArgument(arg: Int): String = s"$arg little ducks..."

  val description = singleArgument {
    // write complex code
    42 + 3
  }

  println(description)

  // example
  val aTryInstance = Try { // java's try {...}
    throw new RuntimeException
  }

  List(1, 2, 3).map { x =>
    x + 1
  }

  // syntax sugar #2: single abstract method pattern
  trait Action {
    def act(x: Int): Int
  }

  val anInstance: Action = new Action {
    override def act(x: Int): Int = x + 1
  }

  val aFunkyInstance: Action = (x: Int) => x + 1 // magic

  // example: Runnables - isntances of traitS/interfaces
  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("hello")
  })

  val aSweetThread = new Thread(() => println("Sweet hello"))

  abstract class AnAbstractType {
    def implemented: Int = 23

    def f(a: Int): Unit // the only unimplemented, so we can use lambda
  }

  val anAbstractInstance: AnAbstractType = (a: Int) => println("Sweet")

  // syntax sugar #3: the :: and #:: methods are special
  val prependedList = 2 :: List(3, 4)
  // 2.::(List(3,4)) -> there is no :: method on Int
  // List(3,4).::(2)
  // ?

  // scala spec: Last character decides the associativity of the method

  1 :: 2 :: 3 :: List(4, 5)
  List(4, 5).::(3).::(2).::(1)

  // :: is right associative

  class MyStream[T] {
    def -->:(value: T): MyStream[T] = this // actual implementation here
  }

  val myStream = 1 -->: 2 -->: 3 -->: new MyStream[Int]

  // ending with a colon is a right associative method

  // snytax sugar #4: multi word method naming
  class TeenGirl(name: String) {
    def `and then said`(gossip: String): Unit = println(s"$name said $gossip")
  }

  val lilly = new TeenGirl("Lilly")
  lilly `and then said` "Scala is so sweet"

  // syntax sugar #5: infix types
  class Composite[A, B]

  val composite: Composite[Int, String] = null
  val compositeInfix: Int Composite String = null

  class -->[A, B]

  val towards: Int --> String = null // Good for type programming

  // syntax sugar $6: update() is very special, much like apply
  val anArray = Array(1, 2, 3)
  anArray(2) = 7 // anArray.update(2, 7)
  // used in mutable collections
  // remember apply() AND update()!

  // syntax sugar #7: setters for mutable containers
  class Mutable {
    private var internalMember: Int = 0 // private for OO encapsulation

    def member: Int = internalMember // "Getter"

    def member_=(value: Int): Unit =
      internalMember = value
  }

  val aMutableContainer = new Mutable
  aMutableContainer.member = 42 // rewritten as aMutableContainer.member_=(42)
}
