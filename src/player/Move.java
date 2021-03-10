package player;

/**
 * Authors: Jesús Díaz, Tomás Hidalgo Set of Bets before Roulette is spun. We also stash all bets
 * and balances made previously.
 */
import java.util.ArrayList;
import java.util.List;
import Exceptions.NegativeException;
import Exceptions.NoMoneyException;
import bets.Bet;
import static project.Roulette.calculateProfit;
import project.WinningNumber;

public class Move { // A move is a set of bets Ex. [RED(50),EVEN(40),HIGH(20)]
  // This move is compared with the winning Number.
  List<Bet> currentBets = new ArrayList<Bet>();
  // Bets made previously
  private static List<ArrayList<Bet>> allBets = new ArrayList<ArrayList<Bet>>();
  // balances calculated previously
  private static List<Integer> allBalances = new ArrayList<>();

  // private HashMap<BetTypes, Integer> currentBets = new HashMap<BetTypes, Integer>();
  private int balance; // Money calculated after roulette spun.
  private int moneyAtStake;

  // Getters & Setters
  public static List<ArrayList<Bet>> getAllBets() {
    return allBets;
  }

  public void setMoneyAtStake() {
    // Money at stake is money you have on your current Bets (before Roulette is spun)
    this.moneyAtStake = currentBets.stream().mapToInt(bet -> bet.getAmount()).sum();
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
    this.balance = calculateProfit(this, newWinningNumber) - moneyAtStake;
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

  public void addBet(Bet myBet) {

    if (currentBets.contains(myBet)) {
      myBet.setAmount(myBet.getAmount() + currentBets.get(getBetFromCurrentBets(myBet)).getAmount());
      currentBets.set(getBetFromCurrentBets(myBet), myBet);
    } else {
      currentBets.add(myBet);
    }
    setMoneyAtStake();
  }
  
  //Get bet by reference. Bets are equals if they have same Type or Number
  private int getBetFromCurrentBets(Bet myBet) {
    if (myBet.getNumber() != null) {
      return currentBets.indexOf(new Bet(myBet.getNumber()));
    }else {
      return currentBets.indexOf(new Bet(myBet.getBetType()));
    }
  }

  /**
   * This function throw exceptions if bet is not valid 
   */
  public void betMoney(int bettedMoney) throws NoMoneyException, NegativeException {
    if (bettedMoney < 0) {// User try to bet a negative bet
      throw new NegativeException("You can't input negative bets");
    }

    if (moneyAtStake + bettedMoney > Player.getMoney()) {// User try to bet more money than he has
      throw new NoMoneyException("You have not enough money to bet for this.");
    }
  }

}


