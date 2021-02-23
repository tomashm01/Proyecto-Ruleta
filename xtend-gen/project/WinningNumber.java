package project;

import java.util.List;

@SuppressWarnings("all")
public class WinningNumber {
  public int winningNumber;
  
  public String color;
  
  public String EvenOdd;
  
  public String HighLow;
  
  public WinningNumber() {
    throw new Error("Unresolved compilation problems:"
      + "\n=== cannot be resolved."
      + "\nThe method setColorBall() is undefined");
  }
  
  public List<String> getResults() {
    return List.<String>of(this.color, this.EvenOdd, this.HighLow);
  }
  
  public String getColor() {
    return this.color;
  }
  
  public void setColor(final String color) {
    this.color = color;
  }
  
  public int getNumber() {
    return this.winningNumber;
  }
  
  public String getEvenOdd() {
    return this.EvenOdd;
  }
  
  public String getHighLow() {
    return this.HighLow;
  }
  
  public void setRandomNumber() {
    throw new Error("Unresolved compilation problems:"
      + "\n* cannot be resolved."
      + "\nCannot cast from Object to int"
      + "\n+ cannot be resolved");
  }
  
  public void setColor() {
    throw new Error("Unresolved compilation problems:"
      + "\n=== cannot be resolved.");
  }
  
  public void setEvenOdd() {
    throw new Error("Unresolved compilation problems:"
      + "\n% cannot be resolved."
      + "\n=== cannot be resolved");
  }
  
  /**
   * This function return if the number is between 1-18, 19-36 or is 0.
   */
  public void setHighLow() {
    throw new Error("Unresolved compilation problems:"
      + "\n< cannot be resolved.");
  }
}
