package project;

public class divideByZero extends Exception {
  String error;
  
  public divideByZero (String customErrorMessage) {
    this.error = customErrorMessage;
  }
  
  public String toString() {
    return error;
  }
  
}
