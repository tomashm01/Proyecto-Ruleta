package project;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;
import project.Bet;
import project.NegativeException;
import project.NoMoneyException;
import project.WinningNumber;

@SuppressWarnings("all")
public class removeBetInCurrentBets {
  private static List<ArrayList<Bet>> allBets = new ArrayList<ArrayList<Bet>>();
  
  private static List<Integer> allBalances = new ArrayList<Integer>();
  
  private List<Bet> currentBets = new ArrayList<Bet>();
  
  private int balance;
  
  private int moneyAtStake;
  
  public static List<ArrayList<Bet>> getAllBets() {
    return removeBetInCurrentBets.allBets;
  }
  
  public void setAllBets() {
    removeBetInCurrentBets.allBets.add(((ArrayList<Bet>) this.currentBets));
  }
  
  public List<Bet> getCurrentBets() {
    return this.currentBets;
  }
  
  public void setFinalBalance(final WinningNumber nuevaBola) {
    throw new Error("Unresolved compilation problems:"
      + "\n- cannot be resolved.");
  }
  
  public int getFinalBalance() {
    return this.balance;
  }
  
  public static List<Integer> getAllBalances() {
    return removeBetInCurrentBets.allBalances;
  }
  
  private void setAllBalances() {
    removeBetInCurrentBets.allBalances.add(Integer.valueOf(this.balance));
  }
  
  /**
   * La función más difícil de todo el programa.
   * Esta función crea un iterador que recorre la lista de apuestas actuales
   * crea un objeto apuesta, y compara con los posibles tipos de apuestas. (color, parImpar..)
   * Si coincide que hay una apuesta en la lista con la elección del jugador se borrará la apuesta y se añadirá la nueva.
   * @param choice
   * @param moneyBetted
   */
  public void addBet(final String choice, final int moneyBetted) {
    throw new Error("Unresolved compilation problems:"
      + "\n< cannot be resolved."
      + "\nThe method or field length is undefined for the type String[][]"
      + "\n&& cannot be resolved."
      + "\n++ cannot be resolved.");
  }
  
  public int getMoneyAtStake() {
    return this.moneyAtStake;
  }
  
  /**
   * Se encarga de lanzar excepciones si la apuesta no es válida
   * @param bettedMoney
   * @throws NoMoneyException
   * @throws NegativeException
   */
  public void betMoney(final int bettedMoney) throws NoMoneyException, NegativeException {
    throw new Error("Unresolved compilation problems:"
      + "\n< cannot be resolved."
      + "\n+ cannot be resolved."
      + "\n> cannot be resolved");
  }
  
  public void setMoneyAtStake() {
    final ToIntFunction<Bet> _function = (Bet bets) -> {
      return bets.getAmount();
    };
    this.moneyAtStake = this.currentBets.stream().mapToInt(_function).sum();
  }
}
