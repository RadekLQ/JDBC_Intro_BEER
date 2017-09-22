package be.vdab.beer;

import java.sql.*;

public class ConnectDB {
   public static void main(String[] args) {
      try (Connection con = DriverManager.getConnection(
            "jdbc:mysql://noelvaes.eu/StudentDB","student",
            "student123");)  {
         System.out.println("Connection OK");
      }
      catch (Exception ex) {
         System.out.println("Oops, something went wrong!");
         ex.printStackTrace(System.err);
      }
   }
}