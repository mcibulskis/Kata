package com.noackexpected.kata.scala.bankocr.v1

class Parser {
  def parse(text: Seq[Seq[Char]]): Seq[Int] = {
    val possibilities = mapTextToPossibleDigits(text)
    val calculatedValues = reducePossibilities(possibilities)
    calculatedValues.keys.toList.sorted.map {
      key =>
        calculatedValues.get(key).get
    }
  }

  private def reducePossibilities(possibilities: Seq[((Int, Int, Int, Int), Int)]): Map[(Int, Int), Int] = {
    groupByDigitColumnAndDigitRow(possibilities).map {
      keyAndValue =>
        val key = keyAndValue._1
        val value = keyAndValue._2
        reduceToSingleDigitPerDigitColumnAndDigitRow(key, value)
    }
  }

  private def groupByDigitColumnAndDigitRow(possibilities: Seq[((Int, Int, Int, Int), Int)]): Map[(Int, Int), Seq[((Int, Int, Int, Int), Int)]] = {
    possibilities.groupBy {
      element =>
        (element._1._1, element._1._2)
    }
  }

  private def reduceToSingleDigitPerDigitColumnAndDigitRow(digitColumnAndRow: (Int, Int), possibilities: Seq[((Int, Int, Int, Int), Int)]): ((Int, Int), Int) = {
    val remainingPossibles = possibilities.groupBy {
      element =>
        element._2
    }.filter {
      keyAndValue =>
        keyAndValue._2.size == 12
    }.map {
      element =>
        element._1
    }
    (digitColumnAndRow, remainingPossibles.head)
  }

  private def mapTextToPossibleDigits(text: Seq[Seq[Char]]): Seq[((Int, Int, Int, Int), Int)] = {
    text.view.zipWithIndex.flatMap {
      lineWithIndex =>
        mapCharactersToPossibleDigits(lineWithIndex._1, lineWithIndex._2)
    }.force
  }

  private def mapCharactersToPossibleDigits(line: Seq[Char], lineIndex: Int): Seq[((Int, Int, Int, Int), Int)] = {
    line.view.zipWithIndex.flatMap {
      charWithIndex =>
        generatePossibleDigitsForCharacterAtIndex(charWithIndex._1, charWithIndex._2, lineIndex)
    }.force
  }

  private def generatePossibleDigitsForCharacterAtIndex(currentCharacter: Char, characterIndex: Int, lineIndex: Int): Seq[((Int, Int, Int, Int), Int)] = {
    val digitRowIndex: Int = characterIndex / 3
    val positionXIndex: Int = characterIndex % 3
    val digitColumnIndex: Int = lineIndex / 4
    val positionYIndex: Int = lineIndex % 4
    val possibleDigits = (new DigitRepresentation).calculatePossibleDigits(currentCharacter, positionXIndex, positionYIndex)
    possibleDigits.map {
      digit =>
        ((digitColumnIndex, digitRowIndex, positionXIndex, positionYIndex), digit)
    }
  }
}
