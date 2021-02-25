package project;

/**
 * Authors: Jesús Díaz, Tomás Hidalgo Auxiliary class to print data at Main class.
 */
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Collections;
import java.util.List;

public class HUD {

  private static final int RANDOMNUMBER_REFRESH_RATE = 200;

  // GETTERS

  private static WinningNumber getLastWinningNumber() {
    return Roulette.getAllWinningNumbers().get(Roulette.getAllWinningNumbers().size() - 1);
  }

  private static Integer getLastBlance() {
    return Move.getAllBalances().get(Move.getAllBalances().size() - 1);
  }

  private static ArrayList<Bet> getLastBets() {
    return Move.getAllBets().get(Move.getAllBets().size() - 1);
  }

  private static ArrayList<Bet> lastSuccessfulBet() {
    return Roulette.getAllWonBets().get(Roulette.getAllWonBets().size() - 1);
  }

  /**
   * This function print the info of the bet
   * 
   * @param Move
   * 
   */

  public static void printBetsInfo(Move roundMovement) {
    System.out.print(ConsoleColors.RESET);
    System.out.printf("%82s:%s\n ", "MONEY AT STAKE",
        ConsoleColors.YELLOW_BOLD + roundMovement.getMoneyAtStake() + ConsoleColors.RESET);

    System.out.printf("%80s: ", "Current Bets");
    System.out
        .println(ConsoleColors.YELLOW_BOLD + roundMovement.getCurrentBets() + ConsoleColors.RESET);
  }

  /**
   * This function print the money and the dni of the player
   */

  public static void printPlayerInfo() {
    System.out.printf(ConsoleColors.PURPLE_BOLD + "\n%81s:%d\n" + ConsoleColors.RESET,
        "MONEY" + ConsoleColors.YELLOW_BOLD, Player.getMoney());
    System.out.printf(ConsoleColors.PURPLE_BOLD + "%80s:%s\n ",
        "DNI" + ConsoleColors.RESET + ConsoleColors.BOLD_CUSTOM, Player.getDni());
  }

  /**
   * This function print the winning number, the results of the winning numbers (red or black, even
   * or odd, high or low), print the bets of the player, print the winning bets and the balance in
   * this roll
   */

  public static void printRouletteResults() {
    printWinningNumber();
    printWinningNumberResults();
    printBets();
    printSuccessfulBet();
    printBalanceRoll();
  }

  /*
   * This function print the number on the roulette
   */

  public static void printWinningNumber() {

    System.out.printf(ConsoleColors.CYAN_BOLD + "\n%45s", showAsCyan("NEW NUMBER: "));
    printRandomNumbers();

    printTrulyWinningNumber();
    System.out.printf("\n%42s", showAsCyan("NUMBERS->"));

    List<WinningNumber> reverse = new ArrayList<WinningNumber>(Roulette.getAllWinningNumbers());
    Collections.reverse(reverse);
    if (reverse.size() > 10) {
      reverse.removeIf(bola -> reverse.indexOf(bola) > 10);
    }
    reverse.forEach(bola -> System.out.print(bola.getNumber() + " "));
  }

  private static String showAsCyan(String message) {
    return ConsoleColors.CYAN_BOLD + message + ConsoleColors.RESET;
  }

  /*
   * This function prints the winning number
   */

  private static void printTrulyWinningNumber() {
    printNumberWithColors(getLastWinningNumber());
  }

  /**
   * This function print the numbers using ConsoleColors
   * 
   * @param WinningNumber
   * 
   */

  private static void printNumberWithColors(WinningNumber someWinningNumber) {

    if (someWinningNumber.getColor().equals("RED")) {
      System.out.print(ConsoleColors.RESET);
      if (twoDigits(someWinningNumber)) {
        System.out.print(
            ConsoleColors.RED + "|" + someWinningNumber.getNumber() + "|" + ConsoleColors.RESET);
      } else {
        oneDigitPrint(someWinningNumber);
      }

    } else if (someWinningNumber.getColor().equals("BLACK")) {
      if (twoDigits(someWinningNumber)) {
        System.out.print(ConsoleColors.BLACK_BOLD + "|" + someWinningNumber.getNumber() + "|"
            + ConsoleColors.RESET);
      } else {
        oneDigitPrint(someWinningNumber);
      }

    } else {
      System.out.print(
          ConsoleColors.GREEN + "|" + someWinningNumber.getNumber() + " |" + ConsoleColors.RESET);
      System.out.print("\b");
    }
  }

