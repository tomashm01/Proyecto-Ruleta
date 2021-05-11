package viewControllerJavaFX;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Test extends Application {

  private TextField fieldOrigen = new TextField();
  private Stage escenarioFichero;
  private Button boton = new Button("Quitar comentarios");
  private TextArea area = new TextArea();

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage escenario) throws Exception {
    escenario.setTitle("Quitar comentarios");
    Scene escena = crearEscena();
    escenario.setScene(escena);
    escenario.show();
  }

  private Scene crearEscena() {
    Label label1 = new Label("Nombre del fichero seleccionado");
    Label label2 = new Label("Contenido del fichero sin comentarios");
    HBox caja= editarHBox(label1);

r    VBox vertical = editarVBox(caja,label2);

    Scene escena = new Scene(vertical, 400, 300);

    boton.setOnAction(event -> quitarComentarios());
    return escena;
  }

  private void quitarComentarios() {
    try {
       
      FileChooser fileChooser=new FileChooser();
      File fichOrigen=fileChooser.showOpenDialog(escenarioFichero);
      File fichDestino=fileChooser.showSaveDialog(escenarioFichero);
      
      fieldOrigen.setText(fichOrigen.getName());
      area.setText("Contenido del fichero:\n");
      
      cambiarFichero(fichOrigen,fichDestino);
      
    }catch(FileNotFoundException e) {
      Alert alerta=new Alert(Alert.AlertType.ERROR);
      alerta.setTitle("Excepcion!!");
      alerta.setHeaderText("Error al encontrar el fichero!");
      alerta.setContentText("No existe o no se ha encontrado el fichero");
      alerta.showAndWait();
      
    }catch(IOException e) {
      Alert alerta=new Alert(Alert.AlertType.ERROR);
      alerta.setTitle("Excepcion!!");
      alerta.setHeaderText("Error en el fichero!");
      alerta.setContentText("Error de entrada/salida del fichero");
      alerta.showAndWait();
      
    } 
  }

  private void cambiarFichero(File fichOrigen,File fichDestino) throws IOException,FileNotFoundException{
    String lineaLeida="";    
    String texto="";
    BufferedReader br=new BufferedReader(new FileReader(fichOrigen));
    BufferedWriter bw=new BufferedWriter(new FileWriter(fichDestino));
    boolean estoyEnUnBloque=false;
    
    while((lineaLeida=br.readLine())!=null) {
      
      if(lineaLeida.contains("/*")) {
        estoyEnUnBloque=true;
      }else if(lineaLeida.contains("//")) {
        int posicion=lineaLeida.indexOf("//");
        lineaLeida=lineaLeida.substring(0,posicion);
      }
      
      if(!estoyEnUnBloque) {
        bw.write(lineaLeida);
        texto+=lineaLeida+"\n";
        bw.newLine();
      }
      
      if(lineaLeida.contains("*/")) {
        estoyEnUnBloque=false;
      }
    } 
    br.close();
    bw.close();
    area.appendText(texto);
  }

  private VBox editarVBox(HBox caja, Label label) {
    VBox vertical = new VBox(caja,label,area,boton);
    vertical.setAlignment(Pos.CENTER);
    vertical.setSpacing(10);
    area.setPadding(new Insets(10));
    area.setEditable(false);
    return vertical;
  }

  private HBox editarHBox(Label label) {
    HBox caja = new HBox(label, fieldOrigen);
    caja.setAlignment(Pos.CENTER);
    caja.setSpacing(10);
    fieldOrigen.setEditable(false);
    return caja;
  }
}