package playground

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // Anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("chrunch")
  }

  println(funnyAnimal.getClass)

  class Person(val name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi, My name is $name Jimsson, how can I be in service")
  }

  jim.sayHi
}
