package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class Roulette {
  private static HashMap<String, String> statistics = new HashMap<String, String>();
  private static List<ArrayList<Bet>> allWonBets = new ArrayList<ArrayList<Bet>>();
  private static List<WinningNumber> allWinningNumbers = new ArrayList<WinningNumber>();

  //Setters and Getters
  
  public static List<WinningNumber> getAllWinningNumbers() {
    return allWinningNumbers;
  }

  private static void setAllWinningNumbers(WinningNumber lastWinningNumber) {
    allWinningNumbers.add(lastWinningNumber);
  }

  private static ArrayList<Double> getProbabilities() {
    // Create Atomic Integers to count inside a lambda
    AtomicInteger redCount = new AtomicInteger();
    AtomicInteger evenCount = new AtomicInteger();
    AtomicInteger highCount = new AtomicInteger();
    // iterate over allWiningNumbers to get properties from winningNumbers in order to increment each counter
    allWinningNumbers.stream().forEach(winningNumber -> {
      if (winningNumber.getColor().equals("RED")) {
        redCount.getAndIncrement();
      }
      if (winningNumber.getEvenOdd().equals("EVEN")) {
        evenCount.getAndIncrement();
      }
      if (winningNumber.getHighLow().equals("HIGH")) {
        highCount.getAndIncrement();
      }
    });
    // Transform atomic Integer to double probabilities and return list
    return new ArrayList<Double>(
        Arrays.asList(atomicToProbability(redCount), 1 - atomicToProbability(redCount),
            atomicToProbability(evenCount), 1 - atomicToProbability(evenCount),
            atomicToProbability(highCount), 1 - atomicToProbability(highCount)));
  }

  public static List<ArrayList<Bet>> getAllWonBets() {
    return allWonBets;
  }

  private static Bet buildWonBets(Move playerMove, String successfulBetType) {
    return playerMove.getCurrentBets()
        .get(playerMove.getCurrentBets().indexOf(new Bet(successfulBetType)));
  }

  private static void setAllWonBets(List<Bet> apuestasGanadoras) {
    allWonBets.add((ArrayList<Bet>) apuestasGanadoras);
  }

  public static HashMap<String, String> getStatistics() {
    return statistics;
  }
  
  /**
   * This function spin the roulette
   * 
   * @param playerMove
   */
  
  public static void spunRoulette(Move playerMove) {
    // Create a random number
    WinningNumber randomWinningNumber = new WinningNumber();
    playerMove.setFinalBalance(randomWinningNumber);
    Player.setFinalMoney(playerMove);
    setAllWinningNumbers(randomWinningNumber);
  }

  /**
   * This function calculate the profit int this roll for the player
   * 
   * @param playerMove
   * @param myWinningNumber
   * 
   * @return int
   * 
   */
  public static int calculateProfit(Move playerMove, WinningNumber myWinningNumber) {

    ArrayList<Bet> successfulBets = getSuccessfulBets(playerMove, myWinningNumber);

    setAllWonBets(successfulBets); // Store every successfulBet

    return successfulBets.stream().mapToInt(winningBets -> winningBets.getAmount() * 2).sum();
    // Return profits from successfulBets

  }
  /**
   * This function put the bets that the user win in the ArrayList Bet and returns it
   * 
   * @param playerMove
   * @param myWinningNumber
   * 
   * @return ArrayList
   */
  
  private static ArrayList<Bet> getSuccessfulBets(Move playerMove, WinningNumber myWinningNumber) {
    // successfulBetTypes get the values from winningNumber as list<String>
    List<String> successfulBetTypes = new ArrayList<String>(myWinningNumber.getResults());
    // Now it retain with List of bets from user
    successfulBetTypes.retainAll(currentBetsToString(playerMove));// retain of two List<String>

    // We need to transform ArrayList<String> to ArrayList<Bet> in order to get the amount of each
    // bet.
    ArrayList<Bet> successfulBets = (ArrayList<Bet>) successfulBetTypes.stream()
        .map(successfulBetType -> buildWonBets(playerMove, successfulBetType))
        .collect(Collectors.toList());
    return successfulBets;
  }

  /**
   * This function put the bet of the user in the ArrayList
   * 
   * @param playerMove
   * 
   * @return List of bets done by the user (String)
   * 
   */
  
  private static List<String> currentBetsToString(Move playerMove) {
    return playerMove.getCurrentBets().stream().map(bet -> bet.getType())
        .collect(Collectors.toList());
  }
  
  /**
   * This function calculate the stadistics of all the spins
   */
  
  public static void calculateStatistics() {
    statistics.clear();
    List<String> betTypes = transformPossibleBetTypesToList(); // Key hashmap {"RED","BLACK"...}
    ArrayList<Double> odds = getProbabilities(); // Value (0.1, 0.n...) (Odds mean probabilities)
    fillStatistics(betTypes, odds); // fill hashmap with keys and values
  }
  
  /**
   * This function can transform 2D array into 1D list in order to get everything easier
   */
  
  private static List<String> transformPossibleBetTypesToList() {
    return Arrays.asList(Bet.POSSIBLE_BET_TYPES).stream().flatMap(x -> Arrays.asList(x).stream())
        .collect(Collectors.toList());
  }
  
  private static double atomicToProbability(AtomicInteger count) {
    return count.doubleValue() / allWinningNumbers.size(); // Transform AtomicInteger to probability
  }
  
  /**
   * This function need to get every type {"RED","BLACK"...} to fill every hashmap key (betTypeList) We need to
   * get every probability {0.1,0.9...} to fill every hashmap value (AllWinningNumbersProbabilities)
   * 
   * @param betTypeList
   * @param AllWinningNumbersProbabilities
   */
  
  private static void fillStatistics(List<String> betTypeList,
      ArrayList<Double> AllWinningNumbersProbabilities) {
    int j = 0;
    while (j < AllWinningNumbersProbabilities.size()) {
      statistics.put(betTypeList.get(j),
          String.format("%.2f%%", ((AllWinningNumbersProbabilities.get(j)) * 100)));
      j++;
    }
  }
}


