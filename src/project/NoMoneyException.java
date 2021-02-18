package project;

public class NoMoneyException extends Exception{
	  String error;
	  
	  public NoMoneyException (String customErrorMessage) {
	    this.error = customErrorMessage;
	  }
	  
	  public String toString() {
	    return error;
	  }
	  
}
