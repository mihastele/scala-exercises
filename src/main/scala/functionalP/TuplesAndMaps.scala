package main.scala.functionalP

object TuplesAndMaps extends App {

  // tuples = finite ordered "lists"
  val aTuple = Tuple2(2, "Hello Scala")

  println(aTuple._1) // 2
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap)

  // Maps = they associate things with other things (Key value pairs) keys -> values
  val aMap: Map[String, Int] = Map()

  val phoneBook = Map(("Jim", 555), ("Daniel", 789))
  val phoneBook2 = Map(("Jim", 555), "Daniel" -> 789) // syntactic sugar

  // map ops
  println(phoneBook2.contains("Jim"))
  println(phoneBook2("Jim"))
  // println(phoneBook2("Mary")) -> crash

  val guard = Map("Jim" -> 555, "Daniel" -> 789).withDefaultValue(-1)
  println(guard("Mary"))

  val newPairing = "Mary" -> 692
  val newPhonebook = phoneBook + newPairing
  println(newPhonebook)

  // functionals on map
  //map, flatMap, filter

  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  //filterKeys
  println(phoneBook.view.filterKeys(x =>x.startsWith("J")).toMap)
  //mapValues
  println(phoneBook.view.mapValues(number => "0245-" + number * 10).toMap)

  // conversions to other Collections
  println(phoneBook.toList)
  println(List(("Daniel", 555)).toMap)

  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

}
