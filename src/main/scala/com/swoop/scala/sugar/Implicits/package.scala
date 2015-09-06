package com.swoop.scala.sugar

package object Implicits {

  implicit class ApOp[A](val x: A) extends AnyVal {
    def ap[B](f: A => B): B = f(x)
  }

}
