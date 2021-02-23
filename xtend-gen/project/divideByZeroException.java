package project;

@SuppressWarnings("all")
public class divideByZeroException extends Exception {
  String error;
  
  public divideByZeroException(final String customErrorMessage) {
    this.error = customErrorMessage;
  }
  
  @Override
  public String toString() {
    return this.error;
  }
}
