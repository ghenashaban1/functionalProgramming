package forcom.chapter8

import forcom.chapter6.RNG
import forcom.chapter6.State
import forcom.chapter8.Prop.FailedCase
import forcom.chapter8.Prop.SuccessCount


opaque type Gen[+A] = State[RNG, A]

opaque type SGen[+A] = Int => Gen[A]

opaque type TestCases = Int

object TestCases {
    extension (x: TestCases)
     def toInt: Int = x
    def fromInt(x: Int): TestCases = x
}

object Gen {
opaque type Prop = (TestCases, RNG) => Result

enum Result {
  case Passed 
  case Falsified(failure: FailedCase, successes: SuccessCount) 


  def isFalsified: Boolean = { this match
  case Passed => false
  case Falsified(_, _) => true
} 
}


extension (self: Prop) 
    def &&(that: Prop): Prop = {
        (testCases, rng) => self(testCases, rng) match
            case Result.Passed => that(testCases, rng)
            case value => value
        
    }

extension (self: Prop) 
    def ||(that: Prop): Prop = {
    (testCases, rng) => self(testCases, rng) match 
        case Result.Falsified(_,_) => that(testCases, rng)
        case value => value
    }

extension [A](self: Gen[A]) 
    def unsized: SGen[A] = {
        _ => self
    }
extension [A](self: SGen[A]) 
    def map[B](f: A => B): SGen[B] = {
        n => self(n).map(f)
    }
extension [A](self: SGen[A]) 
    def flatMap[B](f: A => SGen[B]): SGen[B] = {
        n => self(n).flatMap(a => f(a)(n))
    }
extension [A](self: Gen[A]) 
    def list: SGen[List[A]] = {
 n => self.listOfN(n)
}

extension [A](self: Gen[A]) 
    def nonEmptyList: SGen[List[A]] = {
   n => self.listOfN(n.max(1))
}

}


def choose(start: Int, stopExclusive: Int): Gen[Int] = {
 State(RNG.nonNegativeInt).map(n => start + n % (stopExclusive - start)) 
}

def unit[A](a: => A): Gen[A] = {
 State.unit(a)
}

def boolean: Gen[Boolean] = {
    State(RNG.boolean)
}


object Prop {
 opaque type FailedCase = String
 opaque type SuccessCount = Int


 trait Prop {
 def check: Either[(FailedCase, SuccessCount), SuccessCount]
}}


extension [A](self: Gen[A]) 
  def flatMap[B](f: A => Gen[B]): Gen[B] = {
    State.flatMap(self)(f)
}

extension [A](self: Gen[A]) {
 def listOfN(n: Int): Gen[List[A]] = {
 State.sequence(List.fill(n)(self))
}
 def listOfN(size: Gen[Int]): Gen[List[A]] = {
   size.flatMap(listOfN)
}
}

// how does this combine the two? 
def union[A](g1: Gen[A], g2: Gen[A]): Gen[A] = {
    boolean.flatMap(x => if x then g1 else g2)
}
