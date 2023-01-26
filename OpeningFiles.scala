import java.io.File

object OpeningFiles {
  // Функция получения наименований всех текстовых файлов из каталога с данными.
  def getListOfFiles: List[File] = {
    new java.io.File("E://test").listFiles.filter(_.getName.endsWith(".txt")).toList
  }

  // Функция получения имени файла.
  def getFileName(file: File): String = {
    file.toPath.getFileName.toString
  }

  // Функция считывания всех данных из файла.
  def getRawData(fileName: File): String = {
    val source = scala.io.Source.fromFile(s"$fileName")
    try source.mkString finally source.close()
  }

  // Постраничное разделение данных.
  def separateData(str: String): Array[String] = {
    str.split("\f")
  }
}
