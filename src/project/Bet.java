package project;
/**
 * Authors: Jesús Díaz, Tomás Hidalgo
 * Auxiliary class to manage every bet for each Move
 */
public class Bet {
  //Bets can be 3 specific types (black or red) (even or odd) (high or low)
  public static final String[][] POSSIBLE_BET_TYPES = {{"BLACK","RED"},{"EVEN","ODD"},{"HIGH","LOW"}};
  
  private Integer amount;
  private String type;
  
  //Constructors
  public Bet(String type, Integer amount)  { 
    this.amount = amount;
    this.type = type;
  }
  
  public Bet(String type) {
    this.type = type;
  }

  //Getters
  public int getAmount() {
    return amount;
  }

  public String getType() {
    return type;
  }

  //Setters
  public void setType(String type) {
    this.type = type;
  }

  public String toString() {
    return this.getType();
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
