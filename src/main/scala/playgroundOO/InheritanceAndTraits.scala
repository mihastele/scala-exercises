package playgroundOO

object InheritanceAndTraits extends App {

  // Single class inheritance
  class Animal {
    val creatureType: String = "wild"
    val val123: String = "12345"

    def eat = println("nomnom")

    protected final def gg = "mmr++" //final prevents classes from overriding it
  }

  class Cat extends Animal {
    def chrunch = {
      eat
      println("Chrunch chrunch")
    }

    // operator overloading
    def +(c: Cat) = {
      println("gg")
    }

    def unary_+ = println("Plus")
  }

  val cat = new Cat

  cat.chrunch
  cat + cat
  +cat

  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  //Overriding
  class Dog(override val val123: String) extends Animal {
    override val creatureType = "domestic"

    override def eat = {
      super.eat
      println("chrunch chrunch")
    }
  }

  val dog = new Dog("Doge")
  dog.eat

  // Polymorphism
  val unknownAnimal: Animal = new Dog("DogeUnkown")
  unknownAnimal.eat // It'll go to the most derived class

  // overRIDING vs overLOADING
  // super

  final class MonkeyKing extends Animal
  // final classes cannot be used to extend other classes

  // seal - sealed notation only allows file to extend in the same file
  sealed class Turtle extends Animal

  class Cveka extends Turtle


}
