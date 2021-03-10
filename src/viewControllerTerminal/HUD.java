package viewControllerTerminal;

/**
 * Authors: Jesús Díaz, Tomás Hidalgo Auxiliary class to print data at Main class.
 */
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import bets.Bet;
import bets.BetTypes;
import player.Move;
import player.Player;
import project.Roulette;
import project.WinningNumber;
import java.util.Collections;
import java.util.List;

public class HUD {

  private static final int MAXIMUM_NUMBERS_PRINT = 10;
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
    return Roulette.getAllSuccessfulBets().get(Roulette.getAllSuccessfulBets().size() - 1);
  }

  /*
   * Print current Bets
   */
  public static void printBetsInfo(Move roundMovement) {
    System.out.print(ConsoleColors.RESET);
    System.out.printf("%82s:%s\n ", "MONEY AT STAKE",
        ConsoleColors.YELLOW_BOLD + roundMovement.getMoneyAtStake() + ConsoleColors.RESET);

    System.out.printf("%80s:\n", "Current Bets");

    roundMovement.getCurrentBets().forEach(
        bet -> System.out.printf(ConsoleColors.YELLOW_BOLD + "%85s\n", bet + ConsoleColors.RESET));

  }

  //Print money and dni
  public static void printPlayerInfo() {
    System.out.printf(ConsoleColors.PURPLE_BOLD + "\n%81s:%d\n" + ConsoleColors.RESET,
        "MONEY" + ConsoleColors.YELLOW_BOLD, Player.getMoney());
    System.out.printf(ConsoleColors.PURPLE_BOLD + "%80s:%s\n ",
        "DNI" + ConsoleColors.RESET + ConsoleColors.BOLD_CUSTOM, Player.getDni());
  }

  /**
   * This function print: winning number, results of the winning numbers (red or black, even or odd,
   * high or low), bets of the player, winning bets and the balance on this roll
   */

  public static void printRouletteResults() {
    printWinningNumber();
    printWinningNumberResults();
    printPreviousBets();
    printSuccessfulBet();
    printRollBalance();
  }

  public static void printWinningNumber() {

    System.out.printf(ConsoleColors.CYAN_BOLD + "\n%45s", showAsCyan("NEW NUMBER: "));
    printRandomNumbers();

    printTrulyWinningNumber();
    System.out.printf("\n%42s", showAsCyan("NUMBERS->"));

    List<WinningNumber> reverse = new ArrayList<WinningNumber>(Roulette.getAllWinningNumbers());
    Collections.reverse(reverse);
    if (reverse.size() > MAXIMUM_NUMBERS_PRINT) {
      reverse.removeIf(ball -> reverse.indexOf(ball) > MAXIMUM_NUMBERS_PRINT);
    }
    reverse.forEach(ball -> System.out.print(ball.getNumber() + " "));
  }

  private static String showAsCyan(String message) {
    return ConsoleColors.CYAN_BOLD + message + ConsoleColors.RESET;
  }

  private static void printTrulyWinningNumber() {
    printNumberWithColors(getLastWinningNumber());
  }

  private static void printNumberWithColors(WinningNumber someWinningNumber) {

    if (someWinningNumber.isRed()) {
      System.out.print(ConsoleColors.RESET);
      if (twoDigits(someWinningNumber)) {
        System.out.print(
            ConsoleColors.RED + "|" + someWinningNumber.getNumber() + "|" + ConsoleColors.RESET);
      } else {
        oneDigitPrint(someWinningNumber);
      }
    } else if (someWinningNumber.getNumber() == 0) {
      System.out.print(
          ConsoleColors.GREEN + "|" + someWinningNumber.getNumber() + " |" + ConsoleColors.RESET);
      System.out.print("\b");
    } else {
      if (twoDigits(someWinningNumber)) {
        System.out.print(ConsoleColors.BLACK_BOLD + "|" + someWinningNumber.getNumber() + "|"
            + ConsoleColors.RESET);
      } else {
        oneDigitPrint(someWinningNumber);
      }
    }

  }

  private static void oneDigitPrint(WinningNumber someWinningNumber) {
    if (someWinningNumber.getColor().equals(BetTypes.RED)) {
      System.out.print(
          ConsoleColors.RED + "|" + someWinningNumber.getNumber() + " |" + ConsoleColors.RESET);

    } else {
      System.out.print(
          ConsoleColors.BLACK + "|" + someWinningNumber.getNumber() + " |" + ConsoleColors.RESET);

    }
    System.out.print("\b");
  }

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
        System.err.println("Wrong refresh rate");
      }
    }
  }

  public static void printWinningNumberResults() {
    System.out.printf("\n%41s", showAsCyan("Results:"));
    if (getLastWinningNumber().getColor().equals(BetTypes.GREEN)) {
      System.out.println("GREEN");
    }else {
      System.out.println(ConsoleColors.WHITE_BACKGROUND_CUSTOM + getLastWinningNumber().getResults()
          + ConsoleColors.RESET);
    }
  
  }

  public static void printSuccessfulBet() {
    System.out.printf("%46s", showAsCyan("Winning bets:"));
    if (lastSuccessfulBet().isEmpty()) {
      System.out.println("You won no bet");
    } else {
      System.out.println(ConsoleColors.YELLOW_BOLD + lastSuccessfulBet() + ConsoleColors.RESET);
    }
  }

  public static void printPreviousBets() {
    System.out.printf("%48s", showAsCyan("Your last bets:"));
    if (getLastBets().isEmpty()) {
      System.out.println("You didn't bet");
    } else {
      System.out
          .println(ConsoleColors.PINK_BACKGROUND_CUSTOM + getLastBets() + ConsoleColors.RESET);
    }
  }

  public static void printRollBalance() {
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

  public static void printStatistics() {
    Roulette.getStatistics().forEach(
        (key, value) -> System.out.println(ConsoleColors.PURPLE_BOLD + key + ConsoleColors.RESET
            + " : " + ConsoleColors.BOLD_CUSTOM + value + ConsoleColors.RESET));
  }

  public static String showAsRed(String message) {
    return ConsoleColors.RESET + ConsoleColors.RED + message + ConsoleColors.RESET;
  }

  public static String showAsGreen(String message) {
    return ConsoleColors.GREEN + message + ConsoleColors.RESET;
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}

