package project;

import java.util.EnumSet;
import org.junit.jupiter.api.Test;

class MainTest {

  @Test
  void testInsertChoice() {
    EnumSet<BetTypes> color, dozenBet,LineBet;
    color = EnumSet.of(BetTypes.RED,BetTypes.BLACK);

    dozenBet = EnumSet.range(BetTypes.DOZEN1, BetTypes.DOZEN3);
    LineBet = EnumSet.range(BetTypes.LINE1, BetTypes.LINE3);
    
    Main.insertChoice(color);
  }

}
