package v1

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class DictionarySpec extends FlatSpec with ShouldMatchers {
  val impl = new Dictionary(Seq("english.0"))

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
}
