package be.vdab.beer;

import java.sql.*;

public class DatabaseMetaData {
   public static void main(String[] args) {
      try (Connection con = DriverManager.getConnection(
            "jdbc:mysql://noelvaes.eu/StudentDB", "student", "student123");) {
         java.sql.DatabaseMetaData dbmd = con.getMetaData();
         System.out.println("Database name: " + dbmd.getDatabaseProductName());
         System.out.println("Database version: "
               + dbmd.getDatabaseProductVersion());
         System.out.println("Driver name: " + dbmd.getDriverName());
         System.out.println("Driver major version: "
               + dbmd.getDriverMajorVersion());
         System.out.println("Driver minor version: "
               + dbmd.getDriverMinorVersion());
         System.out.println("Transacties: " + dbmd.supportsTransactions());
         System.out.println("Scrollable resultset: "
               + dbmd.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
         System.out.println("Updateable resultset: "
               + dbmd.supportsResultSetConcurrency(
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_UPDATABLE));
         con.close();
      } catch (SQLException ex) {
         printError(ex);
      } catch (Exception ex) {
         ex.printStackTrace(System.err);
      }
   }

   private static void printError(SQLException ex) {
      System.out.println("SQL state: " + ex.getSQLState());
      System.out.println("SQL errorcode: " + ex.getErrorCode());
      System.out.println("SQL message:" + ex.getMessage());
      if (ex.getNextException() != null) {
         printError(ex.getNextException());
      }
   }
}