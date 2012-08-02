package com.noackexpected.kata.scala.bankocr.v1

class DigitRepresentation {
  private val digitToDescriptorMap = Map(
    0 -> 0x020101121000L,
    1 -> 0x000001001000L,
    2 -> 0x020021120000L,
    3 -> 0x020021021000L,
    4 -> 0x000121001000L,
    5 -> 0x020120021000L,
    6 -> 0x020120121000L,
    7 -> 0x020001001000L,
    8 -> 0x020121121000L,
    9 -> 0x020121021000L
  )
  private val digitDescriptorMask = 0xF00000000000L
  private val descriptorToCharMap = Map(
    0x0L -> ' ',
    0x1L -> '|',
    0x2L -> '_'
  )
  private val charToDescriptorMap = invertMap(descriptorToCharMap)

  def generatePositionGridAsSequence(): Seq[(Int, Int)] = {
    generatePositionGrid().flatten
  }

  def generatePositionGrid(): Seq[Seq[(Int, Int)]] = {
    (0 until 4).map {
      rowIndex =>
        (0 until 3).map {
          columnIndex =>
            (columnIndex, rowIndex)
        }
    }
  }

  def calculateCharacterAtPositionForDigit(digit: Int, positionXIndex: Int, positionYIndex: Int): Char = {
    val descriptor = generateDescriptorAtPositionForDigit(digit, positionXIndex, positionYIndex)
    descriptorToCharMap.get(descriptor).get
  }

  def calculatePossibleDigits(character: Char, positionXIndex: Int, positionYIndex: Int): Seq[Option[Int]] = {
    val targetDescriptor = charToDescriptorMap.get(character).get
    (0 until 10).flatMap {
      digit =>
        val calculatedDescriptor = generateDescriptorAtPositionForDigit(digit, positionXIndex, positionYIndex)
        if (calculatedDescriptor == targetDescriptor) {
          Seq(Some(digit))
        } else {
          Seq(None)
        }
    }.toSet.toSeq.sorted
  }

  def generateDescriptorAtPositionForDigit(digit: Int, positionXIndex: Int, positionYIndex: Int): Long = {
    val mask = digitDescriptorMask >> (((positionYIndex * 3) + positionXIndex) * 4)
    (digitToDescriptorMap.get(digit).get & mask) >> ((((3 - positionYIndex) * 3) + (2 - positionXIndex)) * 4)
  }

  private def invertMap[K, V](targetMap: Map[K, V]): Map[V, K] = {
    targetMap.map {
      keyAndValue =>
        (keyAndValue._2, keyAndValue._1)
    }.toMap
  }
}
