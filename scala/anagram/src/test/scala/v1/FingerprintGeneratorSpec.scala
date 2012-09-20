package v1

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import v1.FingerprintGenerator._

class FingerprintGeneratorSpec extends FlatSpec with ShouldMatchers {
  behavior of "Fingerprint generation characteristics"

  it should "generate an empty fingerprint if the word is an empty string" in {
    generateFingerprint("").foreach(_ should equal(0))
  }

  it should "generate a 26 byte fingerprint" in {
    val result: Array[Byte] = generateFingerprint("targetword")
    result.size should equal(26)
  }

  it should "correctly tally the number of each letter into the fingerprint" in {
    val result = generateFingerprint("aabd")
    result(0) should equal(2)
    result(1) should equal(1)
    result(2) should equal(0)
    result(3) should equal(1)
    result(4) should equal(0)
    totalCount(result) should equal(4)
  }

  it should "correctly tally the number of each letter in a case-insensitive manner" in {
    val result = generateFingerprint("aaAAa")
    result(0) should equal(5)
    totalCount(result) should equal(5)
  }

  it should "ignore characters that are not in [a-zA-Z]" in {
    val result = generateFingerprint("a'?1$." + ('a'.toInt + 28).toChar)
    result(0) should equal(1)
    totalCount(result) should equal(1)
  }

  private def totalCount(fingerprint: Array[Byte]): Int = {
    fingerprint.foldLeft[Int](0) {
      (sum, value) =>
        sum + value.toInt
    }
  }
}
