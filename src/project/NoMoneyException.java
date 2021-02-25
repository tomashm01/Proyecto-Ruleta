package project;
/**
 * Authors: Jesús Díaz, Tomás Hidalgo
 * If you have no money to bet, we will throw a NoMoneyException
 */
public class NoMoneyException extends Exception{
	  String error;
	  
	  public NoMoneyException (String customErrorMessage) {
	    this.error = customErrorMessage;
	  }
	  
	  public String toString() {
	    return error;
	  }
	  
}
