package fabio

object Fib {

 def fib(n: Int): Int = {
    // Helper function with accumulator
   
    def fibHelper(n: Int, prev: Int, current: Int): Int = {
      if (n == 0) prev
      else fibHelper(n - 1, current, prev + current)
    }

    // Start the recursion with initial values: prev = 0, current = 1
    fibHelper(n, 0, 1)
  }


    }