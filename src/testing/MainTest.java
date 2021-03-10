package testing;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Test;
import project.Roulette;
import viewControllerTerminal.Main;


class MainTest {

  @Test
  void testInsertChoice() {
    EnumSet<BetTypes> color, dozenBet,LineBet;
    color = EnumSet.of(BetTypes.RED,BetTypes.BLACK);

    dozenBet = EnumSet.range(BetTypes.DOZEN1, BetTypes.DOZEN3);
    LineBet = EnumSet.range(BetTypes.LINE1, BetTypes.LINE3);
    
    Main.insertChoice(color);
  }
  
  @Test
  void testFillStatistics() {
    HashMap<BetTypes, String> statistics = new HashMap<BetTypes, String>();
    ArrayList<Double> AllWinningNumbersProbabilities = new ArrayList<Double>();
    AllWinningNumbersProbabilities.addAll(List.of(0.2,0.5,0.7,0.8,0.9));
    
    Roulette.fillStatistics(AllWinningNumbersProbabilities);;
    
    
  }

}
