package roulette;

/**
 * Authors: Jesús Díaz, Tomás Hidalgo Where we calculate benefits from Roulette to set money at user
 * according to it Move.
 */
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import bets.Bet;
import bets.BetTypes;
import player.Move;
import player.Player;

public class Roulette {
  private static HashMap<BetTypes, Double> statistics = new HashMap<BetTypes, Double>();
  private static List<WinningNumber> allWinningNumbers = new ArrayList<WinningNumber>();
  private static List<ArrayList<Bet>> allSuccessfulBets = new ArrayList<ArrayList<Bet>>();
  
  // Setters and Getters
  public static List<WinningNumber> getAllWinningNumbers() {
    return allWinningNumbers;
  }

  private static void setAllWinningNumbers(WinningNumber lastWinningNumber) {
    allWinningNumbers.add(lastWinningNumber);
  }
  
  public static List<ArrayList<Bet>> getAllSuccessfulBets() {
    return allSuccessfulBets;
  }
  
  public static HashMap<BetTypes, Double> getStatistics() {
    return statistics;
  }

  public static void spunRoulette(Move playerMove) {
    // Create a random number
    WinningNumber randomWinningNumber = new WinningNumber();
    playerMove.setFinalBalance(randomWinningNumber);
    Player.setFinalMoney(playerMove);
    setAllWinningNumbers(randomWinningNumber);
  }

  public static int calculateProfit(Move playerMove, WinningNumber myWinningNumber) {
    int profit = 0;
    ArrayList<Bet> successfulBets = new ArrayList<Bet>();
    //Profit from Not number bets
    for (Bet playerBet : playerMove.getCurrentBets()) {
      for (BetTypes result : myWinningNumber.getResults()) {
        if (playerBet.equals(new Bet(result))) {
          profit += playerBet.getAmount() * playerBet.getOdd();
          successfulBets.add(playerBet);
        }
      }
    }
  
    //Profit from Numbers bets
    if (playerMove.getCurrentBets().contains(new Bet(myWinningNumber.getNumber()))) {
      Bet winningNumberBet = getWinningNumberBet(playerMove, myWinningNumber);
      profit += winningNumberBet.getAmount() * winningNumberBet.getOdd();
      successfulBets.add(winningNumberBet);
    }
    //Maybe this could break SRP but makes our program more efficient.
    allSuccessfulBets.add(successfulBets);
    return profit;
  }

  private static Bet getWinningNumberBet(Move playerMove, WinningNumber myWinningNumber) {
    return playerMove.getCurrentBets().get(playerMove.getCurrentBets().indexOf(new Bet(myWinningNumber.getNumber())));
  }

  public static void calculateStatistics() {
    //We will filter all zero's, so we will need a backup.
    List<WinningNumber>allWinningNumbersBackup = new ArrayList<WinningNumber>(allWinningNumbers);
    allWinningNumbers = allWinningNumbers.stream().filter(number -> !number.getColor().equals(BetTypes.GREEN)).collect(Collectors.toList());
    
    Long redCount = allWinningNumbers.stream().filter(number -> number.getColor().equals(BetTypes.RED)).count();
    Long evenCount = allWinningNumbers.stream().filter(number -> number.getEvenOdd().equals(BetTypes.EVEN)).count();
    Long highCount = allWinningNumbers.stream().filter(number -> number.getHighLow().equals(BetTypes.HIGH)).count();
    
    Object[] betTypesList = EnumSet.range(BetTypes.RED, BetTypes.LOW).toArray();
    
    double[] probabilities = {
        getProbability(redCount),getOpositeProbability(redCount),
        getProbability(evenCount),getOpositeProbability(evenCount),
        getProbability(highCount),getOpositeProbability(highCount)
    };
    
    fillStatistics(betTypesList, probabilities);
    allWinningNumbers = allWinningNumbersBackup;
  }

  private static void fillStatistics(Object[] betTypesList, double[] probabilities) {
    for (int i = 0; i<betTypesList.length; i++) {
      statistics.put((BetTypes) betTypesList[i],probabilities[i]);
    }
  }

  private static double getProbability(Long counter) {    
    return (double) counter / allWinningNumbers.size();
  }

  private static double getOpositeProbability(Long counter) {
    return 1 - getProbability(counter);
  }
}


