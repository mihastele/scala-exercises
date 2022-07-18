package playgroundOO

object GenericMyListExt extends App {


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

    def printElements: String

    override def toString: String = "[" + printElements + "]"

  }

  object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException

    def tail: MyList[Nothing] = throw new NoSuchElementException

    def isEmpty: Boolean = true

    def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

    override def printElements: String = ""
  }

  // compose 2 elements
  class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h

    def tail: MyList[A] = t

    def isEmpty: Boolean = false

    def add[B >: A](element: B): MyList[B] = new Cons(element, this)

    override def printElements: String =
      if (t.isEmpty) "" + h
      else h.toString() + " " + t.printElements
  }


}
