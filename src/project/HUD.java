package project;

public class HUD {

  /*
   * This function print the balance of the roll
   */
  
  public static void printfBalanceRoll(Integer profitRoll) {
    
    System.out.printf("%55s:", "Balance of this roll");
    if (profitRoll - Ruleta.getMoneyBetLastRoll() > 0) {
      System.out.println(ConsoleColors.GREEN + "(+" + (profitRoll - Ruleta.getMoneyBetLastRoll()) + ")"
          + ConsoleColors.RESET);
    } else {
      System.out.println(ConsoleColors.RED + "(" + (profitRoll - Ruleta.getMoneyBetLastRoll()) + ")"
          + ConsoleColors.RESET);
    }
  }
  
  /*
   * This function print the number on the roulette
   */
  
  public static void printBallNumber() {
    System.out.printf("%41s%d\n", "NUMBER ->",Ruleta.getBallNumber());

  }
  
  /*
   * This function prints the results of the ball
   */
  
  public static void printResults() {
    System.out.printf("%44s","Results:");
    System.out.println(Ruleta.getResultsLastRoll());
   
  }
  
  /*
   * This function prints all the successful bets
   */
  
  public static void printSucessfulBet() {

    System.out.printf("%56s","Winning bets:");
    if (Ruleta.getProfitRoll() == 0) {
      System.out.println(" Nothing :(");
    } else {
      System.out.println(Ruleta.getWinningBets().toString());
    }

  }
  
  /*
   * This function print the type of bet
   */
  
  public static void printInputType(String possibleChoice1, String possibleChoice2) {
    System.out.printf("%s", ConsoleColors.PURPLE + "Input the type");
    System.out.print(" (" + possibleChoice1 + " | " + possibleChoice2 + "):");
  }
  
}

