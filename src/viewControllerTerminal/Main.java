package viewControllerTerminal;
import java.util.EnumSet;
import bets.*;
import player.Move;
import player.Player;
import project.Roulette;
/**
 * Authors: Jesús Díaz, Tomás Hidalgo
 * Where user data is received, and finally, processed.
 * Making our roulette interactive.
 * Using a Menu.
 */
import java.util.Scanner;
import Exceptions.NegativeException;
import Exceptions.NoMoneyException;

public class Main {
  // User has DNI_ATTEMPTS to input a valid dni or a random dni will be set
  final static int DNI_ATTEMPTS = 3;

  public static void main(String[] args) {

    Scanner s = new Scanner(System.in);
    int option = 0;
    int number;
    boolean betCompleted = false;
    boolean spunRoulette = false;
    Move roundMovement = new Move();
 
    EnumSet<BetTypes> redBlackBet,evenOddBet,highLowBet, dozenBet,lineBet;
    redBlackBet = EnumSet.of(BetTypes.RED,BetTypes.BLACK);
    evenOddBet = EnumSet.of(BetTypes.EVEN,BetTypes.ODD);
    highLowBet = EnumSet.of(BetTypes.HIGH,BetTypes.LOW);
    dozenBet = EnumSet.range(BetTypes.DOZEN1, BetTypes.DOZEN3);
    lineBet = EnumSet.range(BetTypes.LINE1, BetTypes.LINE3);
    
    HUD.clearScreen();
    if (!dniExists()) {
      setRandomDni();
    }
    do {

      HUD.printPlayerInfo();// Print money and DNI

      if (betCompleted) {
        HUD.printBetsInfo(roundMovement); // Print currentBets and money at Stake
      }

      option = rouletteMenu();

      switch (option) {

        case 1:// Bet to red or black
          int moneyBetted = insertAmount(roundMovement);
          BetTypes colorChoice = insertChoice(redBlackBet); 
          roundMovement.addBet(new Bet(colorChoice,moneyBetted));
          betCompleted = true;
          break;

        case 2:// Bet to even or odd
          moneyBetted = insertAmount(roundMovement);
          BetTypes evenOddChoice = insertChoice(evenOddBet);
          roundMovement.addBet(new Bet(evenOddChoice,moneyBetted));
          betCompleted = true;
          break;

        case 3:// Bet to low(1-18) or high(19-36)
          moneyBetted = insertAmount(roundMovement);
          
          BetTypes highLowChoice = insertChoice(highLowBet);
          roundMovement.addBet(new Bet(highLowChoice,moneyBetted));
          betCompleted = true;
          break;

        case 4:// Bet to Dozen (1-12,13-24,24-36)
          moneyBetted = insertAmount(roundMovement);
          BetTypes DozenChoice = insertChoice(dozenBet);
          roundMovement.addBet(new Bet(DozenChoice,moneyBetted));
          betCompleted = true;
          break;

        case 5:// Bet to Line (1,3..34) (2,5...35) (3,6...36)
          moneyBetted = insertAmount(roundMovement);
          BetTypes lineChoice = insertChoice(lineBet);          
          roundMovement.addBet(new Bet(lineChoice,moneyBetted));
          betCompleted = true;
          break;
       
        case 6:// Bet to Number (0-36)
          number=insertNumber();
          moneyBetted = insertAmount(roundMovement);
          roundMovement.addBet(new Bet(number,moneyBetted));  
          betCompleted = true;
          break;
       
        case 7:
          Roulette.spunRoulette(roundMovement);
          HUD.printRouletteResults();
          roundMovement = new Move();
          spunRoulette = true;
          betCompleted = false;
          printEnter(s);
          break;
          
        case 8:// Clear all Bets
          roundMovement = new Move();
          betCompleted = false;
          break;
          
        case 9:
          if (spunRoulette) {
            Roulette.calculateStatistics();
            HUD.printStatistics();
            printEnter(s);
          } else {
            System.out.println(HUD.showAsRed("No statistics generated"));
            printEnter(s);
          }
          break;
          
        case 10:
          Player.restartGame();
          System.out.println(HUD.showAsRed("Money has been reset"));
          printEnter(s);
          break;
          
        case 11:// End game
          System.out.println(ConsoleColors.CYAN + "Goodbye");
          break;
          
        default:
          System.err.println(HUD.showAsRed("Error in the input option"));
          printEnter(s);
          
      }
      HUD.clearScreen();
    } while (option != 11);
  }

