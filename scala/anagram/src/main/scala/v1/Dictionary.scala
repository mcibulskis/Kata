package v1

class Dictionary(val dictionaryFiles: Seq[String]) {
  private val words = loadDictionaryFiles()

  def size = {
    words.size
  }

  private def loadDictionaryFiles() = {
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
