package com.noackexpected.romanNumerals

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class RomanNumeralConverterSpec extends FlatSpec with ShouldMatchers {
  val impl: RomanNumeralConverter = new RomanNumeralConverter

  it should "return I when given 1 to convert" in {
    impl.convert(1) should equal("I")
  }

  it should "return V when given 5 to convert" in {
    impl.convert(5) should equal("V")
  }

  it should "return X when given 10 to convert" in {
    impl.convert(10) should equal("X")
  }

  it should "return L when given 50 to convert" in {
    impl.convert(50) should equal("L")
  }

  it should "return C when given 100 to convert" in {
    impl.convert(100) should equal("C")
  }

  it should "return D when given 500 to convert" in {
    impl.convert(500) should equal("D")
  }

  it should "return M when given 1000 to convert" in {
    impl.convert(1000) should equal("M")
  }

  it should "return II when given 2 to convert" in {
    impl.convert(2) should equal("II")
  }

  it should "return VII when given 7 to convert" in {
    impl.convert(7) should equal("VII")
  }

  it should "return MCXXVIII when given 1128 to convert" in {
    impl.convert(1128) should equal("MCXXVIII")
  }

  it should "return IV when given 4 to convert" in {
    impl.convert(4) should equal("IV")
  }

  it should "return IX when given 9 to convert" in {
    impl.convert(9) should equal("IX")
  }

  it should "return XL when given 40 to convert" in {
    impl.convert(40) should equal("XL")
  }

  it should "return XC when given 90 to convert" in {
    impl.convert(90) should equal("XC")
  }

  it should "return CD when given 400 to convert" in {
    impl.convert(400) should equal("CD")
  }

  it should "return CM when given 900 to convert" in {
    impl.convert(900) should equal("CM")
  }

  it should "return MCMXCIX when given 1999 to convert" in {
    impl.convert(1999) should equal("MCMXCIX")
  }

  it should "return MMMCMXCIX when given 3999 to convert" in {
    impl.convert(3999) should equal("MMMCMXCIX")
  }

  it should "return DCCCLXXXVIII when given 888 to convert" in {
    impl.convert(888) should equal("DCCCLXXXVIII")
  }

  it should "return MMXIII when given 2013 to convert" in {
    impl.convert(2013) should equal("MMXIII")
  }
}
