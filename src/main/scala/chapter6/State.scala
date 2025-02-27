package forcom.chapter6


type Rand[+A] = RNG => (A, RNG)

val int: Rand[Int] =  x  => x.nextInt

 def unit[A](a: A): Rand[A] = {
 rng => (a, rng)

 }
 def nonNegativeInt(rng: RNG): (Int, RNG) = {
        val (i, r) = rng.nextInt
        (if (i < 0) then -(i+1) else i, r)    
    }

 def map[A, B](s: Rand[A])(f: A => B): Rand[B] = {
    rng =>
    val (a, rng2) = s(rng)
    (f(a), rng2)
 }

 def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = {
    rng => 
     val (a, rng1) = ra(rng)
     val (b, rng2) = rb(rng)
     (f(a,b), rng2)

 }

 def flatMap[A, B](r: Rand[A])(f: A => Rand[B]): Rand[B] = {
     rng1 =>
     val (a, rng2) = r(rng1)
     f(a) (rng2)
 }

  def map1[A, B](s: Rand[A])(f: A => B): Rand[B] = { 
 flatMap(s)(a => unit(f(a)))
  }

  def map22[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = {
    flatMap(ra)(a => map(rb)(b => f(a, b)))
  }
  
 def nonNegativeLessThan(n: Int): Rand[Int] = {
  flatMap(nonNegativeInt) { i =>
    val mod = i % n
    if (i + (n - 1) - mod >= 0) then unit(mod) else nonNegativeLessThan(n)
  }
 }

 def both[A, B](ra: Rand[A], rb: Rand[B]): Rand[(A, B)] = {
     map2(ra, rb)((_, _))
 }

 val randIntDouble: Rand[(Int, Double)] = {
   both(int, double)
 }


val randDoubleInt: Rand[(Double, Int)] = {
  both(double, int)
}

 def nonNegativeEven: Rand[Int] = {
 map(nonNegativeInt)(i => i - (i % 2))
 }

 def double: Rand[Double] = {
 map(nonNegativeInt)(i => i / (Int.MaxValue.toDouble + 1))

}


 