package com.noackexpected.kata.scala.bankocr.v1

class AccountNumberValidator {
  private val EXPECTED_ACCOUNT_NUMBER_LENGTH = 9

  def validateAccountNumberLength(accountNumber: Seq[Int]): Boolean = {
    accountNumber.size == EXPECTED_ACCOUNT_NUMBER_LENGTH
  }
}
