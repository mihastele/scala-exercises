package lectures.basics

object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }


  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)

  }

  def fibonacci(n: Int): Int = {
    def fiboTailRec(i: Int, last: Int, nextToLast: Int): Int =
      if (i >= n) last
      else fiboTailRec(i + 1, last + nextToLast, last)

    if (n <= 2) 1
    else fiboTailRec(2, 1, 1)
  }
  /*
  fibonacci(4)
  fiboTailRec(2,1,1)
  fiboTailRec(3,2,1)
  fiboTailRec(4,3,2)
*/

  println(fibonacci(8))


}
