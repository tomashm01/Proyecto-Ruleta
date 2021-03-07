package project;

import java.util.EnumSet;

enum BetTypes 
{ 
  RED, BLACK, EVEN, ODD, HIGH, LOW, DOZEN1, DOZEN2, DOZEN3, LINE1, LINE2, LINE3, GREEN;  
}; 

/**
 * Authors: Jesús Díaz, Tomás Hidalgo
 * Auxiliary class to manage every bet for each Move
 */
public class Bet {
  //Bets can be 3 specific types (black or red) (even or odd) (high or low)

  private Integer amount;
  private BetTypes type;
  
  //Constructors
  public Bet(BetTypes type, Integer amount)  { 
    this.amount = amount;
    this.type = type;
  }
  
  public Bet(BetTypes type) {
    this.type = type;
  }

  //Getters
  public int getAmount() {
    return amount;
  }

  public BetTypes getType() {
    return type;
  }

  //Setters
  public void setType(BetTypes type) {
    this.type = type;
  }

  public String toString() {
    return this.getType().toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((type == null) ? 0 : type.hashCode());
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
    if (type == null) {
      if (other.type != null)
        return false;
    } else if (!type.equals(other.type)) //Bets are equals if they have the same type
      return false;
    return true;
  }
  
}
