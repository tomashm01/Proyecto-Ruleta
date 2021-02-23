package project;

@SuppressWarnings("all")
public class NegativeException extends Exception {
  String error;
  
  public NegativeException(final String customErrorMessage) {
    this.error = customErrorMessage;
  }
  
  @Override
  public String toString() {
    return this.error;
  }
}
