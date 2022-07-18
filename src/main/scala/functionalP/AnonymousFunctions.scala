/* Created by Miha Stele */

package functionalP

object AnonymousFunctions extends App {

  // still an OO way of making an anonymous functions
  val doubler = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 * 2
  }

  // anonymous function in functional way (LAMBDA) Lambda calculating
  val doublerFunc = (x: Int) => x * 2
  val doublerFunc1: Int => Int = x => x * 2 // we don't need (x: Int) here since it gets matched by compiler from definition

  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  val justDoSomething = () => println("Hello :)")

  justDoSomething() // when it comes to lambdas, you have to call them with parenthesis, while not required for methods

  // This style below is sometimes used, but not very loved
  val stringToInt = { (str: String) =>
    str.toInt
  }

  val niceInrementer: Int => Int = (x: Int) => x + 1
  val niceInrementer1: Int => Int = _ + 1 // syntactic sugar
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b, each underscore stands for one parameter
  // underscores need to have Type defined

  /*

  replace MyList function calls with lambdas (MyListWithCaseClasses2)
  2. rewrite adder curry as an anonymous function

  */

  val hof: (Int => (Int => Int)) = (a: Int) =>
    (b: Int) => a * 3 + b

  println(hof(4)(17))
}
