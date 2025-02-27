package person

case class Person(name: String, isMale: Boolean, children: Person*)

val lara = Person("Lara", false)
val bob = Person("Bob", true)
val julie = Person("Julie", false, lara, bob)
val persons = List(lara, bob, julie)

// output should be List((Julie,Lara), (Julie,Bob))

// mothers and daughters

val mothersAndDaughters = {
    for {
        people <- persons
        theName = people.name
        kids <- people.children
        theKid = kids.name
        if (!people.isMale)
    } yield (theName, theKid)
}