package com.noackexpected.kata.scala.bankocr.v1

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

class AccountNumberValidatorSpec extends FlatSpec with ShouldMatchers {
  private val impl = new AccountNumberValidator

  it should "return true if the account number provided has exactly 9 digits" in {
    impl.validateAccountNumberLength(List(1, 2, 3, 4, 5, 6, 7, 8, 9)) should equal(true)
  }

  it should "return false if the account number has fewer than 9 digits" in {
    impl.validateAccountNumberLength(List(0)) should equal(false)
  }

  it should "return false if the account number has greater than 9 digits" in {
    impl.validateAccountNumberLength(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)) should equal(false)
  }
}
