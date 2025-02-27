import fabio.Fib.* 
import sorted.Sorted.sumIt
import forcom.*
import person.*

object MyProgram {

 def abs(n: Int): Int = {
  if n < 0 then -n
  else n
}

 def factorial(n: Int): Int = {
  def go(n: Int, acc: Int): Int =
  if n <= 0 then acc
  else go(n - 1, n * acc)

  go(n, 1)
}

 private def formatAbs(x: Int): String = {
  val msg = "The absolute value of %d is %d"
  msg.format(x, abs(x))
}

 private def formatFactorial(x: Int): String = {
  val msg = "The factorial value of %d is %d"
  msg.format(x, factorial(x))
}

 def formatResult(name:String, x:Int, function: Int => Int) = {
  val msg = s"The $name of %d is %d"
  msg.format(x, function(x))
}

 def printAnything[A](result: A): String = {
  s"This is good ${result}"
 }

//  @main def printPlease: Unit = {
// //  println(s"This is format ${formatAbs(-42)}")
// //  println(s"This is factorial ${factorial(3)}")
//   // println(s"This is fib ${fib(8)}")
//   val result = formatResult("abs", -4, abs)
//   //println(s"print the result ${printAnything(1)}")
//   // val isItSorted: Boolean = isSorted(Array(1,2,3), _ > _)
//   // println(flatTheList(List(List(1,2), List(3,4), List(5,6))))
//   val fullList = List(1,2,3,4,5)
//    println(findPalindromes(List("apple", "banana", "cherry", "teet")))
//    println(mothersAndDaughters)

// }
}