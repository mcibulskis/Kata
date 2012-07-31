package com.noackexpected.kata.scala.bankocr.v1

class DigitSequenceGenerator {
  private val digitRepresentation = new DigitRepresentation

  def generateDigits(digits: Seq[Int]): Seq[Seq[Char]] = {
    generateDigitRepresentation(digits, constructNoisyCharacterRepresentationForDigit(0.0))
  }

  def generateNoisyDigits(digits: Seq[Int], noiseRate: Double): Seq[Seq[Char]] = {
    generateDigitRepresentation(digits, constructNoisyCharacterRepresentationForDigit(noiseRate))
  }

  private def generateDigitRepresentation(digits: Seq[Int], generator: (Int) => Seq[Seq[Char]]): Seq[Seq[Char]] = {
    digits.map {
      digit =>
        generator(digit)
    }.reduceLeft(appendDigits)
  }

  private def constructNoisyCharacterRepresentationForDigit(noiseRate: Double)(digit: Int): Seq[Seq[Char]] = {
    val numberOfDistortions = calculateNumberOfDistortions(noiseRate)
    val positionsToDistort = selectN(numberOfDistortions, digitRepresentation.generatePositionGridAsSequence())
    digitRepresentation.generatePositionGrid().map {
      positions =>
        positions.map {
          position =>
            if (positionsToDistort.contains(position)) {
              mutateCharacterAtPositionForDigit(digit, position._1, position._2)
            } else {
              digitRepresentation.calculateCharacterAtPositionForDigit(digit, position._1, position._2)
            }
        }
    }
  }

  private def calculateNumberOfDistortions(noiseRate: Double): Int = {
    (12 * noiseRate).toInt
  }

  private def selectN[B](numberToSelect: Int, fromSequence: Seq[B]): Seq[B] = {
    var selectFrom = new scala.collection.mutable.MutableList[B]() ++ fromSequence
    (0 until numberToSelect).map {
      currentSelection =>
        val selected = selectFrom(scala.util.Random.nextInt(selectFrom.size))
        selectFrom = selectFrom diff List(selected)
        selected
    }
  }

  private def mutateCharacterAtPositionForDigit(digit: Int, columnIndex: Int, rowIndex: Int): Char = {
    val originalCharacter = digitRepresentation.calculateCharacterAtPositionForDigit(digit, columnIndex, rowIndex)
    val possibilities = alternateCharMap.get(originalCharacter).get
    possibilities(scala.util.Random.nextInt(possibilities.size))
  }

  private val alternateCharMap = Map(
    ' ' -> Seq('_', '|'),
    '|' -> Seq('_', ' '),
    '_' -> Seq(' ', '|')
  )

  private def appendDigits(digitA: Seq[Seq[Char]], digitB: Seq[Seq[Char]]): Seq[Seq[Char]] = {
    List(
      List(digitA(0), digitB(0)).flatten,
      List(digitA(1), digitB(1)).flatten,
      List(digitA(2), digitB(2)).flatten,
      List(digitA(3), digitB(3)).flatten
    )
  }
}
