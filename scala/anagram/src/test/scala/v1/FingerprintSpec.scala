package v1

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

import v1.FingerprintGenerator._

class FingerprintSpec extends FlatSpec with ShouldMatchers {
  behavior of "Instances of fingerprints"

  it should "consider two fingerprints as being equal if their fingerprint values are the same" in {
    new Fingerprint(generateFingerprint("test")) should equal(new Fingerprint(generateFingerprint("test")))
  }
}
