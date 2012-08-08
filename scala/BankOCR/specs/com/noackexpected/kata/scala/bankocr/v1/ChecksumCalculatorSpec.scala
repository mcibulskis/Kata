package com.noackexpected.kata.scala.bankocr.v1

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

class ChecksumCalculatorSpec extends FlatSpec with ShouldMatchers {
  private val impl = new ChecksumCalculator

  behavior of "Checksum calculation"

  it should "correctly calculate the checksum of a single digit when the modulo is the same as the digit" in {
    impl.calculateChecksum(4, List(4)) should equal(0)
  }

  it should "correctly calculate the checksum of a single digit when the modulo is not the same as the digit" in {
    impl.calculateChecksum(5, List(7)) should equal(2)
  }

  it should "correctly calculate the checksum of two digits" in {
    impl.calculateChecksum(6, List(2, 5)) should equal(3)
  }

  it should "correctly calculate the checksum of multiple digits" in {
    impl.calculateChecksum(12, List(3, 6, 4, 2)) should equal(4)
  }
}
