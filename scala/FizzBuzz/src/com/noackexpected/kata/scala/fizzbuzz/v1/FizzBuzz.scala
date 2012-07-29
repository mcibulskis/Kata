package com.noackexpected.kata.scala.fizzbuzz.v1

class FizzBuzz {
  def generate(): Seq[String] = {
    (1 until 100 inclusive).map {
      current: Int => numberAsString(current)
    }
  }

  def numberAsString(number: Int): String = {
    val retval = fizz(number) + buzz(number)
    if (retval.isEmpty) number.toString else retval
  }

  def fizz(implicit number: Int): String = {
    if (shouldFizz) "Fizz" else ""
  }

  def buzz(implicit number: Int): String = {
    if (shouldBuzz) "Buzz" else ""
  }

  def shouldFizz(implicit number: Int): Boolean = {
    isDivisibleBy(number, 3) || hasDigit(number, 3)
  }

  def shouldBuzz(implicit number: Int): Boolean = {
    isDivisibleBy(number, 5) || hasDigit(number, 5)
  }

  def isDivisibleBy(number: Int, divisor: Int): Boolean = {
    number % divisor == 0
  }

  def hasDigit(number: Int, digit: Int): Boolean = {
    number.toString.contains(digit.toString)
  }
}
