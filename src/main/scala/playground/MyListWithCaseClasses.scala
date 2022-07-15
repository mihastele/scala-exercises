package playground


object MyListWithCaseClasses extends App{


  trait MyPredicate[-A] {
    def test(elem: A): Boolean
  }

  trait MyTransformer[-A, B] {
    def transform(elem: A): B
  }


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

    def map[B](transformer: MyTransformer[A, B]): MyList[B]
    def filter(predicate: MyPredicate[A]): MyList[A]

    def ++[B >: A](list: MyList[B]): MyList[B]
    def flatMap[B](myTransformer: MyTransformer[A, MyList[B]]): MyList[B]




    def printElements: String
    override def toString: String = "[" + printElements + "]"

  }

  case object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

    def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
    def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

    def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
    def flatMap[B](myTransformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty



    override def printElements: String = ""
  }

  // compose 2 elements
  case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](element: B): MyList[B] = new Cons(element, this)

    def map[B](transformer: MyTransformer[A, B]): MyList[B] =
      new Cons(transformer.transform(h), t.map(transformer))


    def filter(predicate: MyPredicate[A]): MyList[A] =
      if (predicate.test(h)) new Cons (h, t.filter(predicate))
      else t.filter(predicate)


    def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

    def flatMap[B](myTransformer: MyTransformer[A, MyList[B]]): MyList[B] =
      myTransformer.transform(h) ++ t.flatMap(myTransformer)

    override def printElements: String =
      if(t.isEmpty) "" + h
      else h.toString() + " " + t.printElements
  }


  val list123 = new Cons(1, new Cons(4, new Cons( 122, new Cons (3, Empty))))
  val clone123 = new Cons(1, new Cons(4, new Cons( 122, new Cons (3, Empty))))
  val list1235 = new Cons(1, new Cons(4, new Cons( 122, new Cons (44, Empty))))

  println(list123.flatMap( new MyTransformer[Int, MyList[Int]] {
    override def transform(elem: Int):  MyList[Int] = new Cons(elem, new Cons (elem + 1, Empty))
  }).printElements)

  println(clone123 == list123)
  println(list1235 == list123)
}
