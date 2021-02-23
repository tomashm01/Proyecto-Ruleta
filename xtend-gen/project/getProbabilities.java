package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import project.Bet;
import project.WinningNumber;
import project.removeBetInCurrentBets;

@SuppressWarnings("all")
public class getProbabilities {
  static int numberRoll = 0;
  
  static HashMap<String, String> estadisticas = new HashMap<String, String>();
  
  static List<ArrayList<Bet>> historicoDeApuestasGanadoras = new ArrayList<ArrayList<Bet>>();
  
  static List<WinningNumber> historicoDeBolas = new ArrayList<WinningNumber>();
  
  private static void setHistoricoDeBolas(final WinningNumber ultimaBolaLanzada) {
    getProbabilities.historicoDeBolas.add(ultimaBolaLanzada);
  }
  
  public static List<WinningNumber> getHistoricoDeBolas() {
    return getProbabilities.historicoDeBolas;
  }
  
  public static List<ArrayList<Bet>> getHistoricoDeApuestasGanadoras() {
    return getProbabilities.historicoDeApuestasGanadoras;
  }
  
  private static void setHistoricoDeApuestasGanadoras(final List<Bet> apuestasGanadoras) {
    getProbabilities.historicoDeApuestasGanadoras.add(((ArrayList<Bet>) apuestasGanadoras));
  }
  
  /**
   * This function throw the ball
   */
  public static void pushRoulette(final removeBetInCurrentBets miJugada) {
    throw new Error("Unresolved compilation problems:"
      + "\n++ cannot be resolved.");
  }
  
  /**
   * This function calculate the total profit
   */
  public static int calculateProfit(final removeBetInCurrentBets miJugada, final WinningNumber nuevaBola) {
    throw new Error("Unresolved compilation problems:"
      + "\n* cannot be resolved."
      + "\nCannot refer to the non-final variable apuestasGanadoras inside a lambda expression");
  }
  
  public static void stadistics() {
    getProbabilities.calculateStatsType();
  }
  
  public static void calculateStatsType() {
    throw new Error("Unresolved compilation problems:"
      + "\n/ cannot be resolved."
      + "\n/ cannot be resolved."
      + "\n/ cannot be resolved."
      + "\n- cannot be resolved."
      + "\n- cannot be resolved."
      + "\n- cannot be resolved."
      + "\n< cannot be resolved."
      + "\nThe method or field length is undefined for the type String[][]"
      + "\n< cannot be resolved."
      + "\nThe method or field length is undefined for the type String[]"
      + "\n++ cannot be resolved."
      + "\n++ cannot be resolved."
      + "\n< cannot be resolved."
      + "\n/ cannot be resolved."
      + "\n++ cannot be resolved."
      + "\nCannot refer to the non-final variable redCount inside a lambda expression"
      + "\nCannot refer to the non-final variable evenCount inside a lambda expression"
      + "\nCannot refer to the non-final variable highCount inside a lambda expression"
      + "\n* cannot be resolved");
  }
}
