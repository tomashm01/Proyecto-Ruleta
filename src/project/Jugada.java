package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Jugada {
  // Apuestas realizadas con anterioridas anterioridad
  private static List<ArrayList<Apuesta>> historicoDeApuestas = new ArrayList<ArrayList<Apuesta>>(); 
  private static List<Integer> historicoDeBalances = new ArrayList<>();
  
  private List<Apuesta> apuestasActuales = new ArrayList<>(); // Lo que el jugador ha elegido justo
                                                              // antes de lanzar la bola
  private int finalBalance; // Dinero tras restar el dinero en juego y sumar el beneficio
  private int moneyAtStake; // Dinero en juego
  
  //Getters & Setters
  public static List<ArrayList<Apuesta>> getHistoricoDeApuestas() {
    return historicoDeApuestas;
  }

  public void setHistoricoDeApuestas() {
    historicoDeApuestas.add((ArrayList<Apuesta>) apuestasActuales);
  }

  public List<Apuesta> getApuestasActuales() {
    return apuestasActuales;
  }

  public void setFinalBalance(Bola nuevaBola) {
    this.finalBalance = Ruleta.calculateProfit(this, nuevaBola) - moneyAtStake;
    setHistoricoDeApuestas();
    setHistoricoDeBalances();
  }

  public int getFinalBalance() {
    return this.finalBalance;
  }

  public static List<Integer> getHistoricoDeBalances() {
    return historicoDeBalances;
  }

  private void setHistoricoDeBalances() {
    historicoDeBalances.add(this.finalBalance);
  }
  /**
   * La función más difícil de todo el programa.
   * Esta función crea un iterador que recorre la lista de apuestas actuales
   * crea un objeto apuesta, y compara con los posibles tipos de apuestas. (color, parImpar..)
   * Si coincide que hay una apuesta en la lista con la elección del jugador se borrará la apuesta y se añadirá la nueva.
   * @param choice
   * @param moneyBetted
   */
  public void addApuesta(String choice, int moneyBetted) {
    Iterator<Apuesta> it = apuestasActuales.iterator();
    while (it.hasNext()) {
      Apuesta apuesta = (Apuesta) it.next();
      for (int i = 0; i < Apuesta.POSSIBLE_BET_TYPES.length; i++) {
        if (Arrays.asList(Apuesta.POSSIBLE_BET_TYPES[i]).contains(apuesta.getType())
            && Arrays.asList(Apuesta.POSSIBLE_BET_TYPES[i]).contains(choice)) {
          it.remove();
        }
      }
    }
    apuestasActuales.add(new Apuesta(choice, moneyBetted));
    setMoneyAtStake();
  }

  public int getMoneyAtStake() {
    return moneyAtStake;
  }
  
  /**
   * Se encarga de lanzar excepciones si la apuesta no es válida
   * @param bettedMoney
   * @throws NoMoneyException
   * @throws NegativeException
   */
  public void betMoney(int bettedMoney) throws NoMoneyException, NegativeException{
    if (bettedMoney < 0) {
      throw new NegativeException("You can't input negative bets");
    }

    if (moneyAtStake + bettedMoney > Jugador.getMoney()) {
      throw new NoMoneyException("You have not enough money to bet for this.");
    }
  }

  public void setMoneyAtStake() {
    this.moneyAtStake = apuestasActuales.stream().mapToInt(bets -> bets.getAmount()).sum();
  }
}


