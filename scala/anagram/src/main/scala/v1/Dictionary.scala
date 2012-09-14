package v1

class Dictionary(dictionaryFiles: Seq[String]) {
  loadDictionaryFiles()

  def loadDictionaryFiles() {
    if (dictionaryFiles ==  null || dictionaryFiles.isEmpty) throw new IllegalArgumentException("At least one dictionary file must be provided.")
  }
}
