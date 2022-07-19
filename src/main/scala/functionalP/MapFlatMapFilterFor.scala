package main.scala.functionalP

object MapFlatMapFilterFor extends App {

  val list = List(1, 2, 3)

  println(list)
  println(list.head)
  println(list.tail)

  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))
  println(list.filter(_ % 2 == 0))
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // print all combinations between 2 lists
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("Black", "White")

  // val lst = List()
  // println(chars.foreach( char => lst::(numbers.map( number => char + number.toChar))))
  // println(lst)

  // iterations
  val combinations = numbers.flatMap(n => chars.map(c => c + n.toString))
  println(combinations)

  val combinations2 = numbers.flatMap(n => chars.flatMap(c => colors.map(color => s"${color} ${c}${n}")))
  println(combinations2)

  //foreach
  list.foreach(println)

  // for-comprehensions (reduce chaining of flatmaps and maps)
  val forCombinations = for {
    n <- numbers
    c <- chars
    color <- colors
  } yield s"${color} ${c}${n}"

  println(forCombinations)

  val forCombinationsFiltered = for {
    n <- numbers if n % 2 == 0 // if = filter call == numbers.filter(_ % 2) == numbers.filter(n => n %2)
    c <- chars
    color <- colors
  } yield s"${color} ${c}${n}"

  // side effects comprehensions
  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map { x =>
    x * 2
  }

  /*
  1. Mylist supports comprehensions?
  2. A small collection of at most 1 elemebt - Maybe[+T]
        - map, flatMap, filter
  */


}
