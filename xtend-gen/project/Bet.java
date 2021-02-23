package project;

@SuppressWarnings("all")
public class Bet {
  public static final String[][] POSSIBLE_BET_TYPES = { new String[] { "BLACK", "RED" }, new String[] { "EVEN", "ODD" }, new String[] { "HIGH", "LOW" } };
  
  private Integer amount;
  
  private String type;
  
  public Bet(final String type, final Integer amount) {
    this.amount = amount;
    this.type = type;
  }
  
  public Bet(final String type) {
    this.type = type;
  }
  
  public int getAmount() {
    return (this.amount).intValue();
  }
  
  public String getType() {
    return this.type;
  }
  
  public void setType(final String type) {
    this.type = type;
  }
  
  @Override
  public String toString() {
    return this.getType();
  }
  
  @Override
  public int hashCode() {
    throw new Error("Unresolved compilation problems:"
      + "\n* cannot be resolved."
      + "\n=== cannot be resolved."
      + "\n+ cannot be resolved");
  }
  
  @Override
  public boolean equals(final Object obj) {
    throw new Error("Unresolved compilation problems:"
      + "\n=== cannot be resolved."
      + "\n=== cannot be resolved."
      + "\n!== cannot be resolved."
      + "\n=== cannot be resolved."
      + "\n!== cannot be resolved."
      + "\n! cannot be resolved.");
  }
}