  private static void printEnter(Scanner s) {
    System.out.print(ConsoleColors.CYAN_CUSTOM + "Press Enter to continue" + ConsoleColors.RESET);
    s.nextLine();
  }


  //If dni is set to the player...
  private static boolean dniExists() {
    Scanner s = new Scanner(System.in);

    int count = DNI_ATTEMPTS;

    do {// User have n attemps to input a valid DNI

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
    System.out.printf("%90s", "We have created a DNI for you.");
    Player.setDni(Player.generateRandomDni());
  }

  /**
   * This function request a type of bet to the player, validate this type and returns it 
   */

  public static BetTypes insertChoice(EnumSet<BetTypes> betType) { 
    Scanner s = new Scanner(System.in);
    String choice;
    
    System.out.print("Input the type " + betType + ":");
    choice = s.nextLine().toUpperCase(); //"red"

    do {
      if (!betTypeContainsChoice(betType, choice)) {
        System.out.printf(HUD.showAsRed("%s"), "\nPlease insert" + betType + ":");
        choice = s.nextLine().toUpperCase();
      }
    } while (!betTypeContainsChoice(betType, choice));
 
    return BetTypes.valueOf(choice); // 
  }
  
  /**
   * Check if String is in EnumSet 
   * E.g: EnumSet colors: RED,BLACK 
   * Get RED.toString() and compare with choice 
   */
  private static boolean betTypeContainsChoice(EnumSet<BetTypes> betType, String choice) {
    return betType.stream().anyMatch(enumType -> enumType.toString().equals(choice));
  }

  /**
   * Print roulette menu and return a chosen option
   * We think Menu class is generic, and it shouldnt manage colours
   */

  static int rouletteMenu() {
    Menu roulette = new Menu(ConsoleColors.CYAN_CUSTOM + "--ROULETTE MENU--" + ConsoleColors.RESET,
        HUD.showAsGreen("Color bet"),
        HUD.showAsGreen("Even or odd bet"),
        HUD.showAsGreen("Higher or lower bet"),
        HUD.showAsGreen("Dozen bet"),
        HUD.showAsGreen("Line bet"),
        HUD.showAsGreen("Number bet"),
        HUD.showAsGreen("Spin roulette"),
        HUD.showAsGreen("Clear bets"),
        HUD.showAsGreen("Statistics"),
        HUD.showAsGreen("Reset Game"),
        ConsoleColors.RED + "End game" + ConsoleColors.RESET) ;
    roulette.showMenu();
    int option = roulette.selectOption(ConsoleColors.CYAN_CUSTOM + "\n\nChoose an option");
    System.out.println(ConsoleColors.RESET);
    return option;
   
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
      System.out.print(ConsoleColors.YELLOW_BOLD + "Please insert the amount to bet: ");
      moneyBetted = getValidNumber(); // Checks if its a number
      try {
        roundMovement.betMoney(moneyBetted); // Checks if you have enough money or input is negative
        invalid = false;
      } catch (NoMoneyException | NegativeException moneyException) {
        System.err.println(HUD.showAsRed(moneyException.toString()));
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

  public static int getValidNumber() {
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
        System.err.print(HUD.showAsRed("Invalid input.\nInsert number again:"));
        // Advances the scanner to prevent input errors
        s.nextLine();
      }
    } while (invalid); // The loop ends when the input is valid
    return numToValidate;
  }
  
  public static int insertNumber() {
    int number;
    do {
      System.out.println("Insert a number: ");
      number=getValidNumber();
      if(number<0 || number>36) {
        System.out.println(ConsoleColors.RED+"Numero incorrecto"+ConsoleColors.RESET);
      }
      
    }while(number<0 || number>36);
    return number;
  }
}
