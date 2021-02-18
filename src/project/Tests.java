package project;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Tests {

  public static void main(String[] args) {
    Set<String> opcionesResultantes = new HashSet<>();
    Map<String, Integer> d1 = new HashMap<>();
    int finalMoney = 0;
    opcionesResultantes.add("RED");
    d1.putAll(Map.of("RED",50,"ODD",20));
    for (String s : opcionesResultantes) {
      if (d1.containsKey(s)) {
        finalMoney += d1.get(s) * 2;
      }
    }
    System.out.println(finalMoney);
    
  }

}
