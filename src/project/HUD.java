package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HUD {

  
  /*
   * This function print the number on the roulette
   */
  public static void printBallNumber() {
    if (Ruleta.getHistoricoDeBolas() != null) {
      System.out.println("");
      System.out.printf("%42s", "NUMBERS->");  
      
      List<Bola> reverse = new ArrayList<Bola>(Ruleta.getHistoricoDeBolas());
      Collections.reverse(reverse);
      if (reverse.size() > 10) {
        reverse.removeIf(bola -> reverse.indexOf(bola) > 10 );       
      }
      reverse.forEach(bola -> System.out.print(bola.getNumber() + " "));
    }
  }
  
  public static void printBallResults() {
    System.out.printf("\n%41s","Results:");
    System.out.println(Ruleta.getHistoricoDeBolas().get(Ruleta.getHistoricoDeBolas().size()-1).getResults());
  }
  
  public static void printSucessfulBet() {
    System.out.printf("%46s","Winning bets:");
    if (Ruleta.getHistoricoDeApuestasGanadoras().get(Ruleta.getHistoricoDeApuestasGanadoras().size()-1).size() == 0) {
      System.out.println("No ganaste ninguna apuesta :(");
    }else {
      System.out.println(Ruleta.getHistoricoDeApuestasGanadoras().get(Ruleta.getHistoricoDeApuestasGanadoras().size()-1));
    }
  }
  
  public static void printBets() {
    System.out.printf("%48s","Your last bets:");
    if (Jugada.getHistoricoDeApuestas().get(Jugada.getHistoricoDeApuestas().size()-1).size() == 0) {
      System.out.println("No realizaste ninguna apuesta :(");
    }else {
      System.out.println(Jugada.getHistoricoDeApuestas().get(Jugada.getHistoricoDeApuestas().size()-1));
    }
 }
  
 public static void printBalanceRoll() {
    System.out.printf("%53s:", "Balance of this roll");
    if (Jugada.getHistoricoDeBalances().get(Jugada.getHistoricoDeBalances().size()-1) > 0) {
      System.out.print(ConsoleColors.GREEN);
      System.out.println("+" + Jugada.getHistoricoDeBalances().get(Jugada.getHistoricoDeBalances().size()-1));
      System.out.println(ConsoleColors.RESET);
    }else {
      System.out.print(ConsoleColors.RED);
      System.out.println(Jugada.getHistoricoDeBalances().get(Jugada.getHistoricoDeBalances().size()-1));  
      System.out.println(ConsoleColors.RESET);
    }
  }
  
  /*
   * This function print the type of bet
   */  
  public static void printInputType(String possibleChoice1, String possibleChoice2) {
    System.out.printf("%s", ConsoleColors.PURPLE + "Input the type");
    System.out.print(" (" + possibleChoice1 + " | " + possibleChoice2 + "):");
  }
  
}

