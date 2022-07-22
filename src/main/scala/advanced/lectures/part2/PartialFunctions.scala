package main.scala.advanced.lectures.part2

object PartialFunctions extends App {
  val afunction = (x: Int) => x + 1 // Function[Int, Int] === Int => Int

  val aFussyFunction = (x: Int) =>
    if (x == 1) 43
    else if (x == 2) 56
    else if (x == 5) 999
    else throw new FunctionNotApplicableException

  class FunctionNotApplicableException extends RuntimeException

  val aNicerFussyFun = (x: Int) => x match {
    case 1 => 43
    case 2 => 56
    case 5 => 999
  }

  // {1, 2, 5} => Int, Partial function support only part of Type

  val aPartialFn: PartialFunction[Int, Int] = {
    case 1 => 43
    case 2 => 56
    case 5 => 999
  } // partial function value

  println(aPartialFn(2))
  // println(aPartialFn(57243))
  // partial functions are base on pattern matching

  // PF utlities
  println(aPartialFn.isDefinedAt(57))

  // lifted to full functions returning Options
  val lifted = aPartialFn.lift // Int => Option(Int)
  println(lifted(2))
  println(lifted(98))

  val pfChain = aPartialFn.orElse[Int, Int] {
    case 45 => 67
  }

  println(pfChain(2))
  println(pfChain(45))

  // PF extend normal functions
  val aFunction: Int => Int = {
    case 1 => 99
  }

  // HOF accept partial functions as well
  val aMappedList = List(1, 2, 3).map {
    case 1 => 43
    case 2 => 78
    case 3 => 1000
  }

  println(aMappedList)

  /*
  Note: PF can only have 1 parameter type
  */

  /* Exercises */

  val aManualFussyFunction = new PartialFunction[Int, Int] {
    override def apply(v1: Int): Int = v1 match
      case 1 => 43
      case 2 => 65
      case 5 => 999

    override def isDefinedAt(x: Int): Boolean =
      x == 1 || x == 2 || x == 5
  }

  val chatBot: PartialFunction[String, String] = {
    case "hello" => "Hi, my name is HAL9000"
    case "goodbye" => "Once you start talking to me, there is not return, human"
    case "call mom" => "Unable to find your phone without your credit card"
  }

  //scala.io.Source.stdin.getLines().foreach(line => println("chatbot says: " + chatBot(line)
  scala.io.Source.stdin.getLines().map(chatBot).foreach(println)
}
