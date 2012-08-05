package com.noackexpected.kata.scala.bankocr.v1

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

class DigitSequenceGeneratorSpec extends FlatSpec with ShouldMatchers {
  private val impl = new DigitSequenceGenerator
  private val drs = new DigitRepresentationSpec

  //=====================
  // single digit generation
  //
  behavior of "Single digit generation"

  it should "generate an Seq of Seq of characters that looks like LED digits for 0" in {
    val text = impl.generateDigits(List(List(0)))

    text should equal(drs.digit0)
  }

  it should "generate an Seq of Seq of characters that looks like LED digits for 1" in {
    val text = impl.generateDigits(List(List(1)))

    text should equal(drs.digit1)
  }

  it should "generate an Seq of Seq of characters that looks like LED digits for 2" in {
    val text = impl.generateDigits(List(List(2)))

    text should equal(drs.digit2)
  }

  it should "generate an Seq of Seq of characters that looks like LED digits for 3" in {
    val text = impl.generateDigits(List(List(3)))

    text should equal(drs.digit3)
  }

  it should "generate an Seq of Seq of characters that looks like LED digits for 4" in {
    val text = impl.generateDigits(List(List(4)))

    text should equal(drs.digit4)
  }

  it should "generate an Seq of Seq of characters that looks like LED digits for 5" in {
    val text = impl.generateDigits(List(List(5)))

    text should equal(drs.digit5)
  }

  it should "generate an Seq of Seq of characters that looks like LED digits for 6" in {
    val text = impl.generateDigits(List(List(6)))

    text should equal(drs.digit6)
  }

  it should "generate an Seq of Seq of characters that looks like LED digits for 7" in {
    val text = impl.generateDigits(List(List(7)))

    text should equal(drs.digit7)
  }

  it should "generate an Seq of Seq of characters that looks like LED digits for 8" in {
    val text = impl.generateDigits(List(List(8)))

    text should equal(drs.digit8)
  }

  it should "generate an Seq of Seq of characters that looks like LED digits for 9" in {
    val text = impl.generateDigits(List(List(9)))

    text should equal(drs.digit9)
  }

  //============================
  // multiple digit generation
  //
  behavior of "Multiple digit generation"

  it should "generate an Seq of Seq of characters that looks like LED digits from a sequence of digits" in {
    val text = impl.generateDigits(List(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)))

    text should equal(appendedDigits(List(drs.digit0, drs.digit1, drs.digit2, drs.digit3, drs.digit4, drs.digit5, drs.digit6, drs.digit7, drs.digit8, drs.digit9)))
  }

  //========================
  // noisy digits
  //
  behavior of "Generating sequences of digits with noisy character representations"

  it should "generate a noisy version of the target digit with 2 wrong characters when the noise rate is 0.17" in {
    val text = impl.generateNoisyDigits(List(0), 0.17)
    val numberOfDifferences = detectDifferences(text, drs.digit0)
    println("noisy:    " + text)
    println("expected: " + drs.digit0)
    println("differences: " + numberOfDifferences)

    numberOfDifferences should equal(2)
  }

  it should "generate a noisy version of the target digit with 4 wrong characters when the noise rate is 0.34" in {
    val text = impl.generateNoisyDigits(List(2), 0.34)
    val numberOfDifferences = detectDifferences(text, drs.digit2)
    println("noisy:    " + text)
    println("expected: " + drs.digit2)
    println("differences: " + numberOfDifferences)

    numberOfDifferences should equal(4)
  }

  it should "generate a noisy version of the sequence of digits with 3 wrong characters per digit when the noise rate is 0.25" in {
    val text = impl.generateNoisyDigits(List(1, 2, 3, 4, 5, 6, 7, 8, 9), 0.25)
    val expectedText = appendedDigits(List(drs.digit1, drs.digit2, drs.digit3, drs.digit4, drs.digit5, drs.digit6, drs.digit7, drs.digit8, drs.digit9))
    val numberOfDifferences = detectDifferences(text, expectedText)
    println("noisy:    " + text)
    println("expected: " + expectedText)
    println("differences: " + numberOfDifferences)

    numberOfDifferences should equal(3 * 9)
  }

  //=========================
  // multiple lists of digits
  //
  behavior of "Generating multiple lines of digits"

  it should "generate a longer sequence of sequences of characters if provided with multiple sequences ('rows') of digits" in {
    val text = impl.generateDigits(List(List(1, 2, 3), List(2, 3, 4), List(3, 4, 5)))
    val expectedText = appendedDigits(List(drs.digit1, drs.digit2, drs.digit3)) ++ appendedDigits(List(drs.digit2, drs.digit3, drs.digit4)) ++ appendedDigits(List(drs.digit3, drs.digit4, drs.digit5))

    text should equal(expectedText)
  }

  //=========================
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
