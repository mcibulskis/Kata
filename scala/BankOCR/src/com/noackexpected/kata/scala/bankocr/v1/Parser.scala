package com.noackexpected.kata.scala.bankocr.v1

class Parser {
  private val digitRepresentation = new DigitRepresentation

  def parse(text: Seq[Seq[Char]]): Seq[Seq[Option[Int]]] = {
    val possibilities = mapTextToPossibleDigits(text)
    val calculatedValues = reducePossibilities(possibilities)
    val submapMap = createSubmapsPerDigitRow(calculatedValues)
    submapMap.keys.toList.sorted.map {
      digitRow =>
        convertRowOfPossibleValuesToSequenceOfOrderedOptions(submapMap.get(digitRow).get)
    }
  }

  def parseWithConfidences(text: Seq[Seq[Char]]): Seq[Seq[Seq[(Option[Int], Double)]]] = {
    val possibilities = mapTextToPossibleDigits(text)
    val calculatedConfidences = calculateConfidences(possibilities)
    val submapMap = createSubmapsPerDigitRow(calculatedConfidences)
    submapMap.keys.toList.sorted.map {
      digitRow =>
        convertRowOfPossibleValuesToSequenceOfOrderedOptions(submapMap.get(digitRow).get)
    }
  }

  private def createSubmapsPerDigitRow[DigitPossibility](possibilities: Map[(Int, Int), DigitPossibility]): Map[Int, Map[(Int, Int), DigitPossibility]] = {
    val keyGrouping = possibilities.keys.groupBy(_._1)
    keyGrouping.keys.toList.sorted.foldLeft(Map[Int, Map[(Int, Int), DigitPossibility]]()) {
      (overallMap, digitRow) =>
        overallMap ++ Map[Int, Map[(Int, Int), DigitPossibility]](digitRow -> pickSubmapElements(keyGrouping.get(digitRow).get.toSeq, possibilities))
    }
  }

  private def pickSubmapElements[DigitPossibility](digitPositions: Seq[(Int, Int)], possibilities: Map[(Int, Int), DigitPossibility]): Map[(Int, Int), DigitPossibility] = {
    digitPositions.sorted.foldLeft(Map[(Int, Int), DigitPossibility]()) {
      (overallMap, digitPosition) =>
        overallMap ++ Map[(Int, Int), DigitPossibility](digitPosition -> possibilities.get(digitPosition).get)
    }
  }

  private def convertRowOfPossibleValuesToSequenceOfOrderedOptions[DigitPossibility](possibilities: Map[(Int, Int), DigitPossibility]): Seq[DigitPossibility] = {
    possibilities.keys.toList.sorted.map {
      key =>
        possibilities.get(key).get
    }
  }

  private def reducePossibilities(possibilities: Seq[((Int, Int, Int, Int), Option[Int])]): Map[(Int, Int), Option[Int]] = {
    groupByDigitColumnAndDigitRow(possibilities).map {
      keyAndValue =>
        val key = keyAndValue._1
        val value = keyAndValue._2
        reduceToSingleDigitPerDigitColumnAndDigitRow(key, value)
    }
  }

  private def calculateConfidences(possibilities: Seq[((Int, Int, Int, Int), Option[Int])]): Map[(Int, Int), Seq[(Option[Int], Double)]] = {
    groupByDigitColumnAndDigitRow(possibilities).map {
      keyAndValue =>
        val key = keyAndValue._1
        val value = keyAndValue._2
        (key, determineRemainingPossibilities(value))
    }
  }

  private def groupByDigitColumnAndDigitRow(possibilities: Seq[((Int, Int, Int, Int), Option[Int])]): Map[(Int, Int), Seq[((Int, Int, Int, Int), Option[Int])]] = {
    possibilities.groupBy {
      element =>
        (element._1._1, element._1._2)
    }
  }

  private def reduceToSingleDigitPerDigitColumnAndDigitRow(digitColumnAndRow: (Int, Int), possibilities: Seq[((Int, Int, Int, Int), Option[Int])]): ((Int, Int), Option[Int]) = {
    val remainingPossibilities = determineRemainingPossibilities(possibilities)
    (digitColumnAndRow, selectMostLikelyPossibility(remainingPossibilities))
  }

  private def determineRemainingPossibilities(possibilities: Seq[((Int, Int, Int, Int), Option[Int])]): Seq[(Option[Int], Double)] = {
    possibilities.groupBy {
      element =>
        element._2
    }.map {
      digitAndOccurrences =>
        (digitAndOccurrences._1, digitAndOccurrences._2.size / (digitRepresentation.NUM_COLUMNS * digitRepresentation.NUM_ROWS).toDouble)
    }.toSeq.sortWith {
      (digitAndLikelihood1, digitAndLikelihood2) =>
        digitAndLikelihood1._2 > digitAndLikelihood2._2
    }
  }

  private def selectMostLikelyPossibility(possibilities: Seq[(Option[Int], Double)]): Option[Int] = {
    possibilities.head._1
  }

  private def mapTextToPossibleDigits(text: Seq[Seq[Char]]): Seq[((Int, Int, Int, Int), Option[Int])] = {
    text.view.zipWithIndex.flatMap {
      lineWithIndex =>
        mapCharactersToPossibleDigits(lineWithIndex._1, lineWithIndex._2)
    }.force
  }

  private def mapCharactersToPossibleDigits(line: Seq[Char], lineIndex: Int): Seq[((Int, Int, Int, Int), Option[Int])] = {
    line.view.zipWithIndex.flatMap {
      charWithIndex =>
        generatePossibleDigitsForCharacterAtIndex(charWithIndex._1, charWithIndex._2, lineIndex)
    }.force
  }

  private def generatePossibleDigitsForCharacterAtIndex(currentCharacter: Char, characterIndex: Int, lineIndex: Int): Seq[((Int, Int, Int, Int), Option[Int])] = {
    val digitRowIndex: Int = characterIndex / digitRepresentation.NUM_COLUMNS
    val positionXIndex: Int = characterIndex % digitRepresentation.NUM_COLUMNS
    val digitColumnIndex: Int = lineIndex / digitRepresentation.NUM_ROWS
    val positionYIndex: Int = lineIndex % digitRepresentation.NUM_ROWS
    val possibleDigits = digitRepresentation.calculatePossibleDigits(currentCharacter, positionXIndex, positionYIndex)
    possibleDigits.map {
      digit =>
        ((digitColumnIndex, digitRowIndex, positionXIndex, positionYIndex), digit)
    }
  }
}
