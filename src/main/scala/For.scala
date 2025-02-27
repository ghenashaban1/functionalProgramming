package forcom

import scala.collection.immutable.Range.Exclusive

 def even(list: List[Int]): List[Int] = {
    for {
        x <- list if (x % 2) == 0
    } yield x
 }

  def square(list: List[Int]): List[Int] = {
    for {
        x <- list
    } yield x * x
 }

 def cProduct(list1: List[Int], list2: List[Char]): List[(Int, Char)] = {
    for {
        x <- list1
        y <- list2
    } yield (x,y)
 }

 def sumPairs(list1: List[Int], list2: List[Int], threshold: Int): List[(Int, Int)] = {
    for {
        x <- list1
        y <- list2
        if x + y > threshold
    } yield (x, y)
 }

  def flatTheList(list: List[List[Int]]): List[Int] = {
    for {
        outter <- list
        inner <- outter
    } yield inner
 }

  def multiTable(n: Int): List[(Int,Int,Int)] = {
    for {
        x <- 1 to n
        y <- 1 to n
    } yield (x,y, (x*y))
 }.toList

 def zipTwoLists[A,B](list1: List[A], list2: List[B]): List[(A, B)] = {
    val result = for {
        x <- list1.indices
        if x < list2.length
    } yield (list1(x) , list2(x))
    result.toList
 }

 def wordLength(strings: List[String]): List[(String, Int)] = {
    for {
        string <- strings
        value = string.length()
    } yield (string, value)
 }

 def filterByPrefix(strings: List[String], filter: String): List[String] = {
    for {
        string <- strings
        if string.startsWith(filter)
    } yield string
 }

 def possibleTriangles(list: List[Int]): List[(Int,Int,Int)] = {
    for {
        a <- list
        b <- list
        c <- list
        if a < b && b < c 
        if (a + b > c) && (b + c > a) && (a + c > b)
    } yield (a,b,c)
 }

 def uniqueChar(list: List[String]): List[String] = {
    for {
        string <- list
        if string.groupBy(identity).forall { case (_, occurrences) => occurrences.length == 1 }
        // if string.groupBy(x => x).map{(_,v) => v.size == 1}.toList.filter(x => x == 1)

    } yield string
 }

  def mergeWithConditions(list1: List[Int], list2: List[Int]): List[(Int,Int)] = {
    for {
        x <- list1
        y <- list2
        if (x > y)
    } yield (x , y)
  }

  def numDivisible(list: List[Int], divisor: List[Int]): List[Int] = {
    val bla = for {
        number <- list
        div <- divisor
        if (number%div == 0)
    } yield number
    bla.distinct
  }

  def crossFilter(list1: List[Int], list2: List[Int]): List[(Int, Int)] = {
    for {
        x <- list1
        y <- list2
        if (x % y == 0)
    } yield (x,y)
  }

  def sumSquare(list: List[Int]): Int = {
    val bla = for {
        x <- list
        if (x % 2 == 0)
    } yield x
    bla.sum
  }

  def firstNCombinations(list1: List[Int], list2: List[Int], value: Int): List[(Int,Int)] = {
    val fullList = for {
        x <- list1
        y <- list2
    } yield (x,y)

    fullList.take(value)
  }

  def stringComb(list: List[String]): List[(String, String)] = {
    for {
        word1 <- list
        word2 <- list
        if (word1 != word2)
    } yield (word1, word2)
  }

  def findPalindromes(list: List[String]): List[String] = {
    for {
        word <- list
        if (word == word.reverse)
    } yield word
  }

  