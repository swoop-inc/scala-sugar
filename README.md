# Scala Sugar

Compared to coding in Java, coding in Scala is pretty sweet. Compared to coding in highly expressive languages such as Python and Ruby, Scala holds its own most of the time but not always. This library is a collection of useful extensions to the core Scala libraries (implicit and otherwise) that make programming in Scala even sweeter.

This project uses [semantic versioning](http://semver.org/).

## Implicits

Where possible, implicits are implemented using [value classes](http://docs.scala-lang.org/overviews/core/value-classes.html) to reduce overhead.

### Generics

```scala
import com.swoop.scala.sugar.Implicits._

// ap() allows arbitrary function chaining. The name comes from ap(plying) a function.
// This is similar to one use of Ruby's Object#try (the other being taken care of by Option).
// It is also convenient for injecting logic into a method chain, similar to Ruby's Object#tap.

5.ap(x => x + x)
// res0: Int = 10

5.ap{x => println(s"I got ${x}"); x}.toFloat
// I got 5
// res1: Float = 5.0
```

### Regular expressions

```scala
import com.swoop.scala.sugar.Implicits._

// regex.test(str) checks for a partial match and returns a Boolean (inspired by JavaScript)
// This is considerably simpler than !regex.findFirstIn(str).isEmpty and semantically less
// awkward than regex.findFirstIn(str).isDefined.

"a+b+".r.test("xyz aabbb")
// res0: Boolean = true

// regex.matchIn(str) returns the first match in an object that makes optional extraction easy.
// str.extract(regex) is sugar for regex.matchIn(str)

val m = "aabb".extract("a+(b+)".r)
// m: com.swoop.scala.sugar.regex.MatchedInString = Some(aabb)

m.group(1).get
// res1: String = bb

"no match here".extract("a+(b+)".r).group(1).getOrElse("nope")
// res2: String = nope

// Compare the readability & convenience of method chaining above with the usual approach
"a+(b+)".r.findFirstMatchIn("no match here") match {
  case Some(m) => m.group(1)
  case None => "nope"
}
```

## License

`scala-sugar` is Copyright &copy; 2015 [Simeon Simeonov](https://about.me/simeonov) and [Swoop, Inc.](http://swoop.com). It is free software, and may be redistributed under the terms of the LICENSE.
