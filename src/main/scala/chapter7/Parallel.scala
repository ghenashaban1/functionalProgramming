package forcom.chapter7

import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import java.util.concurrent.Callable

opaque type Par[A] = ExecutorService => Future[A]   

object Par {
def unit[A](a: A): Par[A] = es => unitFuture(a)

case class unitFuture[A](get: A) extends Future[A] {
    def isDone = true
    def get(timeout: Long, units: TimeUnit) = get
    def isCancelled = false
    def cancel(evenIfRunning: Boolean): Boolean = false

}

extension [A](pa: Par[A])
  def map2[B, C](pb: Par[B])(f: (A, B) => C): Par[C] = {
    (es: ExecutorService) => {
        val futureA = pa(es)
        val futureB = pb(es)
        unitFuture(f(futureA.get, futureB.get))

    }
  }

def fork[A](pa: => Par[A]): Par[A] =
    es => es.submit(new Callable[A] {
    def call = pa(es).get
})

def asyncF[A, B](f: A => B): A => Par[B] = {
    a => lazyUnit(f(a))
}

def sortPar(parList: Par[List[Int]]): Par[List[Int]] = {
   parList.map(a => a.sorted)
}

def lazyUnit[A](a: => A): Par[A] = fork(unit(a))

extension [A](pa: Par[A])
    def map[B](f: A => B): Par[B] = pa.map2(unit(()))((a,_)=> f(a))

def parMap[A, B](ps: List[A])(f: A => B): Par[List[B]] = {
   fork{
    val fbs: List[Par[B]] = ps.map(asyncF(f))
    sequence(fbs)
   }
}

def sequence[A](ps: List[Par[A]]): Par[List[A]] = {
    ps.foldRight(unit(List.empty[A]))((pa, acc) => pa.map2(acc)(_ :: _))
}

//why not use parMap
def simpleParFilter[A](as: List[A])(f: A => Boolean): Par[List[A]] = {
    lazyUnit(as.filter(f))
}

def parFilter[A](as: List[A])(f: A => Boolean): Par[List[A]] = {
fork {
    val pars: List[Par[List[A]]] = {
         as.map(asyncF(a =>
         if f(a) then List(a) else Nil)) 
    }
    sequence(pars).map(_.flatten)
}
}

// is this instead of the fork ?
def delay[A](fa: => Par[A]): Par[A] =
es => fa(es)

// extension [A](pa: Par[A]) 
//   def run(s: ExecutorService): Future[A] = pa(s)
 }