  /**
   * This function prints one number in red
   * 
   * @param WinningNumber
   * 
   */

  private static void oneDigitPrint(WinningNumber someWinningNumber) {
    if (someWinningNumber.getColor().equals("RED")) {
      System.out.print(
          ConsoleColors.RED + "|" + someWinningNumber.getNumber() + " |" + ConsoleColors.RESET);

    } else {
      System.out.print(
          ConsoleColors.BLACK + "|" + someWinningNumber.getNumber() + " |" + ConsoleColors.RESET);

    }
    System.out.print("\b");
  }

  /**
   * This function converts the winning number in String
   * 
   * @param WinningNumber
   * 
   */

  private static boolean twoDigits(WinningNumber someWinningNumber) {
    return String.valueOf(someWinningNumber.getNumber()).length() == 2;
  }

  /**
   * This function prints randoms numbers until the winning number comes out
   */

  private static void printRandomNumbers() {

    for (int i = 0; i < 10; i++) {
      WinningNumber random = new WinningNumber();
      printNumberWithColors(random);
      if (twoDigits(random)) {
        System.out.print("\b\b\b\b");// Erase the before number
      } else {
        System.out.print("\b\b\b");
      }
      try {
        TimeUnit.MILLISECONDS.sleep(RANDOMNUMBER_REFRESH_RATE);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  /**
   * This function prints the results of the winning numbers
   */

  public static void printWinningNumberResults() {
    System.out.printf("\n%41s", showAsCyan("Results:"));
    if (getLastWinningNumber().getNumber() == 0) {
      System.out.println("You lost everything");
    } else {
      System.out.println(ConsoleColors.WHITE_BACKGROUND_CUSTOM + getLastWinningNumber().getResults()
          + ConsoleColors.RESET);
    }
  }

  /**
   * This function print the bets which the player win
   */

  public static void printSuccessfulBet() {
    System.out.printf("%46s", showAsCyan("Winning bets:"));
    if (lastSuccessfulBet().size() == 0) {
      System.out.println("You won no bet");
    } else {
      System.out.println(ConsoleColors.YELLOW_BOLD + lastSuccessfulBet() + ConsoleColors.RESET);
    }
  }

  /**
   * This function prints the bets of the player
   */

  public static void printBets() {
    System.out.printf("%48s", showAsCyan("Your last bets:"));
    if (getLastBets().size() == 0) {
      System.out.println("You did not make any moves");

    } else {
      System.out
          .println(ConsoleColors.PINK_BACKGROUND_CUSTOM + getLastBets() + ConsoleColors.RESET);
    }
  }

  /**
   * This function print the balace in the roll
   */

  public static void printBalanceRoll() {
    System.out.printf("%53s:", showAsCyan("Balance of this roll"));
    if (getLastBlance() > 0) {
      System.out.print(ConsoleColors.GREEN);
      System.out.println("+" + getLastBlance());
      System.out.println(ConsoleColors.RESET);
    } else {
      System.out.print(ConsoleColors.RED);
      System.out.println(getLastBlance());
      System.out.println(ConsoleColors.RESET);
    }
  }

  /**
   * This function print the type of bet
   * 
   * @param String
   * @param String
   * 
   */

  public static void printInputType(String possibleChoice1, String possibleChoice2) {
    System.out.printf("%s", ConsoleColors.PURPLE_BOLD + "Input the type");
    System.out
        .print(ConsoleColors.CYAN_BOLD + " (" + possibleChoice1 + " | " + possibleChoice2 + "):");
  }

  /*
   * This function print the stadistics of the roulette
   */

  public static void printStatistics() {
    Roulette.getStatistics().forEach(
        (key, value) -> System.out.println(ConsoleColors.PURPLE_BOLD + key + ConsoleColors.RESET
            + " : " + ConsoleColors.BOLD_CUSTOM + value + ConsoleColors.RESET));
  }

  /**
   * This function show a message as error in console
   * 
   * @param error
   * 
   * @return error coloured
   * 
   */

  public static String showAsError(String error) {
    return ConsoleColors.RESET + ConsoleColors.RED + error + ConsoleColors.RESET;
  }

  /**
   * This function clear the terminal screen
   */

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}

