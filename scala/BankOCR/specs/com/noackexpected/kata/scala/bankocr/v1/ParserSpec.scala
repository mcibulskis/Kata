package com.noackexpected.kata.scala.bankocr.v1

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ParserSpec extends FlatSpec with ShouldMatchers {
  it should "convert a sequence of sequence of characters describing a single digit into a sequence containing that digit" in {
    val text = new DigitSequenceGenerator().generateDigits(List(List(0)))

    val digits = (new Parser).parse(text)

    digits should equal(List(List(Some(0))))
  }

  it should "convert a sequence of sequence of characters describing multiple digits into a sequence of digits" in {
    val text = new DigitSequenceGenerator().generateDigits(List(List(1, 2, 3, 4, 5, 6, 7, 8, 9)))

    val digits = (new Parser).parse(text)

    digits should equal(List(List(Some(1), Some(2), Some(3), Some(4), Some(5), Some(6), Some(7), Some(8), Some(9))))
  }

  it should "convert a completely unrecognizable sequence of characters into a 'None' option" in {
    val text = List(List('_', '|', '_'), List('_', '|', '_'), List('_', '|', '_'), List('_', '|', '_'))

    val digits = (new Parser).parse(text)

    digits should equal(List(List(None)))
  }

  it should "allow for fuzzy matching of digits when the character representation is slightly noisy" in {
    val text = new DigitSequenceGenerator().generateNoisyDigits(List(1), 0.17)  // should have 2/12 different characters

    val digits = new Parser().parse(text)

    digits should equal(List(List(Some(1))))
  }

  it should "allow for multiple 'rows' of digits, producing a separate sequence of digits for each 'row'" in {
    val text = new DigitSequenceGenerator().generateDigits(List(List(1, 2, 3, 4, 5), List(2,3, 4, 5, 6), List(3, 4, 5, 6, 7)))

    val digits = new Parser().parse(text)

    digits should equal(List(
      List(Some(1), Some(2), Some(3), Some(4), Some(5)),
      List(Some(2), Some(3), Some(4), Some(5), Some(6)),
      List(Some(3), Some(4), Some(5), Some(6), Some(7))))
  }
}
