package project;

@SuppressWarnings("all")
public class NoMoneyException extends Exception {
  String error;
  
  public NoMoneyException(final String customErrorMessage) {
    this.error = customErrorMessage;
  }
  
  @Override
  public String toString() {
    return this.error;
  }
}
