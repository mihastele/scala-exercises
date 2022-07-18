/* Created by Miha Stele */

package playgroundOO

// import lectures.basics.{Test34, TestImport}
// import lectures.basics._ // only use _ (all) when needed

import lectures.basics.{Test34, TestImport => AnotherName}

import java.util.{Date => UtilDate}
import java.sql.{Date => SqlDate}

object packagingAndImports extends App {

  // package memebers are accessiblr by their simple name
  val writer = new Test12

  // import package (packages that are not in the same package/directory)
  val test34 = new Test34
  val test345 = new lectures.basics.Test34 // Fully qualified name

  // packages are ordered in hirerarchy seperated by dots

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  val navn = new AnotherName

  val sqlDate = new SqlDate(1010101)

  // default imports
  // java.lang - String, Object, Exception, ...
  // scala - Int, Nothing, Function, ...
  // scala.Predef - println, ???, ...
}
