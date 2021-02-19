package project;

/**
 * Esta clase es utilizada para mostrar el HUD de nuestro juego
 *
 *
 */
public class HUD {

  public static void mostrarGananciasTirada(Integer gananciasTirada) {
    
    System.out.printf("%55s:", "Balance de esta tirada");
    if (gananciasTirada - Ruleta.getDineroApostadoUltimaPartida() > 0) {
      System.out.println(ConsoleColors.GREEN + "(+" + (gananciasTirada - Ruleta.getDineroApostadoUltimaPartida()) + ")"
          + ConsoleColors.RESET);
    } else {
      System.out.println(ConsoleColors.RED + "(" + (gananciasTirada - Ruleta.getDineroApostadoUltimaPartida()) + ")"
          + ConsoleColors.RESET);
    }
  }

  public static void printBallNumber() {
    System.out.printf("%41s%d\n", "NUMBER ->",Ruleta.getBallNumber());

  }

  public static void printResults() {
    System.out.printf("%44s","Resultados:");
    System.out.println(Ruleta.getResultadosUltimaPartida());
   
  }

  public static void printApuestasAcertadas() {

    System.out.printf("%56s","Tus apuestas acertadas:");
    if (Ruleta.getGananciasTirada() == 0) {
      System.out.println(" Ninguna :(");
    } else {
      System.out.println(Ruleta.getApuestasAcertadas().toString());
    }

  }

  public static void printInputType(String possibleChoice1, String possibleChoice2) {
    System.out.printf("%s", ConsoleColors.PURPLE + "Input the type");
    System.out.print(" (" + possibleChoice1 + " | " + possibleChoice2 + "):");
  }

  public static void printRedBlackChoice() {
    System.out.printf(ConsoleColors.RED + "%4s", "RED " + ConsoleColors.RESET);
    System.out.printf("|" + ConsoleColors.BLACK_BOLD + "%s", " BLACK: " + ConsoleColors.RESET);
  }

}

