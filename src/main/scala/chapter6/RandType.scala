// package forcom.chapter6

// opaque type State[S, +A] = S => (A, S)

// object State {
   
// extension [S, A](underlying: State[S, A]) {
//  def run(s: S): (A, S) = underlying(s)
//  def apply(f: S => (A, S)): State[S, A] = f


//  def map[B](f: A => B): State[S, B] = {flatMap(a => unit(f(a)))}

//  def map2[B, C](sb: State[S, B])(f: (A, B) => C): State[S, C] = {
//     for
//     a <- underlying
//     b <- sb
//     yield f(a, b)
// }

//  def flatMap[B](f: A => State[S,B]): State[S,B] = {
//     s => 
//         val (a, s1) = underlying(s)
//         f(a)(s1)
//  }

// }
//  def unit[S,A](a: A): State[S,A] = {
//     s => (a,s)
//  }

//  def nonNegativeInt(rng: RNG): (Int, RNG) = {
//         val (i, r) = rng.nextInt
//         (if (i < 0) then -(i+1) else i, r)    
//     }
// }

