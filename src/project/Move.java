package project;
/**
 * Authors: Jesús Díaz, Tomás Hidalgo
 * Set of Bets before Roulette is spun.
 * We also stash all bets and balances made previously.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Move { // A move is a set of bets Ex. [RED(50),EVEN(40),HIGH(20)]
  // This move is compared with the winning Number.

  // Bets made previously
  private static List<ArrayList<Bet>> allBets = new ArrayList<ArrayList<Bet>>();
  // balances calculated previously
  private static List<Integer> allBalances = new ArrayList<>();

  private HashMap<BetTypes, Integer> currentBets = new HashMap<BetTypes, Integer>();
  private int balance; // Money calculated after roulette spun.
  private int moneyAtStake;

  // Getters & Setters
  public static List<ArrayList<Bet>> getAllBets() {
    return allBets;
  }
  
  public void setMoneyAtStake() {
    //Money at stake is money you have on your current Bets (before Roulette is spun)
    this.moneyAtStake = currentBets.values().stream().reduce(0, Integer::sum);
  }
  
  public int getMoneyAtStake() {
    return moneyAtStake;
  }
  
//  public void setAllBets() {
//    allBets.add((ArrayList<Bet>) currentBets);
//  }

  public HashMap<BetTypes, Integer> getCurrentBets() {
    return (HashMap<BetTypes, Integer>) currentBets;
  }

  public void setFinalBalance(WinningNumber newWinningNumber) {

    this.balance = Roulette.calculateProfit(this, newWinningNumber) - moneyAtStake;
//    setAllBets();
    setAllBalances();
  }

  public int getFinalBalance() {
    return this.balance;
  }

  public static List<Integer> getAllBalances() {
    return allBalances;
  }

  private void setAllBalances() {
    allBalances.add(this.balance);
  }

  /**
   * Add bet
   * 
   * @param String
   * @param int
   * 
   */
  
  public void addBet(BetTypes choice, int moneyBetted) {
    
    if (this.currentBets.containsKey(choice)){
      moneyBetted += currentBets.get(choice);
    }
    
    this.currentBets.put(choice, moneyBetted); // Add the new bet      
    setMoneyAtStake();
  }

  /**
   * 
   * This function throw exceptions if bet is not valid
   * 
   * @param bettedMoney
   * @throws NoMoneyException
   * @throws NegativeException
   * 
   */
  
  public void betMoney(int bettedMoney) throws NoMoneyException, NegativeException {
    if (bettedMoney < 0) {//User try to bet a negative bet
      throw new NegativeException("You can't input negative bets");
    }

    if (moneyAtStake + bettedMoney > Player.getMoney()) {//User try to bet more money than he has
      throw new NoMoneyException("You have not enough money to bet for this.");
    }
  }
  
}


