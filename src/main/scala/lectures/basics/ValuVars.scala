package lectures.basics

object ValuVars extends App {

  val x: Int = 42
  println(x)

  // val-s are immutable
  // Types of val-s are optional

  val aString: String = "Hello"

  val aBool: Boolean = false;
  val char: Char = 'w'


  // variables
  // Types of var-s are optional
  var aVariable: Int = 3
  aVariable = 4 // side effects (reassigns). Code is easier to understand without side effects, but we need them.

}
