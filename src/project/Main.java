package project;

/**
 * javac -d . Main.java Menu.java Ruleta.java Jugada.java Bola.java HUD.java Jugador.java
 * NoMoneyException.java NegativeException.java ConsoleColors.java Apuesta.java1
 * 
 * java project.Main
 */
import java.util.Scanner;

public class Main{
  public static int count=5;
  public static void main(String[] args) {


    Scanner s = new Scanner(System.in);
    int option=0;
    boolean betCompleted = false;
    boolean spunRoulette = false;
    Jugada jugadaDeEstaRonda = new Jugada();
    
    
    // User have 3 attemps to input a valid dni or a random dni is going to be asign to him
    clearScreen();
    if (!insertDni()) {
      setRandomDni();
    }
    do {
      
      System.out.printf(ConsoleColors.PURPLE + "\n%82s:%d\n", "MONEY", Jugador.getMoney());      
      System.out.printf("%80s:%s\n " + ConsoleColors.RESET, "DNI", Jugador.getDni());
      
      if (betCompleted) {
        System.out.print(ConsoleColors.RESET);
        System.out.printf("%89s:%s\n ", "MONEY IN GAME",
            jugadaDeEstaRonda.getMoneyAtStake());
        
        System.out.printf("%88s: ", "Current Bets");
        System.out.println(jugadaDeEstaRonda.getApuestasActuales());
        
      }

      if (spunRoulette) {

        HUD.printBallNumber();
        HUD.printBallResults();
        HUD.printBets();
        HUD.printSucessfulBet();
        HUD.printBalanceRoll();

      }
     
      option=rouletteMenu();   
      
      switch (option) {

        case 1:
          int moneyBetted = insertAmount(jugadaDeEstaRonda);
          String red = Apuesta.POSSIBLE_BET_TYPES[0][0];
          String black = Apuesta.POSSIBLE_BET_TYPES[0][1];
          String colorChoice = insertChoice(red, black);
          jugadaDeEstaRonda.addApuesta(colorChoice, moneyBetted);
          betCompleted = true;
          break;
        case 2:
          moneyBetted = insertAmount(jugadaDeEstaRonda);
          String even = Apuesta.POSSIBLE_BET_TYPES[1][0];
          String odd = Apuesta.POSSIBLE_BET_TYPES[1][1];
          String evenOddChoice = insertChoice(even, odd);
          jugadaDeEstaRonda.addApuesta(evenOddChoice, moneyBetted);
          betCompleted = true;
          break;
        case 3:
          moneyBetted = insertAmount(jugadaDeEstaRonda);
          String high = Apuesta.POSSIBLE_BET_TYPES[2][0];
          String low = Apuesta.POSSIBLE_BET_TYPES[2][1];
          String highLowChoice = insertChoice(high, low);
          jugadaDeEstaRonda.addApuesta(highLowChoice, moneyBetted);
          betCompleted = true;
          break;
        case 4:
          Ruleta.pushRoulette(jugadaDeEstaRonda);
          jugadaDeEstaRonda = new Jugada();
          spunRoulette = true;
          betCompleted = false;
          break;
        case 5:
          if (spunRoulette) {
            Ruleta.stadistics();
            HUD.printStadistics();
          }else
            System.out.println("No stadistics generated");
          break;
        case 6:
          Jugador.restartGame();
          break;
        case 7:
          System.out.println(ConsoleColors.CYAN + "Goodbye");
          break;
        default:
          System.err.println(showAsError("Error in the input option"));
      }
      clearScreen();
    } while (option != 7 );
  }

  /*
   * This function clean the terminal screen
   */

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  /*
   * This function try to input the dni of the user
   * 
   * @return boolean
   */

  private static boolean insertDni() {
    Scanner s = new Scanner(System.in);

    int count = 3;

    do {// User have 3 attemps to introduce a valid DNI

      System.out.printf(ConsoleColors.CYAN + "Please enter your DNI. (You have %d attempts):",
          count);
      String dni = s.nextLine();

      if (!Jugador.validateDni(dni)) {
        count--;
      } else {
        Jugador.setDni(dni);
        return true;
      }

    } while (count != 0);
    return false;
  }

  /*
   * This function set a random dni
   */

  private static void setRandomDni() {
    System.out.println(ConsoleColors.RED );
    System.out.printf("%107s", "We have created a DNI for you.");
    Jugador.setDni(Jugador.randomDni());
  }

  /*
   * This function request a type of bet to the player, valide this type and returns it
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

  /*
   * This function print the roulette menu
   */

  static int rouletteMenu() {
    Menu roulette = new Menu("--ROULETTE MENU--", "Color bet", "Even or odd bet",
        "Higher or lower bet", "Spin roulette","Stadistics", "Reset Game");
    return roulette.manage();
  }

  /*
   * This function request a amount of money for the bet
   */

  static int insertAmount(Jugada jugadaDeEstaRonda) {
    Scanner s = new Scanner(System.in);
    boolean invalid = true;
    int moneyBetted = 0;

    do {

      System.out.print("Please insert the amount to bet: ");
      moneyBetted = validateNumber();

      try {
        jugadaDeEstaRonda.betMoney(moneyBetted);
        invalid = false;
      } catch (NoMoneyException | NegativeException noMoney) {
        System.err.println(noMoney);
      }

    } while (invalid);
    return moneyBetted;
  }

  /*
   * This function validate if the input fact is a number
   * 
   * @return int
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

  /*
   * This function returns an error in the menu option
   * 
   * @param error
   * 
   * @return String
   */
  private static String showAsError(String error) {
    return ConsoleColors.RESET + ConsoleColors.RED + error + ConsoleColors.RESET;
  }
}
