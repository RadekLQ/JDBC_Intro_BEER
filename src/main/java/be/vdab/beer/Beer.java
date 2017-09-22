package be.vdab.beer;

import java.io.*;

public class Beer implements Serializable {
   private int id;
   private String name;
   private float price;
   private int stock;
   private float alcohol;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public float getPrice() {
      return price;
   }

   public void setPrice(float price) {
      this.price = price;
   }

   public float getAlcohol() {
      return alcohol;
   }

   public void setAlcohol(float alcohol) {
      this.alcohol = alcohol;
   }

   public int getStock() {
      return stock;
   }

   public void setStock(int stock) {
      this.stock = stock;
   }


   public String toString() {
      return String
            .format(
                  "Beer [id=%s, name=%s, price=%s, stock=%s, alcohol=%s]",
                  id,name,price,stock,alcohol);
   }

}
