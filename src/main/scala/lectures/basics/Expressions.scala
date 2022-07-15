package lectures.basics

object Expressions extends App {

  // Imperative languages like java and python, you tell computer what doing (instructions)

  // Expressions - return value or a Type (computes as value)

  val condition = true
  val truecond = if(condition) 5 else 3 // IF Expression, because it evaluates, if instruction is in python and java because it doesn't return a value


  var i = 0
  while (i < 10) {
    println(i)
    i+=1
  }

  // FROM NOW ON NEVER USE WHILE AND FOR LOOPS AGAIN, BECAUSE THEY ARE IMPERATIVE

  // EVERYTHING IN SCALA IS AN EXPRESSION

  var avariable = 0
  val aWeirdVar = (avariable = 3)
  print(aWeirdVar) // Prints () - Unit, meaning it is a void variable because assigning a variable is a void expression
  print(avariable) // prints the assigned value



  // Code blocks
  val aCodeBlock = {
    val x = 2
    val y = x + 1
    if(y > 2) "Hello" else "Goodbye" // this is the value of the CodeBlock because it is the last line
  }



}
