package bets;

import java.util.EnumSet;
import roulette.WinningNumber;

/**
 * Authors: Jesús Díaz, Tomás Hidalgo Auxiliary class to manage every bet for each Move
 */
public class Bet {

  BetTypes betType;
  int amount;
  int odd; // 1:x
  Integer number = null;

  // public boolean isWin(WinningNumber myWinningNumber);

  public void setAmount(int amount) {
    this.amount = amount;
  }

  // Getters
  public int getAmount() {
    return this.amount;
  }

  public Integer getNumber() {
    return this.number;
  }

  public int getOdd() {
    return this.odd;
  }

  public BetTypes getBetType() {
    return this.betType;
  }

  public void setBetType(BetTypes betType) {
    this.betType = betType;
  }

  public void setOdd(int odd) {
    this.odd = odd;
  }

  public Bet(BetTypes betType, int amount) {
    this.betType = betType;
    this.amount = amount;
    if (EnumSet.range(BetTypes.DOZEN1, BetTypes.LINE3).contains(this.betType)) {
      this.odd = 3;
    } else {
      this.odd = 2;
    }

  }

  public Bet(Integer numberBet, int amount) {
    this.number = numberBet;
    this.amount = amount;
    this.betType = BetTypes.NUMBER;
    this.odd = 37;
  }
  public Bet(BetTypes betType) {
    this.betType = betType;
  }
  
  public Bet(Integer numberBet) {
    this.betType = BetTypes.NUMBER;
    this.number = numberBet;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((betType == null) ? 0 : betType.hashCode());
    result = prime * result + ((number == null) ? 0 : number.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Bet other = (Bet) obj;
    if (betType != other.betType)
      return false;
    if (number == null) {
      if (other.number != null)
        return false;
    } else if (!number.equals(other.number))
      return false;
    return true;
  }

  @Override
  public String toString() {
    if (this.number != null) {
      return "Number:" + this.number + ", amount:" + amount;
    }else {
      return "Type:" + betType + ", amount:" + amount;
    }
  }

  
}
