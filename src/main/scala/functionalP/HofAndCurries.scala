package main.scala.functionalP

object HofAndCurries extends App {

  // val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) => ???
  // return type function Int => Int
  // (Int, fn(String => fn(Int, Boolean)))

  // map, flatMap, filter, in MyList are Higher order functions

  // function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x))

  // my learning simple exercise, no judgement please :)
  val nTimes: ((Int => Int), Int, Int) => Int = (fn, n, x) =>
    if (n > 0) fn(nTimes(fn, n - 1, x))
    else fn(x)

  println(nTimes((a: Int) => a + a, 3, 1))


  // ntimes2(f, n, x)
  // nTimes2(f, 3, x) => f(f(f(x))) => nTimes2(f, 2, f(x)) = f(f(f(x)))
  // nTimes(f, n, x) = (f(f(...(x))) = nTimes(f,n -1, f(x))
  def nTimes2(f: (Int => Int), n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes2(f, n - 1, f(x))

  println(nTimes2((a: Int) => a + a, 3, 1))

  // nTimesBetter = x => f(f(f(x)))
  // increment10 = nTimesBetter(plusOne, 10) => x=> plusOne(plusOne...(x))
  //val y = increment10(1)
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))

  val plus10 = nTimesBetter((x) => x + 1, 10)
  println(plus10(1))

  // curried functions

  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
  println(superAdder(3)(7))

  val add3 = superAdder(3) // y => 3 + y

  // functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))


  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) =
    x => y => f(x, y)

  def fromCurry(f: Int => Int => Int): (Int, Int) => Int =
    (x, y) => f(x)(y)

  // FunctionX
  def compose(f: Int => Int, g: Int => Int): Int => Int =
    x => f(g(x))

  def andThen(f: Int => Int, g: Int => Int): Int => Int =
    x => g(f(x))

  def composeGeneric[A, B, T](f: A => B, g: T => A): T => B =
    x => f(g(x))

  def andThenGeneric[A, B, T](f: B => T, g: T => A): B => A =
    x => g(f(x))

  def superAdder2: (Int => Int => Int) = toCurry(_ + _)

  def add4 = superAdder2(4)

  println(add4(17))

  val simpleAdder = fromCurry(superAdder2)
  println(simpleAdder(4, 7))

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)

  println(composed(4))
  println(ordered(4))
}
