// package forcom.chapter5

// enum LazyList[+A] {
//   case Empty
//   case Cons(h: () => A, t: () => LazyList[A])



//   def cons[A](hd: => A, tl: => LazyList[A]): LazyList[A] = {
//     lazy val head = hd
//     lazy val tail = tl
//     Cons(() => head, () => tail)
//   }

//   def empty[A]: LazyList[A] = Empty


//   def foldRight[A,B](instance: LazyList[A])(acc: => B)(f: (A, => B) => B): B =
//     instance match
//       case Cons(h, t) => f(h(), foldRight(t())(acc)(f))
//       case _          => acc

//   def forAll(p: A => Boolean): Boolean = {
//     foldRight(true)((a, b) => p(a) && b)
//   }

//   def takeWhile(p: A => Boolean): LazyList[A] = {
//     foldRight(empty)((a, b) => if p(a) then cons(a, b) else empty)
//   }

//   def headOption: Option[A] = {
//     foldRight(None)((a, _) => Some(a))
//   }

//   def map[B](f: A => B): LazyList[B] = {
//     foldRight(empty[B])((a, acc) => cons(f(a), acc))
//   }

//   def filter(p: A => Boolean): LazyList[A] = {
//     foldRight(empty[A])((a, acc) => if p(a) then cons(a, acc) else acc)
//   }

//   def continually[A](a: A): LazyList[A] = {
//     cons(a, continually(a))
//   }

//   def from(n: Int): LazyList[Int] = {
//     cons(n, from(n + 1))
//   }

//   def fib: LazyList[Int] = {
//     def fibHelper(current: Int, next: Int): LazyList[Int] = {
//       cons(next, fibHelper(current, next))
//     }
//     fibHelper(0, 1)
//   }

//   def unfold[A, S](state: S)(f: S => Option[(A, S)]): LazyList[A] = {
//     f(state) match {
//       case Some(a, s) => cons(a, unfold(s)(f))
//       case None       => Empty
//     }
//   }

//   def from1(n: Int): LazyList[Int] = {
//     unfold(n)(n => Some(n, n + 1))

//   }

//   def continually1[A](a: A): LazyList[A] = {
//     unfold(())(_ => Some(a, ()))
//   }


//   val ones: LazyList[Int] = {
//     unfold(())(_ => Some(1, ()))
//   }

//   def tails: LazyList[LazyList[A]] = {
//     unfold(this):
//       case Empty         => None
//       case Cons(h, tail) => Some((Cons(h, tail), tail()))

//   }

// }

// object LazyList {
//   def cons[A](hd: => A, tl: => LazyList[A]): LazyList[A] = {
//     LazyList.Cons(() => hd, () => tl)
//   }

//   def empty[A]: LazyList[A] = LazyList.Empty
// }
