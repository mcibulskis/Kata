package com.noackexpected.kata.scala.fizzbuzz.v1

import org.scalatest.matchers.ShouldMatchers

import org.scalatest.FlatSpec

class FizzBuzzSpec extends FlatSpec with ShouldMatchers {
  it should "generate a list of numbers as strings from 1 to 100" in {
    val results = (new FizzBuzz).generate

    results.size should equal(100)
    results(0) should equal("1")
    results(1) should equal("2")
    results(3) should equal("4")
    results(97) should equal("98")
  }

  it should "replace numbers divisible by three with 'Fizz'" in {
    val results = (new FizzBuzz).generate

    results(2) should equal("Fizz")
    results(5) should equal("Fizz")
    results(8) should equal("Fizz")
    results(32) should equal("Fizz")
  }

  it should "replace numbers divisible by five with 'Buzz'" in {
    val results = (new FizzBuzz).generate

    results(4) should equal("Buzz")
    results(9) should equal("Buzz")
    results(24) should equal("Buzz")
    results(54) should equal("Buzz")
  }

  it should "replace numbers divisible by both three and five by 'FizzBuzz'" in {
    val results = (new FizzBuzz).generate

    results(14) should equal("FizzBuzz")
    results(29) should equal("FizzBuzz")
    results(89) should equal("FizzBuzz")
  }

  it should "replace numbers with a 3 in them with 'Fizz'" in {
    val results = (new FizzBuzz).generate

    results(22) should equal("Fizz")
    results(82) should equal("Fizz")
  }

  it should "replace numbers with a 5 in them with 'Buzz'" in {
    val results = (new FizzBuzz).generate

    results(51) should equal("Buzz")
    results(58) should equal("Buzz")
  }
}
