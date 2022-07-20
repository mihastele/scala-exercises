package main.scala.patterMatching

object PatternsEverywhere extends App {

  try {

  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }

  // catches are actually matches
  /*
  try {
  //code
  } catch (e) {
    e match {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }
  */
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0 // ?!
  } yield 10 * x

  // generators are also based on pattern matching
  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples // the same way decomposed as pattern matching. This is possible because it is based on pattern matching
  } yield first * second

  // case classes, :: operators, ...

  // big idea #3
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple // a = 1, b = 2, c = 3 based on pattern matching
  println(b)
  // mutliple value definitons based on pattern matching
  // ALL THE POWER IS AVAILABLE

  val head :: tail = list
  println(head)
  println(tail)

  // big idea #4
  // partial function
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case _ => "something else"
  } // partial function literal

  val mappedEquivalent = list.map { x =>
    x match
      case v if v % 2 == 0 => v + "is even"
      case 1 => "the one"
      case _ => "something else"
  }

  println(mappedList)
}
