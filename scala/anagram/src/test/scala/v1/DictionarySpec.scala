package v1

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import java.io.FileNotFoundException
import v1.FingerprintGenerator._

class DictionarySpec extends FlatSpec with ShouldMatchers {
  val impl = new Dictionary(Seq("src/test/resources/ispell-enwl-3.1.20/english.0"))

  behavior of "Creation of a dictionary"

  it should "throw an exception if no files are specified to be loaded" in {
    intercept[IllegalArgumentException] {
      new Dictionary(Seq())
    }
  }

  it should "throw an exception if a null sequence of files are specified to be loaded" in {
    intercept[IllegalArgumentException] {
      new Dictionary(null)
    }
  }

  it should "throw an exception if the specified file could not be loaded" in {
    intercept[FileNotFoundException] {
      new Dictionary(Seq("notARealFile.txt"))
    }
  }

  //
  // =====================================
  //

  behavior of "Querying information about a dictionary"

  it should "be able to return the names of the files loaded into the dictionary" in {
    impl.dictionaryFiles should equal(Seq("src/test/resources/ispell-enwl-3.1.20/english.0"))
  }

  it should "be able to return the number of words in the dictionary" in {
    impl.size should equal(47158)
  }

  //
  // =========================================
  //

  behavior of "Reading multiple files into a dictionary"

  it should "be able to read multiple dictionary files into the dictionary" in {
    new Dictionary(Seq("src/test/resources/ispell-enwl-3.1.20/english.0", "src/test/resources/ispell-enwl-3.1.20/english.1")).size should equal(68022)
  }

  it should "throw an exception if any of the specified files could not be loaded" in {
    intercept[FileNotFoundException] {
      new Dictionary(Seq("src/test/resources/ispell-enwl-3.1.20/english.0", "notARealFile.txt"))
    }
  }

  //
  // =========================================
  //

  behavior of "Asking for words or fingerprints from the dictionary"

  it should "be able to return a list of all words in the dictionary" in {
    impl.words.size should equal(47158)
    impl.words(0) should equal("ACM")
  }

  it should "be able to return the fingerprint for any word in the dictionary" in {
    impl.getFingerprint("ACM") should equal(new Fingerprint(generateFingerprint("ACM")))
  }
}
