// package lists

// def removeFist(list: List[Int]): List[Int] = {
//     list match {
//         case Nil => sys.error("error")
//         case _ :: tail => tail
//     }
// }

// def setHead(a: Int, list: List[Int]): List[Int] = {
//     list match {
//         case Nil => sys.error("error")
//         case _ :: tail => a :: tail
//     }
// }

// def length[A](as: List[A]): Int = {
//   foldRight(as, 0, (_, acc) => acc + 1)
// }

// foldLeft[A, B](as: List[A], acc: B, f: (B, A) => B): B = {
//     as match {
//         case Nil => acc
//         case h :: t => foldLeft(t, acc + h , f)
//     }
// }

// // Write sum, product, and a function to compute the length of a list using
// // foldLeft.

// def plusOne(list: List[Int]): List[Int] = list.map(x => x +1)

// def plusOnewithFold(list: List[Int]): List[Int] = foldRight()
