package playgroundOO

object Exceptions extends App{

  // throws exception
  // val x: String = null
  // println(x.length)

  // 1. throwing (and catching, later...) Exceptions
  // You can assign NullPointerException to a String type because both inherit Nothing type
  //val aWeirdValue: String = throw new NullPointerException
  // throw new Error("Goodbye")

  // throwable classes extend the Throwable class.
  // Exceptions and Error are the major throwable subtypes

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  try {
    // code that might throw
    getInt(true)
  } catch  {
    case e: RuntimeException => println("caught a Runtime exception")
  } finally {
    // optional, get's executed no matter what
    // does not influence return type, while try and catch do...
    println("Cleaning up whenever there's an Exception or not")
  }

  def getInt2(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  try {
    // code that might throw
    getInt2(true)
  } catch  {
    case e: NullPointerException => println("caught a Runtime exception") // if no default exception, then then we don't catch the exception (finally still executes though)
    case e: Exception => println("Exception caught")
  } finally {
    println("Cleaning up whenever there's an Exception or not")
  }

  val potentialFail = try {
    // code that might throw
    getInt2(false)
  } catch  {
    case e: NullPointerException => println("caught a Runtime exception") // if no default exception, then then we don't catch the exception (finally still executes though)
  } finally {
    println("Cleaning up whenever there's an Exception or not")
  }

  // 3. define your own exception

  class MyException extends Exception
  val exception = new MyException

  // throw exception

  // OOM error
  // val array = Array.ofDim(Int.MaxValue)

  // SO error
  // def infinite: Int = 1 + infinite
  // val noLimint = infinite


  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalcException extends RuntimeException



  object PocketCalc {
    def add(x: Int, y: Int) = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }
    def subtract(x: Int, y: Int) = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }
    def multiply(x: Int, y: Int) = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }
    def divide(x: Int, y: Int) = {
      if (y == 0 ) throw new MathCalcException
      else x / y
    }
  }

  println(potentialFail)
}
