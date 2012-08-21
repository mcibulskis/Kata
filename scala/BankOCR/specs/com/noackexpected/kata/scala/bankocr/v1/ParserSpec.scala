package com.noackexpected.kata.scala.bankocr.v1

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ParserSpec extends FlatSpec with ShouldMatchers {
  private val impl = new Parser
  private val generator = new DigitSequenceGenerator

  //======================
  // single line of clean digits
  //
  behavior of "Parsing a single line of clean digits"

  it should "convert a sequence of sequence of characters describing a single digit into a sequence containing that digit" in {
    val text = generator.generateDigits(List(List(0)))

    val digits = impl.parse(text)

    digits should equal(List(List(Some(0))))
  }

  it should "convert a sequence of sequence of characters describing multiple digits into a sequence of digits" in {
    val text = generator.generateDigits(List(List(1, 2, 3, 4, 5, 6, 7, 8, 9)))

    val digits = impl.parse(text)

    digits should equal(List(List(Some(1), Some(2), Some(3), Some(4), Some(5), Some(6), Some(7), Some(8), Some(9))))
  }

  //==========================
  // single line of unrecognizable digits
  //
  behavior of "Parsing a single line of unrecognizable digits"

  it should "convert a completely unrecognizable sequence of characters into a 'None' option" in {
    val text = List(List('_', '|', '_'), List('_', '|', '_'), List('_', '|', '_'), List('_', '|', '_'))

    val digits = impl.parse(text)

    digits should equal(List(List(None)))
  }

  //===========================
  // single line of noisy digits
  //
  behavior of "Parsing a single line of noisy, but recognizable digits"

  it should "allow for fuzzy matching of digits when the character representation is slightly noisy" in {
    val text = generator.generateNoisyDigits(List(1), 0.17)  // should have 2/12 different characters

    val digits = impl.parse(text)

    digits should equal(List(List(Some(1))))
  }

  //===============================
  // multiple lines of digits
  //
  behavior of  "Parsing multiple lines of digits"

  it should "allow for multiple 'rows' of digits, producing a separate sequence of digits for each 'row'" in {
    val text = generator.generateDigits(List(List(1, 2, 3, 4, 5), List(2,3, 4, 5, 6), List(3, 4, 5, 6, 7)))

    val digits = impl.parse(text)

    digits should equal(List(
      List(Some(1), Some(2), Some(3), Some(4), Some(5)),
      List(Some(2), Some(3), Some(4), Some(5), Some(6)),
      List(Some(3), Some(4), Some(5), Some(6), Some(7))))
  }

  //===============================
  // return confidences with digits
  //
  behavior of "Returning confidences for each of the digits parsed"

  it should "return, for a single digit, a sequence of possible values with confidences" in {
    val text = generator.generateDigits(List(List(1)))

    val digitsWithConfidences = impl.parseWithConfidences(text)

    val confidencesFor1 = digitsWithConfidences(0)(0)
    confidencesFor1.size should equal(11)

    val orderedOptionsFor1 = confidencesFor1.map {
      optionWithConfidences =>
        optionWithConfidences._1
    }
    orderedOptionsFor1 should equal(List(Some(1), Some(7), Some(4), Some(3), Some(9), Some(0), Some(2), Some(8), Some(5), None, Some(6)))

    confidencesFor1(0)._2 should equal(1.0)   // Some(1)
    confidencesFor1(3)._2 should equal(0.75)  // Some(3)
    confidencesFor1(10)._2 should equal(0.5)  // Some(6)
  }

  it should "return confidences for each possible value for all digits, even spanning multiple rows" in {
    val text = generator.generateDigits(List(List(0, 2), List(3, 4)))

    val digitsWithConfidences = impl.parseWithConfidences(text)

    val confidencesFor0 = digitsWithConfidences(0)(0)
    val orderedOptionsFor0 = confidencesFor0.map { _._1 }
    orderedOptionsFor0 should equal(List(Some(0), Some(8), Some(9), Some(6), Some(3), Some(2), Some(5), Some(7), Some(1), Some(4), None))
    confidencesFor0.size should equal(11)
    confidencesFor0(0)._2 should equal(1.0)  // Some(0)
    confidencesFor0(7)._2 should equal(0.75) // Some(7)

    val confidencesFor2 = digitsWithConfidences(0)(1)
    val orderedOptionsFor2 = confidencesFor2.map { _._1 }
    orderedOptionsFor2 should equal(List(Some(2), Some(3), Some(8), Some(9), Some(0), Some(6), Some(5), Some(7), Some(1), Some(4), None))
    confidencesFor2.size should equal(11)
    confidencesFor2(0)._2 should equal(1.0)     // Some(2)
    confidencesFor2(6)._2 should equal(2.0/3.0) // Some(5)

    val confidencesFor3 = digitsWithConfidences(1)(0)
    val orderedOptionsFor3 = confidencesFor3.map { _._1 }
    orderedOptionsFor3 should equal(List(Some(3), Some(9), Some(2), Some(8), Some(5), Some(7), Some(1), Some(4), Some(0), Some(6), None))
    confidencesFor3.size should equal(11)
    confidencesFor3(0)._2 should equal(1.0)  // Some(3)
    confidencesFor3(8)._2 should equal(0.75) // Some(0)

    val confidencesFor4 = digitsWithConfidences(1)(1)
    val orderedOptionsFor4 = confidencesFor4.map { _._1 }
    orderedOptionsFor4 should equal(List(Some(4), Some(1), Some(9), Some(3), Some(8), Some(5), Some(7), Some(0), Some(6), Some(2), None))
    confidencesFor4.size should equal(11)
    confidencesFor4(0)._2 should equal(1.0)     // Some(4)
    confidencesFor4(8)._2 should equal(2.0/3.0) // Some(6)
  }
}
