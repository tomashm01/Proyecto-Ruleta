package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Move { // A move is a set of bets Ex. [RED(50),EVEN(40),HIGH(20)]
  // This move is compared with the winning Number.

  // Bets made previously
  private static List<ArrayList<Bet>> allBets = new ArrayList<ArrayList<Bet>>();
  // balances calculated previously
  private static List<Integer> allBalances = new ArrayList<>();

  private List<Bet> currentBets = new ArrayList<>();

  private int balance; // Money calculated after roulette spun.
  private int moneyAtStake;

  // Getters & Setters
  public static List<ArrayList<Bet>> getAllBets() {
    return allBets;
  }
  
  public void setMoneyAtStake() {
    //Money at stake is money you have on your current Bets (before Roulette is spun)
    this.moneyAtStake = currentBets.stream().mapToInt(bets -> bets.getAmount()).sum();
  }
  
  public int getMoneyAtStake() {
    return moneyAtStake;
  }
  
  public void setAllBets() {
    allBets.add((ArrayList<Bet>) currentBets);
  }

  public List<Bet> getCurrentBets() {
    return currentBets;
  }

  public void setFinalBalance(WinningNumber newWinningNumber) {
    this.balance = Roulette.calculateProfit(this, newWinningNumber) - moneyAtStake;
    setAllBets();
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
   * This function is used when we need to overwrite a bet of the same type (Example: Red and Black are the same Type) Firstly
   * we remove a previous bet if his type is the same as the new bet Finally we add the new bet
   * 
   * @param String
   * @param int
   * 
   */
  
  public void addBet(String choice, int moneyBetted) {
    removeBetInCurrentBets(choice);
    currentBets.add(new Bet(choice, moneyBetted)); // Add the new bet
    setMoneyAtStake();
  }
  
  /**
   * This function allows to remove the currents bets 
   * 
   * @param String
   * 
   */
  
  private void removeBetInCurrentBets(String choice) {
    // We need an iterator to remove matching bets from currentBets
    Iterator<Bet> iterator = currentBets.iterator(); 

    while (iterator.hasNext()) {
      Bet currentBet = (Bet) iterator.next(); // Get bets from currentBets to compare
      for (String[] betType : Arrays.asList(Bet.POSSIBLE_BET_TYPES)) {
        // Loop each bet Type(Ex:[RED,BLACK],[EVEN,ODD]...)
        if (Arrays.asList(betType).contains(currentBet.getType())
            && Arrays.asList(betType).contains(choice)) {
          // If [Red,Black] was on currentBets and new bet(choice)
          iterator.remove();
          // Remove it from currentBets
        }
      }
    }

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


