package com.noackexpected.kata.scala.bankocr.v1

class DigitSequenceGenerator {

  def generateDigits(digits: Seq[Int]): Seq[Seq[Char]] = {
    digits.map {
      digit =>
        constructCharacterRepresentationForDigit(digit)
    }.reduceLeft(appendDigits)
  }

  private def constructCharacterRepresentationForDigit(digit: Int): Seq[Seq[Char]] = {
    (0 until 4).map {
      rowIndex =>
        (0 until 3).map {
          columnIndex =>
            (new DigitRepresentation).calculateCharacterAtPositionForDigit(digit, columnIndex, rowIndex)
        }
    }
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
