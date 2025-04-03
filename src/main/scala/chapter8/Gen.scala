package chapter8

import forcom.chapter8.*
import forcom.chapter6.RNG


opaque type MaxSize = Int

object MaxSize {
extension (x: MaxSize) 
 def toInt: Int = x
def fromInt(x: Int): MaxSize = x
}

opaque type Prop = (MaxSize, TestCases, RNG) => Gen.Result

object Prop {
    
 def forAll[A](g: SGen[A])(f: A => Boolean): Prop =
 (max, n, rng) =>
    val casesPerSize = (n.toInt - 1) / (max.toInt + 1)
    val props: LazyList[Prop] = LazyList.from(0).take((n.toInt min max.toInt) + 1).map(i => forAll(g(i))(f))
    val prop: Prop = props.map[Prop](p => (max, n, rng) => p(max, TestCases.fromInt(casesPerSize), rng)).toList.reduce(_ && _) 
    prop(max, n, rng)
}