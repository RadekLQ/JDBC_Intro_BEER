package be.vdab.beer;

import java.sql.*;

public class ChangeStock {
   public static void main(String[] args) throws Exception {
      String sql = "UPDATE Beers SET Stock=50 WHERE Name like '%kriek%'";
      try (Connection con = DriverManager.getConnection(
            "jdbc:mysql://noelvaes.eu/StudentDB", "student", "student123");
            Statement stmt = con.createStatement()) {
         int result = stmt.executeUpdate(sql);
         System.out.println("Stock changed: " + result);
      }
   }
}