package main.scala.functionalP

import scala.util.Random

object Sequences extends App {
  /*
  trait Seq[+A]{
    def head: A
    def tail: Seq[A]
  */

  val aSequence = Seq(1, 2, 3, 4) // Factory method builds List
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence.apply(2)) // retrieve value at 2
  println(aSequence(2)) // retrieve value at 2
  println(aSequence ++ Seq(5, 6, 7))
  println((aSequence ++ Seq(6, 4, 2, 1, 3, 54, 1, 2)).sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 7 // or 1 until 7 with 7 excluded
  aRange.foreach(println)

  (1 to 10).foreach(x => print("Hello "))
  println()

  // lists
  /*
  sealed abstract class List[+A]
  case object Nil extends List[Nothing]
  case class ::[A](val hd: A, val tl: List[A]) extends List[A]

  // head, tail, isEmpty O(1)
  most operations O(n)
   */

  val aList = List(1, 2, 3)
  val prepend = 42 :: aList
  val prepend2 = 42 +: aList
  val append = aList :+ 21

  val apples5 = List.fill(5)("apple")
  println(apples5)

  println(aList.mkString("-|-"))

  // Array
  /*
  final class Array[T]
    extends java.io.Serializable
    with java.lang.Cloneable

  equivalent to Java Arrays
  - can be manually constructed with redefined lengths
  - can be mutated
  - are interoperable with Java's T[] arrays

  WHERE IS THE SEQUENCE??!!
  */
  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[Int](3)
  println(threeElements)
  // reference types are initialized with null, primitive types with 0
  threeElements.foreach(println)

  numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers // implicit conversion can be applied
  println(numbersSeq)

  // Vector (Immutable sequnce)
  // final class Vector[+A]
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  // vectors vs lists

  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()

      //operation
      collection.updated(r.nextInt(maxCapacity), 0)

      // return time elapsed
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns // return average time in float

  }

  // keeps refs to tails
  // takes a long time updatingan elment
  val numbersList = (1 to maxCapacity).toList

  // depth of the tree is small (Trie implementation)
  // needs to replace an entire 32-element chunk
  val nubmersVector = (1 to maxCapacity).toVector

  println(getWriteTime(numbersList))
  println(getWriteTime(nubmersVector))
}
