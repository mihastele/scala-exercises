package playgroundOO

object AbstractDataTypes extends App {

  // abstract
  abstract class Animal {
    val creatureType: String = "wild" // abstract can have non abstract members

    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType = "doggoa"

    override def eat: Unit = println("Chrunch chrunch")
  }

  // traits
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"

    override def eat: Unit = println("Nomnom")

    def eat(animal: Animal): Unit = println(s"I'm a croc and I am eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile

  // TRAITS DO NOT HAVE CONSTRUCTOR PARAMETERS
  // You can have multiple traits, but only one class inherited
  // traits are behaviors


  // scala.Any is the mother of all Classes, all classes extend it
  // Scala.Null -> all non primitive objects can have null
  // Scala.Nothing can replace anything

  croc.eat(dog)
}
