package viewControllerJavaFX;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
	  var fxml = new FXMLLoader(getClass().getResource("vistas/ValidDni.fxml"));
	    try {
	      var root = fxml.<Pane>load();
	      var scene = new Scene(root);
	      primaryStage.setScene(scene);
	      primaryStage.setTitle(getClass().getSimpleName());
	      primaryStage.show();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}

	public static void main(String[] args) {
		launch(args);
	}
}
