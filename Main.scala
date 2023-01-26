import DataProcessing._
import MySQL._
import OpeningFiles._

import scala.collection.mutable._

object Main {
  def main(args: Array[String]): Unit = {

    var i = 0;
    var speciesNamesBuffer: ListBuffer[String] = new ListBuffer[String] // Список для хранения названий видов.
    val species: ListBuffer[Species] = new ListBuffer[Species] // Список для хранения объектов класса "Species".

    // Поочередное считывание файлов из каталога.
    for (singleFile <- getListOfFiles) {
      println("File: " + getFileName(singleFile))

      val rawData = getRawData(singleFile) + "END" // END необходим для обозначения конца текстовых данных файла.
      val speciesNames = getSpeciesNames(rawData)
      speciesNamesBuffer = speciesNamesBuffer ++ speciesNames

      for (speciesName <- speciesNames) {
        if (speciesName != "END") {
          val content = getContent(speciesName, speciesNamesBuffer(i + 1), rawData)
          species += Species(i + 1, speciesName, getLatinName(content), getFamily(content), getStatus(content),
            getDescription(content), getArea(content), getPopulation(content), getHabitats(content), getFactors(content))
        }
        i += 1
      }
    }

    // Запись данных в БД.
    for (singleSpecies <- species) {
      println(singleSpecies.id + s" ${singleSpecies.getName}")
      insertData(singleSpecies)
    }
  }
}


