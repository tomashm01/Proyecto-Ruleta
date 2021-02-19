package project;

import java.io.IOException;
/**
 * Version Actual: 6 Clases Main.java: Mostramos un menú funcional con el que interactúa el usuario
 * Sus opciones son: 1ºApostar al color 2ºApostar a par o impar (Lo mismo pero diciendo si es par o
 * impar y multiplica por 2 igual) 3ºSi el numero está entre 1 y 18 o 19 al 36 4º Girar la ruleta
 * 
 * Jugador.java: Es necesario añadir un DNI para identificar que el jugador es apto para jugar en
 * nuestro juego. Sus créditos iniciales serán 500 por defecto. El jugador elegirá la opción que
 * quiera en cada tirada. Puede pasar de ronda sin apostar nada. El jugador podrá apostar X dinero a
 * un color. Si acierta recibe el doble de esa cantidad. También podrá apostar X dinero si el número
 * es par / impar o si es mayor o menor que 36. Las apuestas a cada opción son independientes.
 * 
 * La opción elegida en cada tirada se guardará en un arrayList (RED/BLACK,EVEN/ODD,HIGHER/LOWER) El
 * dinero apostado en esa tirada se guardará en otro arrayList (20,100,0) (Ha apostado 20 al
 * rojo/negro, 100 a que sale par/impar y no ha apostado a higher/lower
 * 
 * 
 * Ruleta.java: En la ruleta saldrá un número aleatorio entre 0 y 36 Dependiendo del número podremos
 * definir si es Rojo/Negro, Par o impar o si es mayor o menor que 19 Por tanto en la ruleta
 * tendremos un conjunto de opciones resultantes para esta tirada: Set [ROJO/NEGRO, PAR/IMPAR,
 * MAYOR/MENOR]
 * 
 * Finalmente cuando realicemos la tirada de la ruleta haremos la intersección entre las opciones
 * elegidas por el usuario y las opciones resultantes de la tirada. Con esta intersección sabremos
 * qué ha acertado el usuario.
 * 
 * Para realizar el cálculo del dinero hemos considerado que: Al apostar se le resta el dinero al
 * usuario hasta que se active la ruleta. Cuando se activa la ruleta se calculan los aciertos que ha
 * tenido el usuario y se duplica por el valor apostado en cada caso (Si ha apostado 20 al rojo y no
 * acierta perderá su dinero. Sin embargo, si ha apostado 100 a par y acierta, se le sumará 200 a su
 * dinero)
 * 
 * Bugs: colorChoice = Jugador.getChoice(0); evenOddChoice = Jugador.getChoice(1); highLowChoice =
 * Jugador.getChoice(2);
 * 
 * Si no hemos añadido color y añadimos evenOdd directamente el programa lanza una excepción pues
 * solo se ha rellenado la primera opción (solo está rellena la primera posición)
 * 
 * Qué pasaría si seleccionamos primero high, despues color y después even? Funcionará
 * correctamente? Debemos hacer que eso funcione.
 */
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    clearScreen();
    
    Scanner s = new Scanner(System.in);

    int option;

 

    boolean apuestaRealizada = false;
    boolean ruletaGirada = false;

    boolean validDni = insertDni();
    if (!validDni) {
      setRandomDni();
    }

    do {
      clearScreen();

      System.out.printf(ConsoleColors.PURPLE + "\n%82s:%d\n", "MONEY", Jugador.getMoney());
      System.out.printf("%80s:%s\n " + ConsoleColors.RESET, "DNI", Jugador.getDni());

      if (apuestaRealizada) {
        System.out.printf("%91s:%s\n " + ConsoleColors.RESET, "DINERO EN JUEGO",
            Jugador.getDineroEnJuego());
      }

      if (ruletaGirada) {
        HUD.printBallNumber();
        HUD.printResults();
        HUD.printApuestasAcertadas();
        HUD.mostrarGananciasTirada(Ruleta.getGananciasTirada());   

      }

      option = rouletteMenu();

      switch (option) {

        case 1:
          int moneyBetted = insertAmount();
          String red = Apuesta.POSSIBLE_BET_TYPES[0];
          String black = Apuesta.POSSIBLE_BET_TYPES[1];
          String colorChoice = insertChoice(red, black);
          Jugador.createBet(colorChoice, moneyBetted);
          apuestaRealizada = true;
          break;
        case 2:
          moneyBetted = insertAmount();
          String even = Apuesta.POSSIBLE_BET_TYPES[2];
          String odd = Apuesta.POSSIBLE_BET_TYPES[3];
          String evenOddChoice = insertChoice(even, odd);
          Jugador.createBet(evenOddChoice, moneyBetted);
          apuestaRealizada = true;
          break;
        case 3:
          moneyBetted = insertAmount();
          String high = Apuesta.POSSIBLE_BET_TYPES[4];
          String low = Apuesta.POSSIBLE_BET_TYPES[5];
          String highLowChoice = insertChoice(high, low);
          Jugador.createBet(highLowChoice, moneyBetted);
          apuestaRealizada = true;
          break;
        case 4:
          Ruleta.pushRoulette();
          ruletaGirada = true;
          break;
        case 5:
          Jugador.restartGame();
          break;
        case 6:
          System.out.println(ConsoleColors.CYAN + "Goodbye");
          break;
        default:
          System.err.println(showAsError("Error in the input option"));
      }
    } while (option != 6);
  }

 

  public static void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
}  



  private static boolean insertDni() {
    Scanner s = new Scanner(System.in);

    int count = 3;

    do {// User have 3 attemps to introduce a valid DNI

      System.out.printf(ConsoleColors.CYAN + "Please enter your DNI. (You have %d attempts):",
          count);
      String dni = s.nextLine();
      
      if (!Jugador.validateDni(dni)) {
        count--;
      } else {
        Jugador.setDni(dni);
        return true;
      }
      
    } while (count != 0);
    return false;
  }
  
  private static void setRandomDni() {
    System.out.printf(ConsoleColors.RED + "%107s", "We have created a DNI for you.");
    Jugador.setDni(Jugador.randomDni());
  }
  
  private static String insertChoice(String possibleChoice1, String possibleChoice2) {
    Scanner s = new Scanner(System.in);
    String choice;

    do {

      HUD.printInputType(possibleChoice1, possibleChoice2);// Add -1 if you want to leave
      // HUD.printRedBlackChoice();

      choice = (s.nextLine().toUpperCase());

      if (!choice.equals(possibleChoice1) && !choice.equals(possibleChoice2)) {

        System.out.printf(showAsError("%s %s %s %s\n"), "\nPlease insert", possibleChoice1, " OR ",
            possibleChoice2);
      }

    } while (!choice.equals(possibleChoice1) && !choice.equals(possibleChoice2));

    return choice;
  }

  static int rouletteMenu() {
    Menu roulette = new Menu("--ROULETTE MENU--", "Color bet", "Even or odd bet",
        "Higher or lower bet", "Spin roulette", "Reset Game");
    return roulette.manage();
  }

  static int insertAmount() {
    Scanner s = new Scanner(System.in);
    boolean invalid = true;
    int moneyBetted = 0;

    do {

      System.out.print("Please insert the amount to bet: ");
      moneyBetted = validateNumber();

      try {

        Jugador.betMoney(moneyBetted);
        invalid = false;
      } catch (NoMoneyException | NegativeException noMoney) {

        System.err.println(noMoney);
      }

    } while (invalid);

    return moneyBetted;
  }

  public static int validateNumber() {
    Scanner s = new Scanner(System.in);
    boolean invalid = true;
    int numToValidate = 0;
    do {
      // Checks that the input can be parsed as an int
      if (s.hasNextInt()) {
        numToValidate = s.nextInt();
        // Advances the scanner to prevent input errors
        s.nextLine();
        // Sets the condition to false to break the loop
        invalid = false;
      } else {
        System.err.print(showAsError("Invalid input.\nInsert number again:"));
        // Advances the scanner to prevent input errors
        s.nextLine();
      }
    } while (invalid); // The loop ends when the input is valid
    return numToValidate;
  }

  private static String showAsError(String error) {
    return ConsoleColors.RESET + ConsoleColors.RED + error + ConsoleColors.RESET;

  }

  public static String showAsError(String error, String string, String possibleChoice1,
      String string2, String possibleChoice2) {
    return ConsoleColors.RESET + ConsoleColors.RED + error + ConsoleColors.RESET;
  }

}
