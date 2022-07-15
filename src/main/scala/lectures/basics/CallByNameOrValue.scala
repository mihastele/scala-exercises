package lectures.basics

object CallByNameOrValue extends App{

  def callByValue(x: Long): Unit = {
    println("by Value " + x)
    println("by Value " + x)
  }

  // Expression is literally passed and evaluated every time
  // Useful in lazy streams
  def callByName(x: => Long): Unit = {
    println("by Name " + x)
    println("by Name " + x)
  }

  callByValue(System.nanoTime())
  callByName(System.nanoTime())


  def infinite(): Int = 1 + infinite()

  def printFirst(x: Int, y: => Int) = println(x)

  printFirst(34, infinite()) // This function survives vecuasse y is never called



}
