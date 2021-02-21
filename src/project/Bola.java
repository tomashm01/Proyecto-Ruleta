package project;

import java.util.List;
import java.util.stream.IntStream;

public class Bola {

  public int number;
  public String color;
  public String EvenOdd;
  public String HighLow;

  public Bola() {
    setRandomNumberToBall();
    if (this.number == 0) {
      this.color = "GREEN";
      this.EvenOdd = "NULL";
      this.HighLow = "NULL";
    } else {
      setColorBall();
      setEvenOddBall();
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
    return number;
  }
  public String getEvenOdd() {
    return EvenOdd;
  }
  public String getHighLow() {
    return HighLow;
  }
  
  public void setRandomNumberToBall() {
    this.number = (int) (Math.random() * (36) + 1);
  }

  public void setColorBall() {

    int red[] = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
    // int black[] = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
    boolean isRed = IntStream.of(red).anyMatch(redNumber -> redNumber == this.number);

    if (isRed) {
      this.color = "RED";
    } else {
      this.color = "BLACK";
    }

  }
  
  public void setEvenOddBall() {
    if (this.number % 2 == 0) {
      this.EvenOdd = "EVEN";
    } else {
      this.EvenOdd = "ODD";
    }
  }
  
  /*
   * This function return if the number is between 1-18, 19-36 or is 0.
   */
  public void setHighLow() {
    if (this.number < 19) {
      this.HighLow = "LOW";
    } else {
      this.HighLow = "HIGH";
    } 
  }
}
