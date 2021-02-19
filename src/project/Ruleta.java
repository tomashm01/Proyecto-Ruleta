package project;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ruleta {

  private static Set<Apuesta> apuestasGanadoras = new HashSet<>();
  private static Integer gananciasTirada;
  private static int ballNumber;
  private static Set<Apuesta> apuestasAcertadas;
  private static String resultadosUltimaPartida;
  private static Integer dineroApostadoUltimaPartida;

  /**
   * This function generate a random value for the ball between 0-36
   * 
   * @return int
   */
  static int randomBall() {
    int aleatorio = (int) (0 + Math.random() * (36 - 0) + 1);
    return aleatorio;
  }

  /**
   * This function returns the color of the number
   * 
   * @param n
   * @return String
   */
  static String colorBall(int n) {
    String val = "";
    int size = 18;
    int red[] = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
    int black[] = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
    if (n == 0) {
      val = "GREEN";
      return val;
    }
    for (int i = 0; i < size; i++) {
      if (n == red[i]) {
        val = "RED";
        return val;
      } else if (n == black[i]) {
        val = "BLACK";
        return val;
      }
    }
    return val;
  }

  /**
   * This function return if the number is Even or Odd
   * 
   * @param n
   * @return String
   */
  static String EvenOddBall(int n) {
    String val = "";
    if (n == 0) {
      val = "NULL";
      return val;
    } else if (n % 2 == 0) {
      val = "EVEN";
      return val;
    } else {
      val = "ODD";
      return val;
    }
  }

  /**
   * This function return if the number is between 1-18, 19-36 or is 0.
   * 
   * @param n
   * @return
   */
  static String HigherLowerThan(int n) {
    String val = "";
    if (n == 0) {
      val = "NULL";
      return val;
    } else if (n < 19) {
      val = "LOW";
      return val;
    } else if (n > 18) {
      val = "HIGH";
      return val;
    }
    return val;
  }

  public static void pushRoulette() {

    ballNumber = Ruleta.randomBall();

    apuestasGanadoras.addAll(List.of(new Apuesta(Ruleta.colorBall(ballNumber)),
        new Apuesta(Ruleta.EvenOddBall(ballNumber)),
        new Apuesta(Ruleta.HigherLowerThan(ballNumber))));

    Jugador.setFinalMoney();
    resultadosUltimaPartida = apuestasGanadoras.toString();
    setDineroApostadoUltimaPartida();
    restartTirada();

  }

  public static int getGananciasTirada() {
    return gananciasTirada;
  }

  public static String getResultadosUltimaPartida() {
    return resultadosUltimaPartida;
  }

  /**
   * Se comprueban las opciones acertadas(RED/BLACK/EVEN...) y se multiplica el valor apostado de
   * cada una por dos (Si la ha acertado claro)
   * 
   * @return
   */
  public static void calcularGananciasTirada() {

    gananciasTirada = 0;

    apuestasAcertadas = new HashSet<Apuesta>(Jugador.apuestasJugador);

    apuestasAcertadas.retainAll(apuestasGanadoras); // Intersección entre las apuestas ganadoras y
                                                    // las apuestas del jugador

    // Un stream() devuelve una secuencia de objetos que soportan varios métodos (devuelve cada
    // apuesta)
    // Un mapToInt() devuelve un valor entero (Calculado usando una función lambda)
    gananciasTirada = apuestasAcertadas.stream()
        .mapToInt(apuestaAcertada -> apuestaAcertada.getAmount() * 2).sum();

  }

  private static void restartTirada() {
    apuestasGanadoras.clear();
    Jugador.restartRound();
  }

  public static int getBallNumber() {
    return ballNumber;
  }

  public static Set<Apuesta> getApuestasGanadoras() {
    return apuestasGanadoras;
  }

  public static Set<Apuesta> getApuestasAcertadas() {
    return apuestasAcertadas;
  }

  public static void setDineroApostadoUltimaPartida() {

    dineroApostadoUltimaPartida = Jugador.apuestasJugador.stream().mapToInt(apuesta -> apuesta.getAmount()).sum();
  }
  
  public static int getDineroApostadoUltimaPartida() {
    return dineroApostadoUltimaPartida;
  }

}


