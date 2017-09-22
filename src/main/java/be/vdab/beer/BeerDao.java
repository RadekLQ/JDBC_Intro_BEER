package be.vdab.beer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeerDao {
    private String url;
    private String user;
    private String password;

    public Beer getBeerById(int id) throws be.vdab.beer.BeerException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con
                     .prepareStatement("SELECT * FROM Beers WHERE Id=?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Beer beer = new Beer();
                    beer.setId(id);
                    beer.setName(rs.getString("Name"));
                    beer.setPrice(rs.getFloat("Price"));
                    beer.setAlcohol(rs.getFloat("Alcohol"));
                    beer.setStock(rs.getInt("Stock"));
                    return beer;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new be.vdab.beer.BeerException(e);
        }
    }

    public List<Beer> getBeersByAlcohol(float alcohol) throws be.vdab.beer.BeerException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con
                     .prepareStatement("SELECT * FROM Beers WHERE Alcohol=?");) {
            List<Beer> beers = new ArrayList<Beer>();
            stmt.setFloat(1, alcohol);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Beer beer = new Beer();
                    beer.setId(rs.getInt("Id"));
                    beer.setName(rs.getString("Name"));
                    beer.setPrice(rs.getFloat("Price"));
                    beer.setAlcohol(rs.getFloat("Alcohol"));
                    beer.setStock(rs.getInt("Stock"));
                    beers.add(beer);
                }
                return beers;
            }
        } catch (SQLException e) {
            throw new be.vdab.beer.BeerException(e);
        }
    }

    public List<Beer> getBeersByName(String name) throws be.vdab.beer.BeerException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con
                     .prepareStatement("SELECT * FROM Beers WHERE Name LIKE ?");) {
            stmt.setString(1, "%" + name + "%");
            List<Beer> beers = new ArrayList<Beer>();
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Beer beer = new Beer();
                    beer.setId(rs.getInt("Id"));
                    beer.setName(rs.getString("Name"));
                    beer.setPrice(rs.getFloat("Price"));
                    beer.setAlcohol(rs.getFloat("Alcohol"));
                    beer.setStock(rs.getInt("Stock"));
                    beers.add(beer);
                }
                return beers;
            }
        } catch (SQLException e) {
            throw new be.vdab.beer.BeerException(e);
        }
    }

    public void updateBeer(Beer beer) throws be.vdab.beer.BeerException {
        try (Connection con = getConnection();
             PreparedStatement stmt = con
                     .prepareStatement("UPDATE Beers SET Name=?, Price=?, Alcohol=?, Stock=?  WHERE Id=?")) {
            stmt.setString(1, beer.getName());
            stmt.setFloat(2, beer.getPrice());
            stmt.setFloat(3, beer.getAlcohol());
            stmt.setInt(4, beer.getStock());
            stmt.setInt(5, beer.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new be.vdab.beer.BeerException(e);
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Private helper methods
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
