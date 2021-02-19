package project;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ruleta {

  private static Set<Apuesta> winningResults = new HashSet<>(); //Results of the ball which are winner
  private static Integer profitRoll; 
  private static int ballNumber;
  private static Set<Apuesta> winningBets; //Winning bets of the user
  private static String resultsLastRoll;
  private static Integer moneyBetLastRoll;

  //Getters
  public static int getProfitRoll() {
    return profitRoll;
  }

  public static String getResultsLastRoll() {
    return resultsLastRoll;
  }
  
  public static int getBallNumber() {
    return ballNumber;
  }

  public static Set<Apuesta> getWinningResults() {
    return winningResults;
  }

  public static Set<Apuesta> getWinningBets() {
    return winningBets;
  }
  
  public static int getMoneyBetLastRoll() {
    return moneyBetLastRoll;
  }

  //Setters
  
  public static void setMoneyBetLastRoll() {
    moneyBetLastRoll = Jugador.playerBets.stream().mapToInt(apuesta -> apuesta.getAmount()).sum();
  }

  /*
   * This function calculate the total profit 
   *
   */
  
  public static void calculateProfit() {

    profitRoll = 0;

    winningBets = new HashSet<Apuesta>(Jugador.playerBets);

    winningBets.retainAll(winningResults); // intersection between the resulting bets and the bets that the user has won
                                                   
    profitRoll = winningBets.stream()
        .mapToInt(winningBets -> winningBets.getAmount() * 2).sum();

  }

  /*
   * This function clear the ball results and the bets of the player
   */
  
  private static void restarRoll() {
    winningResults.clear();
    Jugador.restartRound();
  }
  
  /*
   * This function generate a random value for the ball between 0-36
   * 
   * @return int
   */
  
  static int randomBall() {
    int random = (int) (0 + Math.random() * (36 - 0) + 1);
    return random;
  }

  /*
   * This function returns the color of the number
   * 
   * @param n
   * 
   * @return String
   */
  
  static String colorBall(int n) {
    String val = "";
    int size = 18;
    int red[] = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
    int black[] = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
    if (n == 0) {
      val = "GREEN";
      return val;
    }
    for (int i = 0; i < size; i++) {
      if (n == red[i]) {
        val = "RED";
        return val;
      } else if (n == black[i]) {
        val = "BLACK";
        return val;
      }
    }
    return val;
  }

  /*
   * This function return if the number is Even or Odd
   * 
   * @param n
   * 
   * @return String
   */
  
  static String EvenOddBall(int n) {
    String val = "";
    if (n == 0) {
      val = "NULL";
      return val;
    } else if (n % 2 == 0) {
      val = "EVEN";
      return val;
    } else {
      val = "ODD";
      return val;
    }
  }

  /*
   * This function return if the number is between 1-18, 19-36 or is 0.
   * 
   * @param n
   * 
   * @return
   */
  
  static String HigherLowerThan(int n) {
    String val = "";
    if (n == 0) {
      val = "NULL";
      return val;
    } else if (n < 19) {
      val = "LOW";
      return val;
    } else if (n > 18) {
      val = "HIGH";
      return val;
    }
    return val;
  }
  
  /*
   * This function throw the ball
   */
  
  public static void pushRoulette() {

    ballNumber = Ruleta.randomBall();

    winningResults.addAll(List.of(new Apuesta(Ruleta.colorBall(ballNumber)),
        new Apuesta(Ruleta.EvenOddBall(ballNumber)),
        new Apuesta(Ruleta.HigherLowerThan(ballNumber))));

    Jugador.setFinalMoney();
    resultsLastRoll = winningResults.toString();
    setMoneyBetLastRoll();
    restarRoll();

  }
  
}


