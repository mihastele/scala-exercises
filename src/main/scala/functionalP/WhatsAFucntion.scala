/* Created by Miha Stele */

package functionalP

object WhatsAFucntion extends App {

  // DREAM: use functions as first class elements
  // problem: we come from OO world. Everything is a class

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // Function types = Function1[A, B] Function1 supports 1 parameter, Function2 supports 2,supports up to Function22
  val stringToIntConverter = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] { // ((Int, Int) => Int) -> Syntactic sugar for Function 2 or function with 2 int parameters
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  // Fnction types Function2[A, B, R] === (A, B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS

  /*
  *

  1. a function that takes 2 strings and concatenates them
  2. MyList implementations and transform MyPredicate and Transformer into function types
  3. define a function which takes and Int and returns another functions which takes an Int and Returns and Int
     - What is the type of this function
     - how to do it?

  * */

  val concatenator: ((String, String) => String) = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = return v1.concat(v2)
  }

  println(concatenator(concatenator("Marko", " "), "Novak"))


  // Higher order functions either receive function as parameter or return a function as a result
  // curried function
  val hof: (Int => (Int => Int)) = new Function1[Int, Function1[Int, Int]] {
    override def apply(v1: Int): Function1[Int, Int] = new Function[Int, Int] {
      // closure -> v1 is available in the inner function
      override def apply(v2: Int): Int = v1 * 3 + v2
    }
  }

  val adder3 = hof(4)

  println(adder3(17))

  println(hof(4)(17)) // currying -> currying means it can be called with multiple parameter list

  // This was the only way back then, by instantiating the class. Remember the OO sectionm where we were simulation functions as object
  class Action1 {
    def execute(element: Int): String = ???
  }

  // FP simulation with OO
  trait Act2[A, B] {
    def execute(element: A): B
  }


  trait MyFunction[A, B] {
    def apply(element: A): B
  }

}