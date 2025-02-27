// package forcom.chapter5

//   import forcom.chapter5.LazyList.empty

//   enum LazyList[+A] {
//     case Empty
//     case Cons(h: () => A, t: () => LazyList[A])

//     def toList: List[A] = this match {
//     case Cons(h, tail) => h() :: tail().toList
//     case Empty => Nil
//   }

//     def take(n: Int): LazyList[A] = this match {
//      case Cons(h, tail) if (n > 1) => LazyList.cons(h(), tail().take(n - 1))
//      case Cons(h, tail) if (n == 1) => LazyList.cons(h(), empty)
//      case _ => empty
//    }

//     def drop(n: Int): LazyList[A] = this match {
//       case Cons(_, tail) if (n > 0) => tail().drop(n-1)
//       case Cons(h, t)=> LazyList.cons(h(), t()) // or case _ => this
//       case Empty => empty
//     }

//     def takeWhile(p: A => Boolean): LazyList[A] = this match {
//       case Cons(h, t) if p(h()) => LazyList.cons(h(), t().takeWhile(p))
//       case _ => empty
//     }

//   }


// object LazyList {

//  def cons[A](hd: => A, tl: => LazyList[A]): LazyList[A] = {
//    lazy val head = hd
//    lazy val tail = tl
//    Cons(() => head, () => tail)
//  }

//  def empty[A]: LazyList[A] = Empty

//  def apply[A](as: A*): LazyList[A] = if as.isEmpty then empty else cons(as.head, apply(as.tail*))


// }



