package project;

import project.removeBetInCurrentBets;

@SuppressWarnings("all")
public class Player {
  private static int money = 500;
  
  private static String dni = "";
  
  public static String getDni() {
    return Player.dni;
  }
  
  public static int getMoney() {
    return Player.money;
  }
  
  public static void setDni(final String dni) {
    Player.dni = dni;
  }
  
  public static void restartGame() {
    Player.money = 500;
  }
  
  /**
   * This function calculate the total money to deliver to the player
   */
  public static void setFinalMoney(final removeBetInCurrentBets miJugada) {
    throw new Error("Unresolved compilation problems:"
      + "\n+= cannot be resolved.");
  }
  
  /**
   * This function validate if the dni which is passed by parameter is correct or incorrect
   * 
   * @param dni
   * 
   * @return boolean
   */
  static boolean isValidDni(final String dni) {
    throw new Error("Unresolved compilation problems:"
      + "\n! cannot be resolved."
      + "\n% cannot be resolved."
      + "\n=== cannot be resolved.");
  }
  
  /**
   * This function generate a random Dni and return it
   * 
   * @return String
   */
  public static String generateRandomDni() {
    throw new Error("Unresolved compilation problems:"
      + "\n* cannot be resolved."
      + "\n% cannot be resolved."
      + "\n+ cannot be resolved."
      + "\nCannot cast from Object to int");
  }
}
