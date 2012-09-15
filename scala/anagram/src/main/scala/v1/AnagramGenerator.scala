package v1

class AnagramGenerator(val dictionaryFiles: Seq[String]) {
  def this() = this(Seq("src/test/resources/ispell-enwl-3.1.20/english.0"))

  private val dictionary = new Dictionary(dictionaryFiles)

  def dictionarySize = dictionary.size

  def generateAnagrams(numberOfWords: Int, targetWord: String): Seq[String] = {
    Seq()
  }
}
