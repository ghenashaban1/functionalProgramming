// package forcom.chapter4

@main
def run() = { 
  val name: Option[String] = Some("test")
  val surname: Option[String] = Some("bLa")
  val third = Some("three")

  val first: Option[String] = name.flatMap(theName => surname.flatMap(_ => third.map(_ => theName + surname + third)))

  val bla = for {
    theName <- name
    second <- surname
    three <- third
  } yield (theName,surname,third)

  println(bla)
  println(first)


  val name1: Either[String, Int] = Right(2)
  val surname1: Either[String, Int] = Right(5)
  val third1: Either[String, Int] = Right(3)

  val anotherWay = name1.flatMap(name => surname1.flatMap(s => third1.map(t => (name, s, t))))

  val blabla: Either[String, (Int, Int, Int)] = for {
    one <- name1
    two <- surname1
    three <- third1
  } yield (one,two,three)

  println(blabla)
  println(anotherWay)
  
}
