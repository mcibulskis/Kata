package com.noackexpected.kata.scala.bankocr.v1

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

class AccountNumberValidatorSpec extends FlatSpec with ShouldMatchers {
  private val impl = new AccountNumberValidator

  //=========================
  // account number length
  //
  behavior of "Account number length validation"

  it should "return true if the account number provided has exactly 9 digits" in {
    impl.validateLength(List(1, 2, 3, 4, 5, 6, 7, 8, 9)) should equal(true)
  }

  it should "return false if the account number has fewer than 9 digits" in {
    impl.validateLength(List(0)) should equal(false)
  }

  it should "return false if the account number has greater than 9 digits" in {
    impl.validateLength(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)) should equal(false)
  }

  //============================
  // checksum
  //
  behavior of "Account number checksum validation"

  it should "return true if the account number has a valid checksum" in {
    impl.validateChecksum(List(4, 5, 7, 5, 0, 8, 0, 0, 0)) should equal(true)
  }

  it should "return false if the account number does not have a valid checksum" in {
    impl.validateChecksum(List(6, 6, 4, 3, 7, 1, 4, 9, 5)) should equal(false)
  }
}
