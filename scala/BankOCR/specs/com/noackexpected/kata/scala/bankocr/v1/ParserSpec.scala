package com.noackexpected.kata.scala.bankocr.v1

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ParserSpec extends FlatSpec with ShouldMatchers {
  it should "convert a sequence of sequence of characters describing a single digit into a sequence containing that digit" in {
    val text = new DigitSequenceGenerator().generateDigits(List(0))

    val digits = (new Parser).parse(text)

    digits should equal(List(0))
  }

  it should "convert a sequence of sequence of characters describing multiple digits into a sequence of digits" in {
    val text = new DigitSequenceGenerator().generateDigits(List(1, 2, 3, 4, 5, 6, 7, 8, 9))

    val digits = (new Parser).parse(text)

    digits should equal(List(1, 2, 3, 4, 5, 6, 7, 8, 9))
  }
}
