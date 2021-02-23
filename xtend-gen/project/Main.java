package project;

import java.util.Scanner;
import project.ConsoleColors;
import project.Menu;
import project.NegativeException;
import project.NoMoneyException;
import project.Player;
import project.removeBetInCurrentBets;

@SuppressWarnings("all")
public class Main {
  public static int count = 5;
  
  public static void main(final String[] args) {
    throw new Error("Unresolved compilation problems:"
      + "\n! cannot be resolved."
      + "\n!== cannot be resolved."
      + "\nThe method or field break is undefined"
      + "\nThe method moneyBetted(int) is undefined"
      + "\nThe method or field moneyBetted is undefined"
      + "\nThe method or field break is undefined"
      + "\nThe method moneyBetted(int) is undefined"
      + "\nThe method or field moneyBetted is undefined"
      + "\nThe method or field break is undefined"
      + "\nThe method or field break is undefined"
      + "\nThe method or field break is undefined"
      + "\nThe method or field break is undefined"
      + "\nThe method or field break is undefined");
  }
  
  /**
   * This function clean the terminal screen
   */
  public static void clearScreen() {
    System.out.print("u000033[Hu000033[2J");
    System.out.flush();
  }
  
  /**
   * This function try to input the dni of the user
   * 
   * @return boolean
   */
  private static boolean dniExists() {
    throw new Error("Unresolved compilation problems:"
      + "\n!== cannot be resolved."
      + "\n! cannot be resolved."
      + "\n-- cannot be resolved.");
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
   */
  private static String insertChoice(final String possibleChoice1, final String possibleChoice2) {
    throw new Error("Unresolved compilation problems:"
      + "\n! cannot be resolved."
      + "\n! cannot be resolved."
      + "\n! cannot be resolved."
      + "\n! cannot be resolved."
      + "\n&& cannot be resolved"
      + "\n&& cannot be resolved");
  }
  
  /**
   * This function print the roulette menu
   */
  static int rouletteMenu() {
    Menu roulette = new Menu("--ROULETTE MENU--", "Color bet", "Even or odd bet", "Higher or lower bet", "Spin roulette", "Stadistics", "Reset Game");
    return roulette.manage();
  }
  
  /**
   * This function request a amount of money for the bet
   */
  static int insertAmount(final removeBetInCurrentBets jugadaDeEstaRonda) {
    Scanner s = new Scanner(System.in);
    boolean invalid = true;
    int moneyBetted = 0;
    do {
      {
        System.out.print("Please insert the amount to bet: ");
        moneyBetted = Main.validateNumber();
        try {
          jugadaDeEstaRonda.betMoney(moneyBetted);
          invalid = false;
        } catch (final Throwable _t) {
          if (_t instanceof NoMoneyException || _t instanceof NegativeException) {
            final Exception noMoney = (Exception)_t;
            System.err.println(noMoney);
          } else {
            COMPILE ERROR : 'org.eclipse.xtext.xbase.lib.Exceptions' could not be found on the classpath!
          }
        }
      }
    } while(invalid);
    return moneyBetted;
  }
  
  /**
   * This function validate if the input fact is a number
   * 
   * @return int
   */
  public static int validateNumber() {
    Scanner s = new Scanner(System.in);
    boolean invalid = true;
    int numToValidate = 0;
    do {
      boolean _hasNextInt = s.hasNextInt();
      if (_hasNextInt) {
        numToValidate = s.nextInt();
        s.nextLine();
        invalid = false;
      } else {
        System.err.print(Main.showAsError("Invalid input.\nInsert number again:"));
        s.nextLine();
      }
    } while(invalid);
    return numToValidate;
  }
  
  /**
   * This function returns an error in the menu option
   * 
   * @param error
   * 
   * @return String
   */
  private static String showAsError(final String error) {
    throw new Error("Unresolved compilation problems:"
      + "\n+ cannot be resolved."
      + "\n+ cannot be resolved"
      + "\n+ cannot be resolved");
  }
}
