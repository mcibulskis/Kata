package v1

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class AnagramGeneratorSpec extends FlatSpec with ShouldMatchers {
  val impl = new AnagramGenerator()

  behavior of "Creation of an anagram generator"

  it should "load the default dictionary if no specific dictionary is specified" in {
    impl.getDictionaryNames should equal(Seq("ispell-enwl-3.1.20/english.0"));
  }


  //
  // =====================================
  //

  behavior of "Generation of anagrams consisting of one word from the target word"

  it should "return an empty sequence if no one word anagrams can be created from the target word" in {
    val results = impl.generateAnagrams(1, "zzzzzz")

    results.size should equal(0)
  }
}
