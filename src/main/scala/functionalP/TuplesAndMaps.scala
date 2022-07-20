package main.scala.functionalP

import scala.annotation.tailrec

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
  println(phoneBook.view.filterKeys(x => x.startsWith("J")).toMap)
  //mapValues
  println(phoneBook.view.mapValues(number => "0245-" + number * 10).toMap)

  // conversions to other Collections
  println(phoneBook.toList)
  println(List(("Daniel", 555)).toMap)

  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  /*
    Exercises
  1. What would happen if I had two original entries like "jim-> 555 and "JIM" -> 900 and run toLowerCase
  2. Overly simplified social network based on maps
      Person = String
      - add person to the network
      - remove person
      - friend
      - unfriend
      - numbers friends of a given person
      - person with most friends
      - how many people have no friends (jus joined network)
      - is there a social connection between 2 people (direct or not)
  */

  val mapJimJIM = Map("JIM" -> 123, "jim" -> 221, "JiM" -> 4212)
  println(mapJimJIM.map(pair => pair._1.toLowerCase -> pair._2))
  // Observation, probably just writes the last value
  // Make sure keys don't overlap or you will lose some data


  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeFriendships(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeFriendships(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeFriendships(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))


  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1 // _1 key, _2 value

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.view.filterKeys(k => network(k).isEmpty).size

  // filter(FX).size -> count(FX)

  println(nPeopleWithNoFriends(testNet))
  println(nPeopleWithNoFriends(network))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    //bfs - breadth first search
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else if (person == target) true
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }

    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Bob"))
}
