package main.scala.patterMatching

object BracelessSyntax {

  // if expressions
  val anIfExpression = if (2 > 3) "bigger" else "smaller"

  // java style
  val anIfExpressionv2 =
    if (2 > 3) {
      "bigger"
    } else {
      "smaller"
    }

  // compact
  val anIfExpressionv3 =
    if (2 > 3) "bigger"
    else "smaller"

  // Scala 3
  val anIfExpressionv4 =
    if 2 > 3 then
      "bigger" // higher indentation than the if par
    else
      "smaller"

  val anIfExpressionv5 =
    if 2 > 3 then
      val result = "bigger"
      result
    else
      val result = "smaller"
      result

  val anIfExpressionv6 = if 2 > 3 then "bigger" else "smaller"

  // for comprehensions in Scala 3 (identation version)
  val aForComprehension = for
    n <- List(1, 2, 3)
    s <- List("black", "white")
  yield s"$n $s"

  // indetation works for method definitons, pattern matching, classes, traits, object, enums, ...
  // make sure you don't mix one way with the other during coding to not confuse the team

  class Animal: // in ident version we need a colon :
    def eat(): Unit =
      println("I'm eating")
    end eat
  end Animal // you can add end definiton anywhere in identation mode

  val aSpecialAnimal = new Animal :
    override def eat(): Unit = println("I'm special")

  // don't mix spaces and tabs in indetation
  def main(args: Array[String]): Unit = {

  }

}
