module Projects {

  exports roulette;
  exports viewControllerJavaFX;
  exports bets;
  exports Exceptions;
  exports player;
  exports viewControllerTerminal;
  exports viewControllerJavaFX.vistas;
  
  requires javafx.base;
  requires javafx.controls;
  requires transitive javafx.graphics;
  requires javafx.fxml;
  opens viewControllerJavaFX to javafx.fxml;
  opens viewControllerJavaFX.vistas to javafx.fxml;
}
