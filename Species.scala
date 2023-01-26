case class Species(id: Int, name: String, latinName: String, family: String, status: String, description: String, area: String, population: String,
                   habitats: String, factors: String) {

  /* --- Функции дополнительной очистки данных. --- */

  // Получение названия вида.
  def getName: String = {
    name.split(",")(0).toLowerCase().split(" ").map(_.capitalize).mkString(" ")
  }

  // Получение латинского названия вида.
  def getLatinName: String = {
    latinName.drop(1).split("\\|")(0).split("\\[")(0).split(" —")(0).replace("]", "").replace(",", "")
  }

  // Получение семейства вида.
  def getFamily: String = {
    var plantFamily = family.drop(1).split(" ")(0).replace(",", "").replaceAll("[a-zA-Z]", "").filter(_.isLetter)
    if (plantFamily.isEmpty) plantFamily = "None"
    plantFamily
  }

  // Получение статуса вида.
  def getStatus: Int = {
    var digitStatus = status.split(" категория")(0)
    if (digitStatus.isEmpty) digitStatus = "999"
    digitStatus.toInt
  }

  // Получение описания вида.
  def getDescription: String = {
    if (description.isEmpty) "None"
    else description
  }

  // Получение характеристики ареала и распространения вида.
  def getArea: String = {
    if (area.isEmpty) "None"
    else area.dropRight(1) + "."
  }

  // Получение оценки численности вида.
  def getPopulation: String = {
    if (population.isEmpty) "None"
    else
      population.dropRight(1) + "."
  }

  // Получение характеристики ареала и распространения вида.
  def getHabitats: String = {
    if (habitats.isEmpty) "None"
    else habitats
  }

  // Получение факторов угрозы вида.
  def getFactors: String = {
    if (factors.isEmpty) "None"
    else factors
  }
}
