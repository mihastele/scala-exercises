package playgroundOO

object CaseClasses extends App {
  /*
  *  equals, hashcode, toString
  * */

  case class Person(name: String, age: Int)

  ////////// case classes //////////
  // 1. class parameters are fields in case classes
  val jim = new Person("Jim", 32)

  println(jim.name)
  // Jim

  // 2. sensible toString instead of hashcode
  // println(instance) turns it into println(instance.toString) --- syntactic sugar
  println(jim.toString)
  // Person(Jim,32)

  // 3. equals and hashCode implemented Out of the box
  val jim2 = new Person("Jim", 32)

  println(jim == jim2)
  // true

  // 4. CCs have handy copy method
  val jim3 = jim.copy(age = 45)

  println(jim3)
  // Person(Jim,45)

  // 5. CCS have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23) // no need to use "new"

  // 6. CCs are serializable
  // Akka

  // 7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  /*
   Expand Mylist
  */

}
