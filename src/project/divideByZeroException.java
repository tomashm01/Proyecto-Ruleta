package project;

public class divideByZeroException extends Exception {
  String error;
  
  public divideByZeroException (String customErrorMessage) {
    this.error = customErrorMessage;
  }
  
  public String toString() {
    return error;
  }
  
}
