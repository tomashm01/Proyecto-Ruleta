module Projects {

  exports roulette;
  exports viewControllerJavaFX;
  exports bets;
  exports Exceptions;
  exports player;
  exports viewControllerTerminal;

  requires javafx.base;
  requires javafx.controls;
  requires transitive javafx.graphics;
  opens viewControllerJavaFX to javafx.fxml;
}
