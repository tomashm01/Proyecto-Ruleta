package project;
/**
 * Authors: Jesús Díaz, Tomás Hidalgo
 * Where we calculate benefits from Roulette to set money at user according to it Move.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class Roulette {
  private static HashMap<BetTypes, String> statistics = new HashMap<BetTypes, String>();
  
  private static List<ArrayList<Bet>> allWonBets = new ArrayList<ArrayList<Bet>>();
  private static List<WinningNumber> allWinningNumbers = new ArrayList<WinningNumber>();

  //Setters and Getters
  public static List<WinningNumber> getAllWinningNumbers() {
    return allWinningNumbers;
  }
  private static void setAllWinningNumbers(WinningNumber lastWinningNumber) {
    allWinningNumbers.add(lastWinningNumber);
  } 
  public static void spunRoulette(Move playerMove) {
    // Create a random number
    WinningNumber randomWinningNumber = new WinningNumber();
    playerMove.setFinalBalance(randomWinningNumber);
    Player.setFinalMoney(playerMove);
    setAllWinningNumbers(randomWinningNumber);
  }

  public static int calculateProfit(Move playerMove, WinningNumber myWinningNumber) {
    Integer totalProfit = 0;
    totalProfit += calculateSimpleBetsProfit(playerMove, myWinningNumber);
    totalProfit += calculateDozenLineBetsProfit(playerMove, myWinningNumber);
    return totalProfit; 
  }
  private static Integer calculateDozenLineBetsProfit(Move playerMove,
      WinningNumber myWinningNumber) {
    int profit;
    EnumSet<BetTypes> dozenLineBet = EnumSet.range(BetTypes.DOZEN1,BetTypes.LINE3);
    dozenLineBet.retainAll(myWinningNumber.getResults()); 
    dozenLineBet.retainAll(playerMove.getCurrentBets().keySet());//dozenLineBet -> BT.DOZEN1
    profit = dozenLineBet.stream().mapToInt(wonBet-> playerMove.getCurrentBets().get(wonBet)*3).sum();
    return profit;
  }
  private static Integer calculateSimpleBetsProfit(Move playerMove, WinningNumber myWinningNumber) {
    int profit;
    EnumSet<BetTypes> simpleBet = EnumSet.range(BetTypes.RED,BetTypes.LOW);
    simpleBet.retainAll(myWinningNumber.getResults()); //Obtengo todas las apuestas 1to1 que han salido
    simpleBet.retainAll(playerMove.getCurrentBets().keySet()); //Intersección con las apostadas
    profit = simpleBet.stream().mapToInt(wonBet-> playerMove.getCurrentBets().get(wonBet)*2).sum();
    return profit;
  }


  private static ArrayList<Double> getProbabilities() {
    // Create Atomic Integers to count inside a lambda
    AtomicInteger redCount = new AtomicInteger();
    AtomicInteger evenCount = new AtomicInteger();
    AtomicInteger greenCount = new AtomicInteger();
    AtomicInteger highCount = new AtomicInteger();
    AtomicInteger line1Count = new AtomicInteger();
    AtomicInteger line2Count = new AtomicInteger();
    AtomicInteger dozen1Count = new AtomicInteger();
    AtomicInteger dozen2Count = new AtomicInteger();


    // iterate over allWiningNumbers to get properties from winningNumbers in order to increment each counter
    allWinningNumbers.stream().forEach(winningNumber -> {
      if (winningNumber.getColor().equals(BetTypes.GREEN)) {
        greenCount.getAndIncrement();
      }
      if (winningNumber.getColor().equals(BetTypes.RED)) {
        redCount.getAndIncrement();
      }
      if (winningNumber.getEvenOdd().equals(BetTypes.EVEN)) {
        evenCount.getAndIncrement();
      }
      if (winningNumber.getHighLow().equals(BetTypes.HIGH)) {
        highCount.getAndIncrement();
      }
      if (winningNumber.getLine().equals(BetTypes.LINE1)) {
        line1Count.getAndIncrement();
      }
      if (winningNumber.getLine().equals(BetTypes.LINE2)) {
        line2Count.getAndIncrement();
      }
      if (winningNumber.getDozen().equals(BetTypes.DOZEN1)) {
        dozen1Count.getAndIncrement();
      }
      if (winningNumber.getDozen().equals(BetTypes.DOZEN2)) {
        dozen2Count.getAndIncrement();
      }
    });
    
    
    
    // Transform atomic Integer to double probabilities and return list
    return new ArrayList<Double>(
        Arrays.asList(atomicToProbability(redCount), 1 - atomicToProbability(redCount),
            atomicToProbability(evenCount), 1 - atomicToProbability(evenCount),
            atomicToProbability(highCount), 1 - atomicToProbability(highCount),
            atomicToProbability(dozen1Count),atomicToProbability(dozen2Count),
            1 - (atomicToProbability(dozen1Count) - atomicToProbability(dozen2Count)),
            atomicToProbability(line1Count),atomicToProbability(line2Count),
            1 - (atomicToProbability(line1Count) - atomicToProbability(line2Count))));
  }

  public static List<ArrayList<Bet>> getAllWonBets() {
    return allWonBets;
  }

//  private static void setAllWonBets(List<Bet> apuestasGanadoras) {
//    allWonBets.add((ArrayList<Bet>) apuestasGanadoras);
//  }

  public static HashMap<BetTypes, String> getStatistics() {
    return statistics;
  }
  
  /**
   * This function calculate the stadistics of all the spins
   */
  
 public static void calculateStatistics() {
  statistics.clear();
   
   ArrayList<Double> odds = getProbabilities(); // Value (0.1, 0.n...) (Odds mean probabilities)
   
   fillStatistics(odds); // fill hashmap with keys and values
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
  
  public static void fillStatistics(ArrayList<Double> AllWinningNumbersProbabilities) {
    int j = 0;
    //Lo suyo seria tener una lista 
    Object[] betTypesList = EnumSet.allOf(BetTypes.class).toArray();
    
    while (j < AllWinningNumbersProbabilities.size()) {
      statistics.put( (BetTypes) betTypesList[j],
          String.format("%.2f%%", ((AllWinningNumbersProbabilities.get(j)) * 100)));
      j++;
    }
    
  }
  
}


