package main.scala.patterMatching

import main.scala.functionalP.MyListWithHOF.{MyList, Cons, Empty}

object AllThePatterns extends App {
  /*
  // 1 - constants

  val x: Any = "Scala"
  val constants = x match
    case 1 => "A number"
    case "Scala" => "THE Scala"
    case true => "The truth"
    case AllThePatterns => "Singleton object"

  // 2 - match anything
  // 2.1 - wildcard
  val matchAnything = x match
    case _ => ""

  // 2.2 variable
  val matchVariable = x match
    case something => s"I've found $something"

  // 3 - tuples
  val aTuple = (1, 2)
  val matchTuple = aTuple match
    case (1, 1) =>
    case (something, 2) => s"I've found $something"


  val nesteDTuple = (1, (2, 3))
  val switchNestedTuple = nesteDTuple match
    case (_, (2, v)) => s"$v is a variable"
  // pattern matches can be nested - cool

  // 4 - case classes - contructor pattern
  // PM can be nested with case classes as well
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match
    case Empty =>
    // case Cons(h, t) => s"$h with a $t"
    case Cons(h, Cons(subhead, subtail)) => s"$h with a $subhead and $subtail"

  // 5 - list patterns
  val aStandardList = List(1, 2, 3, 42)
  val standardListMatching = aStandardList match
    case List(1, _, _, _) => "extractor for list" // extractor, this example matches list with 4 elements being the head "1"
    case List(1, _*) => // list of arbitrary length - advanced
    case 1 :: List(_) => // infix pattern
    case List(1, 2, 3) :+ 42 => // Also an infix pattern

  // 6 - type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match
    case list: List[Int] => // explicit type specifier
    case _ =>

  // 7 - name binding
  val nameBindingMatch = aList match
    case nonEmptyList@Cons(_, _) => println(nonEmptyList) // name binding for using it later
    case Cons(1, rest@Cons(2, _)) => println(rest) // name binding inside patterns

  // 8 - multi-patterns
  val multiPattern = aList match
    case Empty | Cons(0, _) => // compound pattern (mutli-pattern)

  // 9 - if guards
  val secondElementSpecial = aList match
    case Cons(_, Cons(speciaElement, _)) if speciaElement % 2 == 0 =>
   */

  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match
    case listOfStrings: List[String] => "A list of Strings"
    case listOfNumbers: List[Int] => "list of Ints"
    case _ => ""

  println(numbersMatch)
  // JVM trick question, JVM was designed for Java and backward compatibility, Java 1 did not have generics
  // Java compiler erased the generics
  // erasure problem
  /*

  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match
    case listOfStrings: List => "A list of Strings"
    case listOfNumbers: List => "list of Ints"
    case _ => ""

  */

}
