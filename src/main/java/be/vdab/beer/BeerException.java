package be.vdab.beer;

public class BeerException extends Exception{

   public BeerException() {
      super();
   }

   public BeerException(String message, Throwable cause,
         boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }

   public BeerException(String message, Throwable cause) {
      super(message, cause);
   }

   public BeerException(String message) {
      super(message);
   }

   public BeerException(Throwable cause) {
      super(cause);
   }

}
