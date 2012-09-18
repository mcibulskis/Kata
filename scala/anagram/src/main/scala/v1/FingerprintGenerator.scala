package v1

object FingerprintGenerator {
  def generateFingerprint(text: String): Array[Byte] = {
    var fingerprint = new Array[Byte](26)

    val asciiA = 'a'.toInt
    val characterMap = text.groupBy(_.toLower.toInt - asciiA)
    println("asciiA = " + asciiA)
    println("map = " + characterMap)
    characterMap.foreach {
      indexAndLetters =>
        fingerprint(indexAndLetters._1) = indexAndLetters._2.length.toByte
    }

    fingerprint
  }
}
