package project;

public class Player {
  private static int INITIAL_MONEY = 500;
  
  private static int money = INITIAL_MONEY;
  private static String dni = "";
  
  //Getters
  public static String getDni() {
    return dni;
  }
  public static int getMoney() {
    return money;
  }
  
  //Setters 
  public static void setDni(String dni) {
    Player.dni = dni;
  }
  
  public static void restartGame() {
    Player.money = 500;
  }

  /*
   * This function sum the balance after move
   */
  public static void setFinalMoney(Move playerMove) {
   Player.money += playerMove.getFinalBalance();
  }

  /*
   * This function validate if the dni which is passed by parameter is valid
   * @param dni
   * @return boolean
   */
  static boolean isValidDni(String dni) {
    String pattern = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$(?i)";
    //^[0-9]{8} -> first 8 characters must be numbers
    //$ -> last character
    //(?i) ->  case insensitive 
    if (!dni.matches(pattern)) {
      return false;
    }
    
    String validChars = pattern.substring(10, 33); //[T-E]
    char dniLetter = dni.toUpperCase().charAt(8); 
    int charIndex = Integer.parseInt(dni.replace(dni.charAt(8), ' ').trim()) % 23; 
    //Get module from dni number
    if (validChars.charAt(charIndex) == dniLetter) { 
      //If module from dni number as index equals DNI letter
      return true;
    }else {
      return false;
    }
  }

  /* 
   * @return random Dni
   */
  public static String generateRandomDni() {
    int MAX = 99999999; 
    int randomNum = (int) (Math.random() * MAX);//random 8 digits
    // Calculate the letter of the dni that corresponds to the number
    String validChars = "TRWAGMYFPDXBNJZSQVHLCKE";
    // Concatenate all and returns a dni
    return String.valueOf(randomNum) + validChars.charAt(randomNum % 23);
  }
  
}
