package viewControllerJavaFX.vistas;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class mainController {
	@FXML
	private Button testButton;
	@FXML
	private Label labelTest;
	@FXML
	private TextField textFieldTest;

	// Event Listener on Button[#testButton].onAction
	@FXML
	public void test(ActionEvent event) {
		System.out.println("Hola");
	}
}
