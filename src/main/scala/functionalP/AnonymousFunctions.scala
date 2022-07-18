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

  justDoSomething()
}
