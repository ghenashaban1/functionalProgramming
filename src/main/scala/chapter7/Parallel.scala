package forcom.chapter7


def map2[A,B,C](p1: Par[A], p2: Par[B])(f: (A,B) => C): Par[C] = ???


def sum(ints: IndexedSeq[Int]): Par[Int] = {
    if ints.size <= 1 then
    Par.unit(ints.headOption.getOrElse(0))
    else
    val (l, r) = ints.splitAt(ints.size / 2)
    Par.map2(sum(l), sum(r))(_ + _)
}

