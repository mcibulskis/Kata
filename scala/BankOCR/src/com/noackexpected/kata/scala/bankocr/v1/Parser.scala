package com.noackexpected.kata.scala.bankocr.v1

class Parser {
  def parse(text: Seq[Seq[Char]]): Seq[Seq[Option[Int]]] = {
    val possibilities = mapTextToPossibleDigits(text)
    val calculatedValues = reducePossibilities(possibilities)
    val submapMap = createSubmapsPerDigitRow(calculatedValues)
    submapMap.keys.toList.sorted.map {
      digitRow =>
        convertRowOfPossibleValuesToSequenceOfOrderedOptions(submapMap.get(digitRow).get)
    }
  }

  private def createSubmapsPerDigitRow(possibilities: Map[(Int, Int), Option[Int]]): Map[Int, Map[(Int, Int), Option[Int]]] = {
    val keyGrouping = possibilities.keys.groupBy(_._1)
    keyGrouping.keys.toList.sorted.foldLeft(Map[Int, Map[(Int, Int), Option[Int]]]()) {
      (overallMap, digitRow) =>
        overallMap ++ Map[Int, Map[(Int, Int), Option[Int]]](digitRow -> pickSubmapElements(keyGrouping.get(digitRow).get.toSeq, possibilities))
    }
  }

  private def pickSubmapElements(digitPositions: Seq[(Int, Int)], possibilities: Map[(Int, Int), Option[Int]]): Map[(Int, Int), Option[Int]] = {
    digitPositions.sorted.foldLeft(Map[(Int, Int), Option[Int]]()) {
      (overallMap, digitPosition) =>
        overallMap ++ Map[(Int, Int), Option[Int]](digitPosition -> possibilities.get(digitPosition).get)
    }
  }

  private def convertRowOfPossibleValuesToSequenceOfOrderedOptions(possibilities: Map[(Int, Int), Option[Int]]): Seq[Option[Int]] = {
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
        (digitAndOccurrences._1, digitAndOccurrences._2.size / 12.0)
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
