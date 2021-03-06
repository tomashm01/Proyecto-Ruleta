package project;

import java.sql.Types;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Testing {
  public static void main(String[] args) {
    

    
    
//    System.out.println(dozenBet);
//    System.out.println(LineBet);
//    
//    Bet myBet = new Bet(BetTypes.RED, 100);
//    
//    String RED = BetTypes.RED.toString();
//    System.out.println("RED");
//    if (simpleBet.contains(myBet.getType())) {
//      System.out.println("Tu apuesta es " + myBet.getType() + ":" + myBet.getAmount());
//    }
    // Display the set 
//    System.out.println("The EnumSet after adding a single element is: " + simpleBet); 
     
    var currentMood = Mood.CALM;
    EnumSet<Mood> goodVibes, set2, set3, set4;
    EnumSet<TypeBet> tipoDeApuesta;  
    
    if (EnumSet.of(Mood.CALM, Mood.HAPPY, Mood.OPTIMISTIC).contains(currentMood)) {
      System.out.println("Mala vibra fuera");
    }
      // List<HTTPMethodConvertible> blah = new ArrayList<>();
    // blah.add(Type.SIMPLE);
    // blah.add(SIMPLE.RED);
    //
    // for (HTTPMethodConvertible element: blah) {
    // System.out.println(element.getHTTPMethodType());
    // }

  }

  static interface HTTPMethodConvertible {
    public String getHTTPMethodType();
  }
  enum Mood{
    HAPPY, SAD, CALM, SLEEPY, OPTIMISTIC, PENSIVE, ENERGETIC;

  }

  static enum TypeBet implements HTTPMethodConvertible {


    SIMPLE("SIMPLE"), DOZEN("DOZEN"), LINE("LINE");

    String httpMethodType;

    Type(String s) {
      httpMethodType = s;
    }

    public String getHTTPMethodType() {
      return httpMethodType;
    }
  }
  static enum SIMPLE implements HTTPMethodConvertible {
    RED("RED"), BLACK("BLACK"), EVEN("EVEN"), ODD("ODD"), HIGH("HIGH"), LOW("LOW");

    String httpMethodType;

    SIMPLE(String s) {
      httpMethodType = s;
    }

    public String getHTTPMethodType() {
      return httpMethodType;
    }
  }
}

