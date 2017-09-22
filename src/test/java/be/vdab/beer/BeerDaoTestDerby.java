package be.vdab.beer;
import static org.junit.Assert.*;
import java.sql.*;
import java.util.List;
import org.junit.*;

public class BeerDaoTestDerby {
   private static final String CREATE_TABLE = "CREATE TABLE Beers (Id INT ,Name VARCHAR(100), BrewerId INT, CategoryId INT, Price float, Stock INT, Alcohol FLOAT, CONSTRAINT primary_key PRIMARY KEY (Id))";
   private static final String INSERT_BEER1 = "INSERT INTO Beers VALUES (1,'TestBeer1',1,1,2.75,100,7)";
   private static final String INSERT_BEER2 = "INSERT INTO Beers VALUES (2,'TestBeer2',1,1,1.75,200,9)";
   private static final String DELETE_BEER1 = "DELETE FROM Beers WHERE Id=1";
   private static final String DELETE_BEER2 = "DELETE FROM Beers WHERE Id=2";
   private static final String DROP_TABLE = "DROP TABLE Beers";
   private BeerDao dao;

   @BeforeClass
   public static void createDatabase() throws SQLException {
      try (Connection con = DriverManager.getConnection(
         "jdbc:derby:memory:TestDB;create=true");
         Statement stmt = con.createStatement()) {
         stmt.executeUpdate(CREATE_TABLE);
      }
   }

   @AfterClass
   public static void dropDatabase() throws SQLException {
      try (Connection con = DriverManager.getConnection(
         "jdbc:derby:memory:TestDB");
         Statement stmt = con.createStatement()) {
         stmt.executeUpdate(DROP_TABLE);
      }
   }

   @Before
   public void setUpBefore() throws Exception {
      try (Connection con = DriverManager.getConnection(
         "jdbc:derby:memory:TestDB");
         Statement stmt = con.createStatement()) {
         stmt.executeUpdate(INSERT_BEER1);
         stmt.executeUpdate(INSERT_BEER2);
      }
      dao = new BeerDao();
      dao.setUrl("jdbc:derby:memory:TestDB");
      dao.setUser("");
      dao.setPassword("");
   }

   @After
   public void cleanupAfter() throws Exception {
      try (Connection con = DriverManager.getConnection(
         "jdbc:derby:memory:TestDB");
         Statement stmt = con.createStatement()) {
         stmt.executeUpdate(DELETE_BEER1);
         stmt.executeUpdate(DELETE_BEER2);
      }
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
   
   @Test
   public void testUpdateBeer() throws Exception {
      Beer beer = dao.getBeerById(2);
      beer.setStock(500);
      dao.updateBeer(beer);
      beer = dao.getBeerById(2);
      assertEquals(500, beer.getStock());      
   }
}
