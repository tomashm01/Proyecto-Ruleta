package project;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ruleta {
  static int numberRoll = 0;
  static int vectorStats[]= {0,0,0,0,0,0};
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

    // ["RED","HIGH"]
    stringApuestasGanadoras // Lista de resultados ["RED","EVEN","HIGH"]
        .retainAll// Utilizo retainAll para hacer la intersección entre la lista de resultados de la
                  // bola con el tipo de cada apuesta
        (miJugada.getApuestasActuales() // Obtengo las lista de apuestas de esta jugada
            .stream() // Utilizo Stream para recorrer cada objeto Apuesta
            .map(apuesta -> apuesta.getType()) // Utilizo map para obtener el tipo de cada apuesta
                                               // en la lista (String)
            .collect(Collectors.toList())); // Utilizo collect para pasar cada tipo a un arrayList
                                            // de Strings (para hacer la interseccion)

    // ["RED","HIGH"]
    stringApuestasGanadoras// ArrayList<String> de apuestas ganadoras ( quiero convertirla a
                           // ArrayList<Apuesta> )
        .forEach(element -> apuestasGanadoras // Por cada String de la lista win
            .add(miJugada.getApuestasActuales() // Añade al ArrayList<Apuesta>
                .get(miJugada.getApuestasActuales().indexOf(new Apuesta(element))))); // La
                                                                                      // referencia
                                                                                      // a memoria
                                                                                      // de una
                                                                                      // apuesta con
                                                                                      // el nombre
                                                                                      // ganador

    setHistoricoDeApuestasGanadoras(apuestasGanadoras);

    return apuestasGanadoras.stream().mapToInt(winningBets -> winningBets.getAmount() * 2).sum(); // Devuelve
                                                                                                  // las
                                                                                                  // ganancias
  }

  public static void stadistics() throws divideByZero {
    if (Ruleta.numberRoll == 0) {
      throw new divideByZero("Stats cannot be calculated");

    }
    System.out.println();
    for (int i = 0; i <= 2; i++) {
      calculateStatsType(i);
    }
  }

  public static void calculateStatsType(int j) {
    int stat1 = 0, stat2 = 0;
    float percentage1 = 0, percentage2 = 0;
    String aux="";
    //RED BLACK EVEN ODD HIGH LOW
    if (j == 0) {
      for (int i = 0; i < historicoDeBolas.size(); i++) {
        if (historicoDeBolas.get(i).getResults().get(j).equals("RED")) {
          stat1++;
        } else {
          stat2++;
        }
      }
      percentage1 = (float) ((float) stat1 / Ruleta.numberRoll) * 100;
      percentage2 = (float) ((float) stat2 / Ruleta.numberRoll) * 100;
      System.out.print(ConsoleColors.WHITE+"RED: " + (int)percentage1+"%  "+ConsoleColors.RESET);
      System.out.println(ConsoleColors.WHITE+"BLACK: " + (int)percentage2+"%"+ConsoleColors.RESET);
      System.out.println();
    } else if (j == 1) {
      for (int i = 0; i < historicoDeBolas.size(); i++) {
        if (historicoDeBolas.get(i).getResults().get(j).equals("EVEN")) {
          stat1++;
        } else {
          stat2++;
        }
      }
      percentage1 = (float) ((float) stat1 / Ruleta.numberRoll) * 100;
      percentage2 = (float) ((float) stat2 / Ruleta.numberRoll) * 100;
      System.out.print(ConsoleColors.WHITE+"EVEN: " + (int)percentage1+"%  "+ConsoleColors.RESET);
      System.out.println(ConsoleColors.WHITE+"ODD: " + (int)percentage2+"%"+ConsoleColors.RESET);
      System.out.println();
    } else {
      for (int i = 0; i < historicoDeBolas.size(); i++) {
        if (historicoDeBolas.get(i).getResults().get(j).equals("HIGH")) {
          stat1++;
        } else {
          stat2++;
        }
      }
      percentage1 = (float) ((float) stat1 / Ruleta.numberRoll) * 100;
      percentage2 = (float) ((float) stat2 / Ruleta.numberRoll) * 100;
      System.out.print(ConsoleColors.WHITE+"HIGH: " + (int)percentage1+"%  "+ConsoleColors.RESET);
      System.out.println(ConsoleColors.WHITE+"LOW: " + (int)percentage2+"%"+ConsoleColors.RESET);
      System.out.println();
    }
  }
}


