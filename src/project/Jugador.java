package project;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
  
  static List<Apuesta> playerBets = new ArrayList<>();
  private static int money = 500;
  private static int moneyInGame = 0;
  private static String dni = "";
  static final double MAX = 99999999;
  private static int moneyBettedLastRoll = 0;
  
  //Getters
  
  public static String getDni() {
    return dni;
  }

  public static int getMoney() {
    return money;
  }

  public static int getMoneyInGame() {
    return moneyInGame;
  }
  
  //Setters
  
  public static void setDni(String dni) {
    Jugador.dni = dni;
  }
  
  /*
   * This function check if the bet money is correct
   */
  
  public static void betMoney(int bettedMoney) throws NoMoneyException, NegativeException {

    if (bettedMoney < 0) {
      throw new NegativeException("You can't input negative bets");
    }

    if (moneyInGame + bettedMoney > money) {
      throw new NoMoneyException("You have not enough money to bet for this.");
    }

    moneyInGame += bettedMoney;

  }

  /**
   * This function add the option and the money bet in the dictionary
   * 
   * @param choice
   * @param moneyBetted
   * 
   */
  
  public static void createBet(String choice, int moneyBetted) {
    playerBets.add(new Apuesta(choice, moneyBetted));
  }
  
  /*
   * This function restart the roll
   */
  
  public static void restartRound() {
    Jugador.playerBets.clear();
    Jugador.moneyInGame = 0;
  }
  
  /*
   * This function restart the game
   */
  
  public static void restartGame() {
    Jugador.money = 500;
  }

  /*
   * This function calculate the total money to deliver to the player
   */
  
  public static void setFinalMoney() {
    Ruleta.calculateProfit();
    Jugador.money +=  Ruleta.getProfitRoll() - moneyInGame;
  }
  

  /*
   * This function validate if the dni which is passed by parameter is correct or incorrect
   * 
   * @param dni
   * 
   * @return boolean
   */
  
  static boolean validateDni(String dni) {
    // Array with all the possibles letters
    char[] lettersDni = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z',
        'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
    String num = "";
    int rest = 0;
    if (dni.length() < 9) {// Check if the dni have 9 position
      return false;
    }
    // Check if the 9º position is a letter
    if (!Character.isLetter(dni.charAt(8))) {
      return false;
    }
    // Check is the size of the dni is 9
    if (dni.length() != 9) {
      return false;
    }
    // Check if the first 8 digits are numbers
    for (int i = 0; i < 8; i++) {
      if (!Character.isDigit(dni.charAt(i))) {
        return false;
      }
      // if is a number with 8 digits, i keep it in a integer
      num += dni.charAt(i);
    }
    // Convert the number to a String
    rest = Integer.parseInt(num);
    // Calculate the letter that corresponds to the number
    rest %= 23;
    // Verify that the letter of the dni corresponds to that of the array
    if ((Character.toUpperCase(dni.charAt(8))) != lettersDni[rest]) {
      return false;
    }
    // DNI CORRECT
    return true;
  }

  /*
   * This function generate a random Dni and return it
   * 
   * @return String
   */
  
  public static String randomDni() {
    // Generate a random dni
    String dni;
    int num = (int) (Math.random() * MAX);
    String numDni = Integer.toString(num);
    int rest = num % 23;
    // Calculate the letter of the dni that corresponds to the number
    String[] letters = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z",
        "S", "Q", "V", "H", "L", "C", "K", "E"};
    String letterDni = letters[rest];
    // Concatenate all and returns a String
    dni = numDni + letterDni;
    return dni;
  }
  
}
