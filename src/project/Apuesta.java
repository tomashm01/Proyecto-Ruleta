package project;

public class Apuesta extends Jugador {
  
  public static final String[] POSSIBLE_BET_TYPES = {"RED","BLACK","EVEN","ODD","HIGH","LOW"};
  private Integer amount;
  private String type;
  
  //Constructors
  
  public Apuesta(String type, Integer amount)  {
    this.amount = amount;
    this.type = type;
  }
    
  public Apuesta(String type) {
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
  
  //Overrides
  
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
    Apuesta other = (Apuesta) obj;
    if (type == null) {
      if (other.type != null)
        return false;
    } else if (!type.equals(other.type))
      return false;
    return true;
  }

  public String toString() {
    return this.getType();
  }
  
}
