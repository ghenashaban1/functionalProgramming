// package forcom.chapter6

// trait RNG {
//     def nextInt: (Int, RNG)
// }

// case class SimpleRNG(seed: Long) extends RNG {
//     def nextInt: (Int, RNG) = {
//         val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
//         val nextRNG = SimpleRNG(newSeed)
//         val n = (newSeed >>> 16).toInt
//         (n, nextRNG)
//     }

//     def randomPair(rng: RNG): ((Int, Int), RNG) = {
//         val (i1, rng2) = rng.nextInt
//         val (i2, rng3) = rng2.nextInt
//         ((i1, i2), rng3)
//     }

//     def nonNegativeInt(rng: RNG): (Int, RNG) = {
//         val (i, r) = rng.nextInt
//         (if (i < 0) then -(i+1) else i, r)    
//     }

//     def double(rng: RNG): (Double, RNG) = {
//         val (i, r)= nonNegativeInt(rng)
//         (i / (Int.MaxValue.toDouble + 1), r)
//     }

//     def intDouble(rng: RNG): ((Int, Double), RNG) = {
//         val (i, r1) = rng.nextInt
//         val (d, r2) = double(r1)
//         ((i, d), r2)
//     }
//     def doubleInt(rng: RNG): ((Double, Int), RNG) = {
//         val ((d,i), r) = intDouble(rng)
//         ((i,d), r)
//     }
//     def double3(rng: RNG): ((Double, Double, Double), RNG) = {
//         val (d1, r)= double(rng)
//         val (d2, r2)= double(r)
//         val (d3, r3) = double(r2)
//         ((d1,d2,d3), r3)
//     }

//     def ints(count: Int)(rng: RNG): (List[Int], RNG) = {
//         if count <= 0 then (List(), rng)
//         else {
//             val (x, r1) = rng.nextInt
//             val (xs, r2) = ints(count - 1)(r1)
//             (x :: xs, r2)
//         }
        
//     }
// }