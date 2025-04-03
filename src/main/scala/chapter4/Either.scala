package either


enum Either[+E, +A] {
 case Left(value: E)
 case Right(value: A)



 def map[B](f: A => B): Either[E, B] = {
        this match
            case Right(value) => Right(f(value))
            case Left(value) => Left(value)
    }

 def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = {
        this match {
            case Right(value) => f(value)
            case Left(value) => Left(value)
        }
    }

 def orElse[EE >: E,B >: A](b: => Either[EE, B]): Either[EE, B] = {
        this match {
            case Right(value) => Right(value)
            case Left(value) => b
        }
    }

 def map2[EE >: E, B, C](that: Either[EE, B])(f: (A, B) => C): Either[EE, C] = {
        val theThis: Either[E, A] = this
        val a: Either[EE, C] = this.flatMap(x => that.map(b => f(x,b)))
         for {
            a <- this // A
            b <- that // B
        } yield f(a,b)
        
    }

    // understand more the for com for options and either


    // converts list of Eithers into one Either
    def sequence[E, A](as: List[Either[E, A]]): Either[E, List[A]] = {???}
    def traverse[E, A, B](as: List[A])(f: A => Either[E, B]): Either[E, List[B]] = {???}

}




// testing 