package com.noackexpected.kata.scala.bankocr.v1

class ChecksumCalculator {
  def calculateChecksum(modulo: Int, sequence: Seq[Int]): Int = {
    sequence.view.reverse.zipWithIndex.map {
      valueWithIndex =>
        valueWithIndex._1 * (valueWithIndex._2 + 1)
    }.foldLeft(0) { (sum, value) => sum + value } % modulo
  }
}
