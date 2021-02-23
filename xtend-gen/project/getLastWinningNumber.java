package project;

import java.util.function.BiConsumer;
import org.eclipse.xtend2.lib.StringConcatenation;
import project.ConsoleColors;
import project.getProbabilities;

@SuppressWarnings("all")
public class getLastWinningNumber {
  /**
   * This function print the number on the roulette
   */
  public static void printWinningNumber() {
    throw new Error("Unresolved compilation problems:"
      + "\n!== cannot be resolved."
      + "\n- cannot be resolved."
      + "\n> cannot be resolved."
      + "\n> cannot be resolved."
      + "\nCannot refer to the non-final variable reverse inside a lambda expression");
  }
  
  public static void printWinningNumberResults() {
    throw new Error("Unresolved compilation problems:"
      + "\n- cannot be resolved.");
  }
  
  public static void printSuccessfulBet() {
    throw new Error("Unresolved compilation problems:"
      + "\n=== cannot be resolved."
      + "\n- cannot be resolved."
      + "\n- cannot be resolved.");
  }
  
  public static void printBets() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field project is undefined for the type Class<removeBetInCurrentBets>"
      + "\nThe method or field project is undefined for the type Class<removeBetInCurrentBets>"
      + "\nThe method or field project is undefined for the type Class<removeBetInCurrentBets>"
      + "\nThe method or field project is undefined for the type Class<removeBetInCurrentBets>"
      + "\nMove cannot be resolved"
      + "\nallBets cannot be resolved"
      + "\nget cannot be resolved"
      + "\nMove cannot be resolved"
      + "\nallBets cannot be resolved"
      + "\nsize cannot be resolved"
      + "\n- cannot be resolved"
      + "\nsize cannot be resolved"
      + "\n=== cannot be resolved"
      + "\nMove cannot be resolved"
      + "\nallBets cannot be resolved"
      + "\nget cannot be resolved"
      + "\nMove cannot be resolved"
      + "\nallBets cannot be resolved"
      + "\nsize cannot be resolved"
      + "\n- cannot be resolved");
  }
  
  public static void printBalanceRoll() {
    throw new Error("Unresolved compilation problems:"
      + "\n> cannot be resolved."
      + "\n- cannot be resolved."
      + "\n- cannot be resolved."
      + "\n- cannot be resolved.");
  }
  
  /**
   * This function print the type of bet
   */
  public static void printInputType(final String possibleChoice1, final String possibleChoice2) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(ConsoleColors.PURPLE);
    _builder.append("Input the type");
    System.out.printf("%s", _builder.toString());
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append(" ");
    _builder_1.append("(");
    _builder_1.append(possibleChoice1, " ");
    _builder_1.append(" | ");
    _builder_1.append(possibleChoice2, " ");
    _builder_1.append("):");
    System.out.print(_builder_1.toString());
  }
  
  public static void printStatistics() {
    final BiConsumer<String, String> _function = (String key, String value) -> {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(key);
      _builder.append(" : ");
      _builder.append(value);
      System.out.println(_builder.toString());
    };
    getProbabilities.estadisticas.forEach(_function);
  }
}
