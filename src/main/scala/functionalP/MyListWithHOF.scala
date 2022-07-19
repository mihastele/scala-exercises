package main.scala.functionalP

import scala.runtime.Nothing$

object MyListWithHOF extends App {


  abstract class MyList[+A] {
    /*
    *  head - first element
       tail - remainder of the list
       isEmpty
       add(int)
       toString
    * */

    def head: A

    def tail: MyList[A]

    def isEmpty: Boolean

    def add[B >: A](element: B): MyList[B]

    def map[B](transformer: (A => B)): MyList[B]

    def filter(predicate: (A => Boolean)): MyList[A]

    def ++[B >: A](list: MyList[B]): MyList[B]

    def flatMap[B](myTransformer: (A => MyList[B])): MyList[B]

    // hofs
    def foreach(f: A => Unit): Unit

    def sort(compare: (A, A) => Int): MyList[A]

    def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]

    def fold[B](start: B)(operator: (B, A) => B): B

    def printElements: String

    override def toString: String = "[" + printElements + "]"

  }

  case object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException

    def tail: MyList[Nothing] = throw new NoSuchElementException

    def isEmpty: Boolean = true

    def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

    def map[B](transformer: (Nothing => B)): MyList[B] = Empty

    def filter(predicate: (Nothing => Boolean)): MyList[Nothing] = Empty

    def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

    def flatMap[B](myTransformer: (Nothing => MyList[B])): MyList[B] = Empty

    // hofs
    def foreach(f: Nothing => Unit): Unit = ()

    def sort(compare: (Nothing, Nothing) => Int) = Empty

    def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
      if (!list.isEmpty) throw new RuntimeException(("Lists do not have the same length"))
      else Empty

    // reduce
    def fold[B](start: B)(operator: (B, Nothing) => B): B = start


    override def printElements: String = ""
  }

  // compose 2 elements
  case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h

    def tail: MyList[A] = t

    def isEmpty: Boolean = false

    def add[B >: A](element: B): MyList[B] = new Cons(element, this)

    def map[B](transformer: (A => B)): MyList[B] =
      new Cons(transformer(h), t.map(transformer))


    def filter(predicate: (A => Boolean)): MyList[A] =
      if (predicate(h)) new Cons(h, t.filter(predicate))
      else t.filter(predicate)


    def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

    def flatMap[B](myTransformer: (A => MyList[B])): MyList[B] =
      myTransformer(h) ++ t.flatMap(myTransformer)

    // hofs
    def foreach(f: A => Unit): Unit = {
      f(h)
      t.foreach(f)
    }

    def sort(compare: (A, A) => Int): MyList[A] = {
      def insert(x: A, sortedList: MyList[A]): MyList[A] =
        if (sortedList.isEmpty) Cons(x, Empty)
        else if (compare(x, sortedList.head) <= 0) Cons(x, sortedList)
        else Cons(sortedList.head, insert(x, sortedList.tail))

      val sortedTail = t.sort(compare)
      insert(h, sortedTail)
    }

    def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] =
      if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
      else Cons(zip(h, list.head), t.zipWith(list.tail, zip))

    // my impl.
    /*def fold[B](start: B)(operator: (B, A) => B): B =
      if (h == Empty) start
      else t.fold(operator(start, head))(operator)*/

    /*def fold[B](start: B)(operator: (B, A) => B): B =
      val newStart = operator(start, h)
      t.fold(newStart)(operator)*/

    /*

    [1,2,3].fold(0)(+) =
    = [2,3].fold(1)(+) =
    = [3].fold(3)(+) =
    = [].fold(6)(+) =    -> Empty impl. only returns start value
    = 6

    */
    def fold[B](start: B)(operator: (B, A) => B): B =
      t.fold(operator(start, h))(operator)

    override def printElements: String =
      if (t.isEmpty) "" + h
      else h.toString() + " " + t.printElements
  }


  val list123 = new Cons(1, new Cons(4, new Cons(122, new Cons(3, Empty))))
  val clone123 = new Cons(1, new Cons(4, new Cons(122, new Cons(3, Empty))))
  val list1235 = new Cons(1, new Cons(4, new Cons(122, new Cons(44, Empty))))

  /*println(list123.flatMap(new Function1[Int, MyList[Int]] {
    override def apply(elem: Int): MyList[Int] = new Cons(elem, new Cons(elem + 1, Empty))
  }).printElements)*/

  println(list123.flatMap((a: Int) => Cons(a, Cons(a + 1, Empty))).printElements)

  println(clone123 == list123)
  println(list1235 == list123)

  list1235.foreach(x => println(x))

  println(list1235.sort((x, y) => y - x))

  println(list1235.zipWith[Int, String](clone123, _ + "---" + _))

  println(list1235.fold(0)((a, b) => a + b))

  println(list1235.fold(0)(_ + _))

  val comprehensionList = for {
    n <- list1235
    n1 <- clone123
  } yield n + "-" + n1.toString

  println(list1235)
  println(clone123)
  println(comprehensionList)
}
