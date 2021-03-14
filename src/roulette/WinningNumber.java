package roulette;
/**
 * Authors: Jesús Díaz, Tomás Hidalgo
 * Random number between 0 - 36, this number has his own properties like color, Even or Odd, High or Low
 */
import java.util.List;
import java.util.stream.IntStream;
import bets.BetTypes;

public class WinningNumber {
  //A winning number has his own properties itself (He is even or odd, high or low, red or black)
  private int winningNumber;
  private BetTypes redBlack;
  private BetTypes evenOdd;
  private BetTypes highLow; //High: number is > 19, low: <= 18
  private BetTypes dozen; //D1 : 1-12, D2 : 13-24, D3 : 25-36
  private BetTypes line; //L1 : 1,4,7..34 , L2 : 2,5,8...35 , L3 : 3,6,9...36
  

  //Constructor
  public WinningNumber() {
    setRandomNumber();
    if (this.winningNumber == 0) {
      this.redBlack = BetTypes.GREEN;
      this.evenOdd = BetTypes.GREEN;
      this.highLow = BetTypes.GREEN;
      this.dozen = BetTypes.GREEN;
      this.line = BetTypes.GREEN;
    } else {
      setColor();
      setEvenOdd();
      setHighLow();
      setDozen();
      setLine();
    }
  }
  
  //Getters and Setters
  public List<BetTypes> getResults() {
    return List.of(this.redBlack,this.evenOdd,this.highLow,this.dozen,this.line);
  }
  public BetTypes getColor() {
    return redBlack;
  }
  public Integer getNumber() {
    return winningNumber;
  }
  public BetTypes getEvenOdd() {
    return evenOdd;
  }
  public BetTypes getDozen() {
    return dozen;
  }
  public BetTypes getLine() {
    return line;
  }
  public BetTypes getHighLow() {
    return highLow;
  }
  
  private void setRandomNumber() {
    this.winningNumber = (int) (Math.random() * (37));
  }

  private void setColor() {
    if (isRed()) {
      this.redBlack = BetTypes.RED;
    } else {
      this.redBlack = BetTypes.BLACK;
    }    
  }

  public boolean isRed() {
    int reds[] = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
    //Loop reds array, check if this.number match with some red.
    boolean isRed = IntStream.of(reds).anyMatch(redNumber -> redNumber == this.winningNumber);
    return isRed;
  }
  
  private void setEvenOdd() {
    if (this.winningNumber % 2 == 0) {
      this.evenOdd = BetTypes.EVEN;
    } else {
      this.evenOdd = BetTypes.ODD;
    }
  }
  
  private void setHighLow() {
    if (this.winningNumber < 19) {
      this.highLow = BetTypes.LOW;
    } else {
      this.highLow = BetTypes.HIGH;
    } 
  }
  
  private void setDozen() {
    if (this.winningNumber % 12 == 0 && this.winningNumber != 0) {
      this.dozen =  BetTypes.valueOf("DOZEN" + ( (int) this.winningNumber/12));    
    }else {
      this.dozen =  BetTypes.valueOf("DOZEN" + ( (int) this.winningNumber/12 + 1));    
    }
  }
  
  private void setLine() {
    if (this.winningNumber % 3 == 0) {
      this.line = BetTypes.valueOf("LINE" + 3);
    }else {
      this.line = BetTypes.valueOf("LINE" + this.winningNumber % 3);
    }
  }
  
}
