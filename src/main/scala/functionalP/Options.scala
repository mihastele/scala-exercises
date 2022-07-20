package main.scala.functionalP

import scala.util.Random

object Options extends App {

  // options means the optional presence of a value, similar to Maybe type we implemented

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)
  println(noOption)

  // Options were made to solve unsafe APIs
  def unsafeMethod(): String = null

  //val result = Some(unsafeMethod()) // WRONG! Some(null) is wrong, never do this
  val result = Option(unsafeMethod()) // Some or None, no need of null checks

  println(result)

  // chained methods
  def backupMethod(): String = "A valid result"

  // WORK with unsafe APIs
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN unsafe APIS
  def betterUnsafeMethod(): Option[String] = None

  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()


  // functions on Options
  println(myFirstOption.isEmpty) // Good test if Option has a value or not
  println(myFirstOption.get) // UNSAFE, you can access a nullpointer, DO NOT USE THIS

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(_ % 2 == 0))
  println(myFirstOption.filter(_ % 3 == 0))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // Since we have map, flatMap and filter, we have for comprehensions

  /*
  Exercise
  */
  val config: Map[String, String] = Map(
    // Let's suppose these are
    // fetched from elsewhere, you're uncertain that they are present
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected"
  }

  // Companion object for Connection class
  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection. If you connect, print connect successfully
  val host = config.get("host")
  val port = config.get("port")

  /*
  // EQUIVALENT IMPERATIVE CODE

  if(h != null)
    if(p != null)
      return Connection.apply(h, p)
  */
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))
  /*
  if(c != null)
    return c.connect
  return null
  */
  val connectionStatus = connection.map(c => c.connect)
  // if (connectionStatus == null) println(None) else print (Some(connectionStatus.get))
  println(connectionStatus)
  /*
  if(status != null)
    println(status)
  */
  connectionStatus.foreach(println)

  // Shorthand solution
  config.get("host").flatMap(host => config.get("port").flatMap(port => Connection(host, port)).map(connection => connection.connect)).foreach(println)

  // for comprehension
  val forConnectionStatus = for{
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect

  forConnectionStatus.foreach(println)

}
