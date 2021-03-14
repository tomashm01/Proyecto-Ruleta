package exceptions;
/**
 * Authors: Jesús Díaz, Tomás Hidalgo
 * If a number is negative we will throw a NegativeException
 */
public class NegativeException extends Exception{
	  String error;
	  
	  public NegativeException (String customErrorMessage) {
	    this.error = customErrorMessage;
	  }
	  
	  public String toString() {
	    return error;
	  }
	  
}
