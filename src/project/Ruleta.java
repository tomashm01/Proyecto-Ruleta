package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Ruleta {
  static int numberRoll = 0;
  static HashMap<String, String> estadisticas = new HashMap<String, String>();

  static List<ArrayList<Apuesta>> historicoDeApuestasGanadoras =
      new ArrayList<ArrayList<Apuesta>>();
  static List<Bola> historicoDeBolas = new ArrayList<Bola>();

  private static void setHistoricoDeBolas(Bola ultimaBolaLanzada) {
    historicoDeBolas.add(ultimaBolaLanzada);
  }

  public static List<Bola> getHistoricoDeBolas() {
    return historicoDeBolas;
  }

  public static List<ArrayList<Apuesta>> getHistoricoDeApuestasGanadoras() {
    return historicoDeApuestasGanadoras;
  }

  private static void setHistoricoDeApuestasGanadoras(List<Apuesta> apuestasGanadoras) {
    historicoDeApuestasGanadoras.add((ArrayList<Apuesta>) apuestasGanadoras);
  }
  /*
   * This function throw the ball
   */

  public static void pushRoulette(Jugada miJugada) {

    Bola nuevaBola = new Bola();
    miJugada.setFinalBalance(nuevaBola);
    Jugador.setFinalMoney(miJugada);
    setHistoricoDeBolas(nuevaBola);
    Ruleta.numberRoll++;
  }

  /*
   * This function calculate the total profit
   *
   */
  public static int calculateProfit(Jugada miJugada, Bola nuevaBola) {
    ArrayList<Apuesta> apuestasGanadoras = new ArrayList<Apuesta>();
    List<String> stringApuestasGanadoras = new ArrayList<String>(nuevaBola.getResults());


    stringApuestasGanadoras.retainAll(miJugada.getApuestasActuales().stream()
        .map(apuesta -> apuesta.getType()).collect(Collectors.toList()));


    stringApuestasGanadoras.forEach(element -> apuestasGanadoras.add(miJugada.getApuestasActuales()
        .get(miJugada.getApuestasActuales().indexOf(new Apuesta(element)))));
    setHistoricoDeApuestasGanadoras(apuestasGanadoras);

    return apuestasGanadoras.stream().mapToInt(winningBets -> winningBets.getAmount() * 2).sum(); // Devuelve
                                                                                                  // las
                                                                                                  // ganancias
  }

  public static void stadistics() {
    calculateStatsType();
  }

  public static void calculateStatsType() {
    estadisticas.clear();
    AtomicInteger redCount = new AtomicInteger();
    AtomicInteger evenCount = new AtomicInteger();
    AtomicInteger highCount = new AtomicInteger();
    historicoDeBolas.stream().forEach(bola -> {
      if (bola.color.equals("RED")) {
        redCount.getAndIncrement();
      }
      if (bola.EvenOdd.equals("EVEN")) {
        evenCount.getAndIncrement();
      }
      if (bola.HighLow.equals("HIGH")) {
        highCount.getAndIncrement();
      }
    });
    double redCountDouble = redCount.doubleValue()/historicoDeBolas.size();
    double evenCountDouble = evenCount.doubleValue()/historicoDeBolas.size();
    double highCountDouble = highCount.doubleValue()/historicoDeBolas.size();

    ArrayList<Double> counters =
        new ArrayList<Double>(Arrays.asList(redCountDouble, 1 - redCountDouble, evenCountDouble,
            1 - evenCountDouble, highCountDouble, 1 - highCountDouble));
    List<String> aux = new ArrayList<>();//Lista de una dimension
    for (int i = 0; i < Apuesta.POSSIBLE_BET_TYPES.length ; i++) {
      for (int j = 0; j < Apuesta.POSSIBLE_BET_TYPES[0].length; j++) {
       aux.add(Apuesta.POSSIBLE_BET_TYPES[i][j]); 
      }
    }
 
    int j = 0;
    while(j< counters.size()){
      estadisticas.put(aux.get(j), String.format("%.2f%%", ((counters.get(j) / historicoDeBolas.size()) * 100)));
      j++;
    }
  }
}


