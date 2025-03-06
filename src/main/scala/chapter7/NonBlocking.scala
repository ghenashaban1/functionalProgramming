package forcom.chapter7

import java.util.concurrent.ExecutorService
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicReference
import java.util.concurrent.Callable


opaque type Future[+A] = (A => Unit) => Unit
opaque type Par[+A] = ExecutorService => Future[A]

extension [A](pa: Par[A]) 
    def run(es: ExecutorService): A = {

    val ref = new AtomicReference[A]
    val latch = new CountDownLatch(1)
    pa(es) { a => ref.set(a); latch.countDown }
    latch.await
    ref.get
}

def unit[A](a: A): Par[A] = {
    es => cb => cb(a)
}

def fork[A](a: => Par[A]): Par[A] = {
    es => cb => eval(es)(a(es)(cb)) }

def eval(es: ExecutorService)(r: => Unit): Unit = {
    es.submit(new Callable[Unit] { def call = r })
}

def choice[A](cond: Par[Boolean])(t: Par[A], f: Par[A]): Par[A] = {
    es => 
        if cond.run(es) then t(es) else f(es)
}

def choiceN[A](n: Par[Int])(choices: List[Par[A]]): Par[A] = {
    es => 
        val index: Int = n.run(es) % choices.size
         choices(index).run(es)
        ???
}

    


  
