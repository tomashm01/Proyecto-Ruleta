package project;

/**
 * javac -d . Main.java Menu.java Roulette.java Move.java WinningNumber.java HUD.java Player.java
    ConsoleColors.java NoMoneyException.java Bet.java divideByZeroException.java
 * 
 * java project.Main
 */
import java.util.Scanner;

public class Main {
  final static int DNI_ATTEMPTS = 3;

  public static void main(String[] args) {

    Scanner s = new Scanner(System.in);
    int option = 0;
    boolean betCompleted = false;
    boolean spunRoulette = false;
    Move roundMovement = new Move();

    // User has 3 attemps to input a valid dni or a random dni will be set
    
    clearScreen();
    if (!dniExists()) {
      setRandomDni();
    }
    do {
      HUD.printPlayerInfo();// Print money and DNI
      if (betCompleted) {
        HUD.printBetsInfo(roundMovement); // Print currentBets and money at Stake
      }
      if (spunRoulette) {
        HUD.printRouletteResults();
      }

      option = rouletteMenu();

      switch (option) {

        case 1://Bet to red or black
          int moneyBetted = insertAmount(roundMovement);
          String red = Bet.POSSIBLE_BET_TYPES[0][0];
          String black = Bet.POSSIBLE_BET_TYPES[0][1];
          String colorChoice = insertChoice(red, black);
          roundMovement.addBet(colorChoice, moneyBetted);
          betCompleted = true;
          break;
          
        case 2://Bet to even or odd
          moneyBetted = insertAmount(roundMovement);
          String even = Bet.POSSIBLE_BET_TYPES[1][0];
          String odd = Bet.POSSIBLE_BET_TYPES[1][1];
          String evenOddChoice = insertChoice(even, odd);
          roundMovement.addBet(evenOddChoice, moneyBetted);
          betCompleted = true;
          break;
          
        case 3://Bet to low(1-18) or high(19-36)
          moneyBetted = insertAmount(roundMovement);
          String high = Bet.POSSIBLE_BET_TYPES[2][0];
          String low = Bet.POSSIBLE_BET_TYPES[2][1];
          String highLowChoice = insertChoice(high, low);
          roundMovement.addBet(highLowChoice, moneyBetted);
          betCompleted = true;
          break;
          
        case 4://Spin the roulette
          Roulette.spunRoulette(roundMovement);
          roundMovement = new Move();
          spunRoulette = true;
          betCompleted = false;
          break;
          
        case 5://Print the stadistics
          if (spunRoulette) {
            Roulette.calculateStatistics();
            HUD.printStatistics();
            System.out.print(ConsoleColors.PURPLE+"Press Enter to continue"+ConsoleColors.PURPLE);
            s.nextLine();
          } else
            System.out.println("No statistics generated");
          break;
          
        case 6://Restar the game
          Player.restartGame();
          break;
          
        case 7://End of the game
          System.out.println(ConsoleColors.CYAN + "Goodbye");
          break;
          
        default://Errors control
          System.err.println(showAsError("Error in the input option"));
      }
      clearScreen();
    } while (option != 7);
  }

  /**
   * This function clean the terminal screen
   */

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
  
  /**
   * return true if custom dni is set successfully to player.
   * 
   * @return boolean
   */

  private static boolean dniExists() {
    Scanner s = new Scanner(System.in);

    int count = DNI_ATTEMPTS;

    do {// User have n attemps to introduce a valid DNI

      System.out.printf(ConsoleColors.CYAN + "Please enter your DNI. (You have %d attempts):",
          count);
      String dni = s.nextLine();

      if (!Player.isValidDni(dni)) {
        count--;
      } else {
        Player.setDni(dni);
        return true;
      }

    } while (count != 0);

    return false;
  }

  /**
   * This function set a random dni
   */
  
  private static void setRandomDni() {
    System.out.println(ConsoleColors.RED);
    System.out.printf("%107s", "We have created a DNI for you.");
    Player.setDni(Player.generateRandomDni());
  }

  /**
   * This function request a type of bet to the player, valide this type and returns it
   * 
   * @param String
   * @param String
   * 
   * @return String
   * 
   */
  
  private static String insertChoice(String possibleChoice1, String possibleChoice2) {
    Scanner s = new Scanner(System.in);
    String choice;

    do {

      HUD.printInputType(possibleChoice1, possibleChoice2);

      choice = (s.nextLine().toUpperCase());

      if (!choice.equals(possibleChoice1) && !choice.equals(possibleChoice2)) {

        System.out.printf(showAsError("%s %s %s %s\n"), "\nPlease insert", possibleChoice1, " OR ",
            possibleChoice2);
      }

    } while (!choice.equals(possibleChoice1) && !choice.equals(possibleChoice2));

    return choice;
  }

  /**
   * Print roulette menu and return a chosen option
   */
  
  static int rouletteMenu() {
    Menu roulette = new Menu("--ROULETTE MENU--", "Color bet", "Even or odd bet",
        "Higher or lower bet", "Spin roulette", "Stadistics", "Reset Game");
    return roulette.manage();
  }

  /**
   * This function request a amount of money for the bet
   * 
   * @param Move
   * 
   */
  
  static int insertAmount(Move roundMovement) {
    Scanner s = new Scanner(System.in);
    boolean invalid = true;
    int moneyBetted = 0;

    do {

      System.out.print("Please insert the amount to bet: ");
      moneyBetted = validateNumber(); // Checks if its a number
      try {
        roundMovement.betMoney(moneyBetted); // Checks if you have enough money or input is negative
        invalid = false;
      } catch (NoMoneyException | NegativeException noMoney) {
        System.err.println(noMoney);
      }

    } while (invalid);
    return moneyBetted;
  }

  /**
   * This function validate if the input fact is a number
   * 
   * @return int
   * 
   */
  
  public static int validateNumber() {
    Scanner s = new Scanner(System.in);
    boolean invalid = true;
    int numToValidate = 0;
    do {
      // Checks that the input can be parsed as an int
      if (s.hasNextInt()) {
        numToValidate = s.nextInt();
        // Advances the scanner to prevent input errors
        s.nextLine();
        // Sets the condition to false to break the loop
        invalid = false;
      } else {
        System.err.print(showAsError("Invalid input.\nInsert number again:"));
        // Advances the scanner to prevent input errors
        s.nextLine();
      }
    } while (invalid); // The loop ends when the input is valid
    return numToValidate;
  }

  /**
   * This function show a message as error in console
   * 
   * @param error
   * 
   * @return error coloured
   * 
   */
  
  private static String showAsError(String error) {
    return ConsoleColors.RESET + ConsoleColors.RED + error + ConsoleColors.RESET;
  }
}
