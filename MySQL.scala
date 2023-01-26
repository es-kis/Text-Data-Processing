import java.sql._

object MySQL {

  val url = "jdbc:mysql://localhost/testredbook?allowMultiQueries=true&serverTimezone=Europe/Moscow&useSSL=false"
  val driver = "com.mysql.jdbc.Driver"
  val username = "root"
  val password = "1234"
  var con: Connection = null

  def insertData(species: Species): Unit = {
    try {
      Class.forName("com.mysql.jdbc.Driver")
      con = DriverManager.getConnection(url, username, password) // Создание подключения к БД.

      val insertSql =
        """
          |INSERT INTO species (s_ID, name, latinName, family, status, description, area, population, habitats, factors)
          |values (?,?,?,?,?,?,?,?,?,?)
      """.stripMargin

      val preparedStmt: PreparedStatement = con.prepareStatement(insertSql)
      preparedStmt.setInt(1, species.id)
      preparedStmt.setString(2, species.getName)
      preparedStmt.setString(3, species.getLatinName)
      preparedStmt.setString(4, species.getFamily)
      preparedStmt.setInt(5, species.getStatus)
      preparedStmt.setString(6, species.getDescription)
      preparedStmt.setString(7, species.getArea)
      preparedStmt.setString(8, species.getPopulation)
      preparedStmt.setString(9, species.getHabitats)
      preparedStmt.setString(10, species.getFactors)
      preparedStmt.execute

      preparedStmt.close()
    } catch {
      case se: SQLException => se.printStackTrace
      case e: Exception => e.printStackTrace
    }
    finally {
      con.close()
    }
  }

}
