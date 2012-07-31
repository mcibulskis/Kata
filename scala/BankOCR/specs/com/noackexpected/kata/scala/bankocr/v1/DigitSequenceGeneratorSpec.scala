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

  //
  // ========================
  // noisy digits
  //

  it should "generate a noisy version of the target digit with 2 wrong characters when the noise rate is 0.17" in {
    val text = new DigitSequenceGenerator().generateNoisyDigits(List(0), 0.17)
    val numberOfDifferences = detectDifferences(text, new DigitRepresentationSpec().digit0)
    println("noisy:    " + text)
    println("expected: " + new DigitRepresentationSpec().digit0)
    println("differences: " + numberOfDifferences)

    numberOfDifferences should equal(2)
  }

  it should "generate a noisy version of the target digit with 4 wrong characters when the noise rate is 0.34" in {
    val text = new DigitSequenceGenerator().generateNoisyDigits(List(2), 0.34)
    val numberOfDifferences = detectDifferences(text, new DigitRepresentationSpec().digit2)
    println("noisy:    " + text)
    println("expected: " + new DigitRepresentationSpec().digit2)
    println("differences: " + numberOfDifferences)

    numberOfDifferences should equal(4)
  }

  it should "generate a noisy version of the sequence of digits with 3 wrong characters per digit when the noise rate is 0.25" in {
    val text = new DigitSequenceGenerator().generateNoisyDigits(List(1, 2, 3, 4, 5, 6, 7, 8, 9), 0.25)
    val drs = new DigitRepresentationSpec
    val expectedText = appendedDigits(List(drs.digit1, drs.digit2, drs.digit3, drs.digit4, drs.digit5, drs.digit6, drs.digit7, drs.digit8, drs.digit9))
    val numberOfDifferences = detectDifferences(text, expectedText)
    println("noisy:    " + text)
    println("expected: " + expectedText)
    println("differences: " + numberOfDifferences)

    numberOfDifferences should equal(3 * 9)
  }

  //
  // =========================
  // helper methods
  //

  private def detectDifferences(actual: Seq[Seq[Char]], expected: Seq[Seq[Char]]): Int = {
    actual.flatten.zip(expected.flatten).aggregate(0)((sum, element) => sum + (if (element._1 == element._2) 0 else 1), _ + _)
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
