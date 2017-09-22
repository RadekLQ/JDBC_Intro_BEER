package be.vdab.beer;
import static org.junit.Assert.*;
import java.sql.*;
import java.util.List;
import org.junit.*;

public class BeerDaoTest {
   private static final String CREATE_TABLE = "CREATE TABLE Beers (Id int(11) NOT NULL AUTO_INCREMENT,Name varchar(100) DEFAULT NULL, BrewerId int(11) DEFAULT NULL, CategoryId int(11) DEFAULT NULL, Price float DEFAULT 0, Stock int(11) DEFAULT 0, Alcohol float DEFAULT 0,PRIMARY KEY (Id)) ENGINE=InnoDB";
   private static final String DROP_TABLE = "DROP TABLE IF EXISTS Beers";
   private static final String INSERT_BEER1 = "INSERT INTO Beers VALUES (1,'TestBeer1',1,1,2.75,100,7)";
   private static final String INSERT_BEER2 = "INSERT INTO Beers VALUES (2,'TestBeer2',1,1,1.75,200,9)";
   private BeerDao dao;

   @BeforeClass
   public static void createDatabase() throws SQLException {
      try (Connection con = DriverManager.getConnection(
         "jdbc:mysql://noelvaes.eu/StudentTestDB", "student",
         "student123");      
         Statement stmt = con.createStatement()) {
         stmt.executeUpdate(DROP_TABLE);
         stmt.executeUpdate(CREATE_TABLE);
         stmt.executeUpdate(INSERT_BEER1);
         stmt.executeUpdate(INSERT_BEER2);
      }
   }

   @AfterClass
   public static void dropDatabase() throws SQLException {
      try (Connection con = DriverManager.getConnection(
         "jdbc:mysql://noelvaes.eu/StudentTestDB", "student",
         "student123"); Statement stmt = con.createStatement()) {
         stmt.executeUpdate(DROP_TABLE);
      }
   }

   @Before
   public void setUpBefore() throws Exception {
      dao = new BeerDao();
      dao.setUrl("jdbc:mysql://noelvaes.eu/StudentTestDB");
      dao.setUser("student");
      dao.setPassword("student123");
   }

   @Test
   public void testGetBeerById() throws Exception {
      Beer beer = dao.getBeerById(1);
      assertEquals("TestBeer1", beer.getName());
   }

   @Test
   public void testBeerNotFound() throws Exception {
      Beer beer = dao.getBeerById(-1);
      assertNull(beer);
   }

   @Test
   public void testGetBeerByName() throws Exception {
      List<Beer> beers = dao.getBeersByName("TestBeer");
      for (Beer beer : beers) {
         assertTrue(beer.getName().toLowerCase().contains("test"));
      }
   }

   @Test
   public void testGetBeerByAlcohol() throws Exception {
      List<Beer> beers = dao.getBeersByAlcohol(7F);
      for (Beer beer : beers) {
         assertEquals(7F, beer.getAlcohol(), 0.1F);
      }
   }
}
