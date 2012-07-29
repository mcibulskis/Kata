package com.noackexpected.kata.scala.bankocr.v1

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

class DigitSequenceGeneratorSpec extends FlatSpec with ShouldMatchers {
  it should "generate an Seq of Seq of characters that looks like LED digits for 0" in {
    val text = new DigitSequenceGenerator().generateDigits(List(0))

    text should equal((new DigitRepresentationSpec).digit0)
  }

  it should "generate an Seq of Seq of characters that looks like LED digits for 1" in {
    val text = new DigitSequenceGenerator().generateDigits(List(1))

    text should equal((new DigitRepresentationSpec).digit1)
  }

  it should "generate an Seq of Seq of characters that looks like LED digits for 2" in {
    val text = new DigitSequenceGenerator().generateDigits(List(2))

    text should equal((new DigitRepresentationSpec).digit2)
  }

  it should "generate an Seq of Seq of characters that looks like LED digits for 3" in {
    val text = new DigitSequenceGenerator().generateDigits(List(3))

    text should equal((new DigitRepresentationSpec).digit3)
  }

  it should "generate an Seq of Seq of characters that looks like LED digits for 4" in {
    val text = new DigitSequenceGenerator().generateDigits(List(4))

    text should equal((new DigitRepresentationSpec).digit4)
  }

  it should "generate an Seq of Seq of characters that looks like LED digits for 5" in {
    val text = new DigitSequenceGenerator().generateDigits(List(5))

    text should equal((new DigitRepresentationSpec).digit5)
  }

  it should "generate an Seq of Seq of characters that looks like LED digits for 6" in {
    val text = new DigitSequenceGenerator().generateDigits(List(6))

    text should equal((new DigitRepresentationSpec).digit6)
  }

  it should "generate an Seq of Seq of characters that looks like LED digits for 7" in {
    val text = new DigitSequenceGenerator().generateDigits(List(7))

    text should equal((new DigitRepresentationSpec).digit7)
  }

  it should "generate an Seq of Seq of characters that looks like LED digits for 8" in {
    val text = new DigitSequenceGenerator().generateDigits(List(8))

    text should equal((new DigitRepresentationSpec).digit8)
  }

  it should "generate an Seq of Seq of characters that looks like LED digits for 9" in {
    val text = new DigitSequenceGenerator().generateDigits(List(9))

    text should equal((new DigitRepresentationSpec).digit9)
  }

  it should "generate an Seq of Seq of characters that looks like LED digits from a sequence of digits" in {
    val text = new DigitSequenceGenerator().generateDigits(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))
    val drs = new DigitRepresentationSpec

    text should equal(appendedDigits(List(drs.digit0, drs.digit1, drs.digit2, drs.digit3, drs.digit4, drs.digit5, drs.digit6, drs.digit7, drs.digit8, drs.digit9)))
  }

  private def appendedDigits(digits: Seq[Seq[Seq[Char]]]): Seq[Seq[Char]] = {
    digits.reduceLeft(appendDigits)
  }

  private def appendDigits(digitA: Seq[Seq[Char]], digitB: Seq[Seq[Char]]): Seq[Seq[Char]] = {
    List(
      List(digitA(0), digitB(0)).flatten,
      List(digitA(1), digitB(1)).flatten,
      List(digitA(2), digitB(2)).flatten,
      List(digitA(3), digitB(3)).flatten
    )
  }
}
