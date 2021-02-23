package project;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Collections;
import java.util.List;

public class HUD {
  
  private static final int RANDOMNUMBER_REFRESH_RATE = 200;

  public static void printBetsInfo(Move roundMovement) {
    System.out.print(ConsoleColors.RESET);
    System.out.printf("%89s:%s\n ", "MONEY IN GAME", roundMovement.getMoneyAtStake());

    System.out.printf("%88s: ", "Current Bets");
    System.out.println(roundMovement.getCurrentBets());
  }

  public static void printPlayerInfo() {
    System.out.printf(ConsoleColors.PURPLE + "\n%82s:%d\n", "MONEY", Player.getMoney());
    System.out.printf("%80s:%s\n " + ConsoleColors.RESET, "DNI", Player.getDni());
  }

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

    System.out.printf(ConsoleColors.CYAN_BOLD + "\n%45s", "NEW NUMBER: ");
    printRandomNumbers();

    printTrulyWinningNumber();
    System.out.printf("\n%42s", "NUMBERS->");

    List<WinningNumber> reverse = new ArrayList<WinningNumber>(Roulette.getAllWinningNumbers());
    Collections.reverse(reverse);
    if (reverse.size() > 10) {
      reverse.removeIf(bola -> reverse.indexOf(bola) > 10);
    }
    reverse.forEach(bola -> System.out.print(bola.getNumber() + " "));
  }

  private static void printTrulyWinningNumber() {
    printNumberWithColors(getLastWinningNumber());    
  }

  private static void printNumberWithColors(WinningNumber someWinningNumber) {
   
    if (someWinningNumber.getColor().equals("RED")) {
      System.out.print(ConsoleColors.RESET);
      if (twoDigits(someWinningNumber)) {
        System.out.print(ConsoleColors.RED + "|" + someWinningNumber.getNumber() + "|" + ConsoleColors.RESET);
      }else {
        oneDigitPrint(someWinningNumber);
      }
     
    } else if (someWinningNumber.getColor().equals("BLACK")) {
      if ( twoDigits(someWinningNumber) ) {
        System.out
        .print(ConsoleColors.BLACK_BOLD + "|" + someWinningNumber.getNumber() + "|" + ConsoleColors.RESET);
      }else {
        oneDigitPrint(someWinningNumber);
      }
     
    }
  }

  private static void oneDigitPrint(WinningNumber someWinningNumber) {
    System.out.print(ConsoleColors.RED + "|" + someWinningNumber.getNumber() + " |" + ConsoleColors.RESET);
    System.out.print("\b");
  }

  private static boolean twoDigits(WinningNumber someWinningNumber) {
    return String.valueOf(someWinningNumber.getNumber()).length() == 2;
  }

  private static void printRandomNumbers() {
    
    for (int i = 0; i < 10; i++) {
      WinningNumber random = new WinningNumber();
      printNumberWithColors(random);
      if (twoDigits(random)) {
        System.out.print("\b\b\b\b");// Borra el anterior
      }else {
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

  private static WinningNumber getLastWinningNumber() {
    return Roulette.getAllWinningNumbers().get(Roulette.getAllWinningNumbers().size() - 1);
  }

  public static void printWinningNumberResults() {
    System.out.printf("\n%41s", "Results:");
    System.out.println(getLastWinningNumber().getResults());
  }

  public static void printSuccessfulBet() {
    System.out.printf("%46s", "Winning bets:");
    if (lastSuccessfulBet().size() == 0) {
      System.out.println("You did not make any moves");
    } else {
      System.out.println(lastSuccessfulBet());
    }
  }

  private static ArrayList<Bet> lastSuccessfulBet() {
    return Roulette.getAllWonBets().get(Roulette.getAllWonBets().size() - 1);
  }

  public static void printBets() {
    System.out.printf("%48s", "Your last bets:");
    if (getLastBets().size() == 0) {
      System.out.println("You did not make any moves");
    } else {
      System.out.println(getLastBets());
    }
  }

  private static ArrayList<Bet> getLastBets() {
    return Move.getAllBets().get(Move.getAllBets().size() - 1);
  }

  public static void printBalanceRoll() {
    System.out.printf("%53s:", "Balance of this roll");
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

  private static Integer getLastBlance() {
    return Move.getAllBalances().get(Move.getAllBalances().size() - 1);
  }

  /*
   * This function print the type of bet
   */
  public static void printInputType(String possibleChoice1, String possibleChoice2) {
    System.out.printf("%s", ConsoleColors.PURPLE + "Input the type");
    System.out.print(" (" + possibleChoice1 + " | " + possibleChoice2 + "):");
  }

  public static void printStatistics() {
    Roulette.getStatistics().forEach((key, value) -> System.out.println(key + " : " + value));
  }

}

