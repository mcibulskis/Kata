package com.noackexpected.kata.scala.bankocr.v1

class AccountNumberValidator {
  private val EXPECTED_ACCOUNT_NUMBER_LENGTH = 9
  private val CHECKSUM_MODULO = 11

  private val checksumCalculator = new ChecksumCalculator

  def validateLength(accountNumber: Seq[Int]): Boolean = {
    accountNumber.size == EXPECTED_ACCOUNT_NUMBER_LENGTH
  }

  def validateChecksum(accountNumber: Seq[Int]): Boolean = {
    checksumCalculator.calculateChecksum(CHECKSUM_MODULO, accountNumber) == 0
  }
}
