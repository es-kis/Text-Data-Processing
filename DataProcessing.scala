import scala.collection.mutable._

object DataProcessing {

  // Получение названий видов.
  def getSpeciesNames(str: String): List[String] = {
    var speciesNames: ListBuffer[String] = new ListBuffer[String]
    val splitStr = str.split("\n")
    for (singleStr <- splitStr) {
      if (singleStr.replace(", или ", "").toUpperCase().equals(singleStr.replace(", или ", "")) && singleStr.exists(_.isLetter)) {
        speciesNames += singleStr.replace("\f", "").replaceAll("\\s*\\([^\\)]*\\)\\s*", " ").replaceAll("\\s*\\[[^\\)]*\\]\\s*", " ")
      }
    }
    speciesNames.toList
  }

  // Получение содержимого статьи.
  def getContent(currentArticle: String, nextArticle: String, str: String): String = {
    str.split(s"$nextArticle")(0).split(s"$currentArticle")(1)
      .replace("\n", " ")
      .replace("\f", "")
      .replaceAll("(?<=\f)\\d{3}", "")
      .replaceAll("\\s*\\([^\\)]*\\)\\s*", " ")
      .replaceAll("\\[.*?\\]","")
      // .replaceAll("\\s*\\|[^\\)]*\\]\\s*", " ")
      .replaceAll("\\s+", " ")
      .replace("- ", "")
      .replace(" .", ".")
  }

  // Получение латинского названия вида.
  def getLatinName(str: String): String = {
    str.split("Cemel")(0)
      .split("Cemei")(0)
      .split("[А-я]")(0)
      .filter(!_.isDigit)
  }

  // Получение семейства.
  def getFamily(str: String): String = {
    val regex = "(?<=Семейство)(.*)(?=Статус)".r
    val family = (regex findAllIn str).mkString("")
    family
  }

  // Получение статуса.
  def getStatus(str: String): String = {
    val regex = "(?<=Статус. )(.*)(?=Краткая характеристика основных)".r
    val status = (regex findAllIn str).mkString("")
    status
  }

  // Получение описания.
  def getDescription(str: String): String = {
    val regex = "(?<=основных определительных признаков. )(.*)(?= Краткая характеристика ареала)".r
    val desc = (regex findAllIn str).mkString("")
    desc
  }

  // Получение ареала распространения.
  def getArea(str: String): String = {
    val regex = "(?<=распространение на территории Калужской области. )(.*)(?= Оценка численности на )".r
    val area = (regex findAllIn str).mkString("")
    area
  }

  // Получение ареала распространения.
  def getPopulation(str: String): String = {
    val regex = "(?<=Калужской области и ее динамика. )(.*)(?= Типичные и характерные места обитания)".r
    val area = (regex findAllIn str).mkString("")
    area
  }

  // Получение мест обитания.
  def getHabitats(str: String): String = {
    val regex = "(?<=биологии и экологии. )(.*)(?= Основные лимитирующие факторы)".r
    val hab = (regex findAllIn str).mkString("")
    hab
  }

  // Получение факторов угрозы.
  def getFactors(str: String): String = {
    val regex = "(?<=факторы угрозы. )(.*)(?= Принятые и необходимые)".r
    val factors = (regex findAllIn str).mkString("")
    factors
  }
}
