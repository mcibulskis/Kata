package v1

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

import v1.FingerprintGenerator._

class AnagramGeneratorSpec extends FlatSpec with ShouldMatchers {
  val impl = new AnagramGenerator()

  behavior of "Creation of an anagram generator"

  it should "load the default dictionary if no specific dictionary is specified" in {
    impl.dictionaryFiles should equal(Seq("src/test/resources/ispell-enwl-3.1.20/english.0"))
  }

  it should "return the correct dictionary size" in {
    impl.dictionarySize should equal(47158)
  }

  //
  // =====================================
  //

  behavior of "Generation of anagrams consisting of one word from the target word"

  it should "return an empty sequence if no one word anagrams can be created from the target word" in {
    impl.generateAnagrams(1, "zzzzzz").size should equal(0)
  }

  it should "return a sequence containing only the same word as the target word if there are no one word anagrams for the target word" in {
    impl.generateAnagrams(1, "an") should equal(Seq("an"))
  }

  //
  // =====================================
  //

  behavior of "Generation of words and fingerprint remainders from target fingerprints"

  it should "return an empty sequence if no words can be generated from the target fingerprint" in {
    impl.generateWordAndFingerprintRemainderFromFingerprint(generateFingerprint("zzzzz")) should equal(Seq())
  }

  it should "return a sequence containing only a single word if it is the only word that can be made from the target fingerprint" in {
    impl.generateWordAndFingerprintRemainderFromFingerprint(generateFingerprint("a")) should equal(Seq(("a", generateFingerprint(""))))
  }
}
