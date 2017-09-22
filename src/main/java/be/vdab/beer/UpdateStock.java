package be.vdab.beer;

import java.sql.*;

public class UpdateStock {
   public static void main(String[] args) {
      String sql = "SELECT Id, Stock FROM Beers";
      try (Connection con = DriverManager.getConnection(
            "jdbc:mysql://noelvaes.eu/StudentDB", "student", "student123");
            Statement stmt = con.createStatement(
                  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql)) {
         while (rs.next()) {
            int stock = rs.getInt(2) + 50;
            rs.updateInt(2, stock);
            rs.updateRow();
         }
      } catch (Exception ex) {
         ex.printStackTrace(System.err);
      }
   }
}