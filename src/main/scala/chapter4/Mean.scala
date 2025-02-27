// // package option

// // import option.Option.{Some, None}


// // @main
// // def run() = { 
// //     val input = List("1", "2", "3")
// //     def toInt(value: String) = value.toInt
// //     val result = Option.traverse1(input)(???)
  
// // }

// enum Option[+A]:
//   case Some(get: A)
//   case None


// object Option:
 
// //   def map[B](f: A => B): Option[B] = {
// //      this match {
// //     case None        => None
// //     case Some(a)     => Some(f(a))
// //      }
// //   }

//   def getOrElse[B>:A](default: => B): B = {
//     this match {
//         case Some(a) => a
//         case None => default
//     }
//   }

//   def flatMap[B](f: A => Option[B]): Option[B] = {
//         map(f).getOrElse(None)
//     }

// //     def orElse[B>:A](ob: => Option[B]): Option[B] = {
// //         map(Some(_)).getOrElse(ob)
// //     }

// //     // Convert Some to None if the value doesn’t satisfy f.
// //     // map and getOrElse.
// //     def filter(f: A => Boolean): Option[A] = {
// //         flatMap(a => if f(a) then Some(a) else None)
// //     }

// //     def mean(xs: Seq[Double]): Option[Double] = {
// //     if xs.isEmpty then None
// //     else Some(xs.sum / xs.length)
// // }
// //     // math.pow(x - m, 2)
// //     def variance(xs: Seq[Double]): Option[Double] = {
// //         mean(xs).flatMap(x => mean(xs.map(y => math.pow(y - x, 2))))
// //     }

//     def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] =
//         (a, b) match
//           case (Some(aa), Some(bb)) => Some(f(aa, bb))
//           case _ => None


//     // converts list of Options into one Option
//     def sequence[A](as: List[Option[A]]): Option[List[A]] = {
//          as.foldRight[Option[List[A]]](Some(Nil))((a, acc) => map2(a, acc)(_ :: _))
//     }
   
//     // example: as =  List("1", "2", "3") and function is toInt.. output So
//     def traverse1[A, B](as: List[A])(f: A => Option[B]): Option[List[B]] = {
//        as.foldRight(Some(Nil): Option[List[B]])((a, acc) =>
//         map2(f(a), acc)(_ :: _))
//     }