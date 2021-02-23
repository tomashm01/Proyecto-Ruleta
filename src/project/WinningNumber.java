package project;

import java.util.List;
import java.util.stream.IntStream;

public class WinningNumber {
  //A winning number has his own properties itself (He is even or odd, high or low, red or black)
  private int winningNumber;
  private String color; //It is called color instead of redBlack because maybe he is green
  private String EvenOdd;
  private String HighLow; //High: number is > 19, low: <= 18

  public WinningNumber() {
    setRandomNumber();
    if (this.winningNumber == 0) {
      this.color = "GREEN";
      this.EvenOdd = "NULL";
      this.HighLow = "NULL";
    } else {
      setColor();
      setEvenOdd();
      setHighLow();
    }
  }
  //Getter list of results
  public List<String> getResults() {
    return List.of(this.color,this.EvenOdd,this.HighLow);
  }
  //Getters atributes
  public String getColor() {
    return color;
  }
  public void setColor(String color) {
    this.color = color;
  }
  public int getNumber() {
    return winningNumber;
  }
  public String getEvenOdd() {
    return EvenOdd;
  }
  public String getHighLow() {
    return HighLow;
  }
  
  public void setRandomNumber() {
    this.winningNumber = (int) (Math.random() * (36) + 1);
  }

  public void setColor() {

    int reds[] = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
    //Loop reds array, check if this.number match with some red.
    boolean isRed = IntStream.of(reds).anyMatch(redNumber -> redNumber == this.winningNumber); 

    if (isRed) {
      this.color = "RED";
    } else {
      this.color = "BLACK";
    }

  }
  
  public void setEvenOdd() {
    if (this.winningNumber % 2 == 0) {
      this.EvenOdd = "EVEN";
    } else {
      this.EvenOdd = "ODD";
    }
  }
  
  /*
   * This function return if the number is between 1-18, 19-36 or is 0.
   */
  public void setHighLow() {
    if (this.winningNumber < 19) {
      this.HighLow = "LOW";
    } else {
      this.HighLow = "HIGH";
    } 
  }
  
}
