package be.vdab.beer;
import java.sql.*;

public class SearchBeer {
   public static void main(String[] args) throws SQLException,
      ClassNotFoundException {
      String sql = "SELECT b.Name, b.Alcohol, b.Price, c.Category, br.Name "
         + "FROM Beers as b, Brewers as br, Categories as c "
         + "WHERE b.BrewerId=br.Id AND b.CategoryId=c.Id ORDER BY b.Alcohol";
      try (Connection con = DriverManager.getConnection(
         "jdbc:mysql://noelvaes.eu/StudentDB", "student",
         "student123");
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
         while (rs.next()) {
            String beerName = rs.getString("b.Name");
            double alcohol = rs.getDouble("b.Alcohol");
            double price = rs.getDouble("b.Price");
            String brewerName = rs.getString("br.Name");
            String beerKind = rs.getString("c.Category");
            System.out.format("%s %s %s %s %s%n", beerName,
               alcohol, price, brewerName, beerKind);
         }
      }
   }
}