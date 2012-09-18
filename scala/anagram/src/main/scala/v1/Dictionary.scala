package v1

import v1.FingerprintGenerator._

class Dictionary(val dictionaryFiles: Seq[String]) {
  private val fingerprintMap = generateFingerprintMap(loadDictionaryFiles())

  def size = fingerprintMap.size

  def words: Seq[String] = fingerprintMap.keys.toList.sorted

  def getFingerprint(word: String): Array[Byte] = {
    fingerprintMap(word)
  }

  private def generateFingerprintMap(words: Seq[String]): Map[String, Array[Byte]] = {
    words.map {
      word =>
        (word, generateFingerprint(word))
    }.toMap
  }

  private def loadDictionaryFiles(): Seq[String] = {
    if (dictionaryFiles ==  null || dictionaryFiles.isEmpty) throw new IllegalArgumentException("At least one dictionary file must be provided.")
    dictionaryFiles.flatMap {
      dictionaryFile =>
        val file = io.Source.fromFile(dictionaryFile)
        val lines = file.getLines().toList
        file.close()
        lines
    }
  }
}
