package be.vdab.beer;

import java.io.*;
import java.nio.file.*;
import java.sql.*;

public class SaveImage {
   public static void main(String[] args) throws Exception {
      Path path = Paths.get("resources/jupiler.gif");
      String sql = "UPDATE Beers SET Image=? WHERE Name=?";
      try (InputStream fi = Files.newInputStream(path);
            Connection con = DriverManager
                  .getConnection("jdbc:mysql://noelvaes.eu/StudentDB",
                        "student", "student123");
            PreparedStatement stmt = con.prepareStatement(sql);) {
         stmt.setBinaryStream(1, fi);        
         stmt.setString(2, "Jupiler");
         int result = stmt.executeUpdate();
         if (result == 0)
            System.out.println("Not found");
         else
            System.out.println("Image saved");
      }
   }
}