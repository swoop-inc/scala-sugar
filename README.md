# Scala Sugar

Compared to coding in Java, coding in Scala is pretty sweet. Compared to coding in highly expressive languages such as Python and Ruby, Scala holds its own most of the time but not always. This library is a collection of useful extensions to the core Scala libraries (implicit and otherwise) that make programming is Scala even sweeter.

This project uses [semantic versioning](http://semver.org/).

## Implicits

Where possible, implicits are implemented using [value classes](http://docs.scala-lang.org/overviews/core/value-classes.html) to reduce overhead.

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

## License

`scala-sugar` is Copyright &copy; 2015 [Simeon Simeonov](https://about.me/simeonov) and [Swoop, Inc.](http://swoop.com). It is free software, and may be redistributed under the terms of the LICENSE.
