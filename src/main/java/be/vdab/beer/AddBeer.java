package be.vdab.beer;

import java.sql.*;

public class AddBeer {
    public static void main(String[] args) throws Exception {
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://noelvaes.eu/StudentDB", "student", "student123");
             Statement stmt = con.createStatement()) {
            int brewerNr = findBrewer(stmt, "Alken");
            if (brewerNr == -1) {
                System.out.println("Brewer unknown");
                return;
            }

            int catNr = findCategory(stmt, "Tripel");
            if (catNr == -1) {
                System.out.println("Category unknown");
                return;
            }

            String sql = "INSERT INTO Beers (Name, Alcohol, Price, Stock, BrewerId, CategoryId) "
                    + "VALUES('Noel Tripel',12,4.5,100,"
                    + brewerNr
                    + ","
                    + catNr
                    + ")";

            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Beer added with id " + id);
                }
            }
        }
    }

    private static int findBrewer(Statement stmt, String brouwer)
            throws SQLException {
        int brewerNr = -1;
        String sql = "SELECT Id FROM Brewers WHERE Name='" + brouwer + "'";
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                brewerNr = rs.getInt(1);
            }
        }
        return brewerNr;
    }

    private static int findCategory(Statement stmt, String cat)
            throws SQLException {
        String sql = "SELECT Id FROM Categories WHERE Category='" + cat + "'";
        int catNr = -1;
        try (ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                catNr = rs.getInt(1);
            }
        }
        return catNr;
    }
}