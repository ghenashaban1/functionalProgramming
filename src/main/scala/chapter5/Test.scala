package forcom.chapter5

enum LazyList[+A] {
  case Empty
  case Cons(h: () => A, t: () => LazyList[A])

  def foldRight[B](acc: => B)(f: (A, => B) => B): B =
    this match
      case Cons(h, t) => f(h(), t().foldRight(acc)(f))
      case _          => acc

  def forAll(p: A => Boolean): Boolean =
    foldRight(true)((a, b) => p(a) && b)

  def takeWhile(p: A => Boolean): LazyList[A] =
    foldRight(LazyList.empty)((a, b) => if p(a) then LazyList.cons(a, b) else LazyList.empty)

  def headOption: Option[A] =
    foldRight(None: Option[A])((a, _) => Some(a))

  def map[B](f: A => B): LazyList[B] =
    foldRight(LazyList.empty[B])((a, acc) => LazyList.cons(f(a), acc))

  def filter(p: A => Boolean): LazyList[A] =
    foldRight(LazyList.empty[A])((a, acc) => if p(a) then LazyList.cons(a, acc) else acc)
}

object LazyList {
  def cons[A](hd: => A, tl: => LazyList[A]): LazyList[A] =
    LazyList.Cons(() => hd, () => tl)

  def empty[A]: LazyList[A] = LazyList.Empty

  def continually[A](a: A): LazyList[A] =
    cons(a, continually(a))

  def continually1[A](a: A): LazyList[A] =
    unfold(())(_ => Some(a, ()))

  def unfold[A, S](state: S)(f: S => Option[(A, S)]): LazyList[A] =
    f(state) match {
      case Some(a, s) => cons(a, unfold(s)(f))
      case None       => Empty
    }
}
