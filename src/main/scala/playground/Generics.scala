package playground

object Generics extends App{
  class MyList[+A] {
    // use type A
    def add[B >: A](element: B): MyList[B] = ???
    /*
     A = Cat
     B = Dog -> Animal (superType)
     B = Animal
    */
  }

  class MyMap[Key, Value]{

  }

  trait Cookie[A]{

  }

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object Mylist {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = Mylist.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // List[Cat] extends List[Animal] -> Covariance
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // Could we add animalList.add(new Dog) ???

  // NO = INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // Hell, NO! Contravariance
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types (subtypes of Animal)
  class Cage[A <: Animal](animal : A)
  val cage = new Cage(new Dog)

  // class Car
  // val newCage = new Cage(new Car)


}
