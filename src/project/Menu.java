package project;

import java.util.Arrays;
import java.util.Scanner;

public class Menu {
  private String titulo = null;
  private String[] opciones = null;
  // Creamos un menú
  public Menu (String titulo, String... opciones) {
    this.titulo = titulo;
    this.opciones = opciones;
  }
  

  // Métodos del menú
  
  /**
   * Añade opciones al menú
   * @param nuevaOpcion
   */
  public void añadirOpcion(String nuevaOpcion){
    this.opciones = Arrays.copyOf(this.opciones, this.opciones.length+1);
    this.opciones[this.opciones.length-1] = nuevaOpcion;  
  }
  
  
  /**
   * Muestra el menú con todas sus opciones.
   */
  private void mostrarMenu() {
    System.out.printf("\n%40s\n\n",ConsoleColors.BLUE_BOLD + this.titulo);
    for (int i =0 ; i < this.opciones.length ; i++) {
      System.out.printf(ConsoleColors.GREEN + "option %d: %s\n",(i+1), this.opciones[i]);
    }
    //Como todos los menús tienen la opción de terminar la añadiré por defecto.
    System.out.printf(ConsoleColors.RESET + ConsoleColors.RED + "option %d: Go back" + ConsoleColors.RESET,(this.opciones.length+1));
  }
  
  private int elegirOpcion() {
   Scanner s = new Scanner(System.in);
   int opcionElegida;
   do {
     System.out.printf(ConsoleColors.RESET + ConsoleColors.BLUE_BOLD + "\n\nElige una opción(1-%d):",this.opciones.length+1);
     System.out.print(ConsoleColors.RESET + ConsoleColors.YELLOW);
     opcionElegida = s.nextInt();
   }while (opcionElegida < 1 || opcionElegida > this.opciones.length+1);
  return opcionElegida; 
  }
  
  public int gestionar() {
    mostrarMenu();
    return elegirOpcion();
  } 
}

