package project;

public class NegativeException extends Exception{
	  String error;
	  
	  public NegativeException (String customErrorMessage) {
	    this.error = customErrorMessage;
	  }
	  
	  public String toString() {
	    return error;
	  }
	  
}
