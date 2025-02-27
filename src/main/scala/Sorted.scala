package sorted

object Sorted {
   def isSorted[A](as: Array[A], gt: (A,A) => Boolean): Boolean = {
        def loop(n: Int): Boolean =
        if n + 1 >= as.length then true
        else if gt(as(n), as(n + 1)) then false
        else loop(n + 1)
        loop(0)
   }

   def sumIt(list: List[Int]): Int = {
    list match {
        case Nil => 0
        case head :: tail => head + sumIt(tail)
    }
   }

//    def partial1[A, B, C](a: A, f: (A, B) => C): B => C = {
//        (b: B) => f(a,b)
//    }

//    def uncurry[A, B, C](f: A => B => C): (A, B) => C = {  
//     (a, b) => f(a)(b) 
//    }

//    def compose[A, B, C](f: B => C, g: A => B): A => C = {
//     a => f(g(a))
//    }
}


