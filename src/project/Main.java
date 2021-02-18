package project;

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
    int count = 3;
    Scanner s = new Scanner(System.in);

    int option;

    String colorChoice = "";
    String evenOddChoice = "";
    String highLowChoice = "";

    do {// User have 3 attemps to introduce a valid DNI

      System.out.printf(ConsoleColors.CYAN + "Please enter your DNI. (You have %d attempts):",
          count);
      String dni = s.nextLine();
      if (!Jugador.validateDni(dni)) {
        count--;
      } else {
        Jugador.setDni(dni);
        break;
      }
    } while (count != 0);

    if (count == 0) {// Random DNI
      System.out.printf(ConsoleColors.RED + "%100s", "We have created a DNI for you.");
      Jugador.setDni(Jugador.randomDni());
    }

    do {

      System.out.printf(ConsoleColors.PURPLE + "\n%82s:%d\n", "MONEY", Jugador.getMoney());
      System.out.printf("%80s:%s\n " + ConsoleColors.RESET, "DNI", Jugador.getDni());

      option = rouletteMenu();

      switch (option) {

        case 1:

          insertAmount();

          do {

            System.out.printf("%s", ConsoleColors.PURPLE + "Input the type\n");// Add -1 if you want
                                                                               // to leave
            System.out.printf(ConsoleColors.RED + "%4s", "RED " + ConsoleColors.RESET);
            System.out.printf("|" + ConsoleColors.BLACK_BOLD + "%s",
                " BLACK: " + ConsoleColors.RESET);

            Jugador.addChoice(s.nextLine()); // Aquí el jugador va a elegir una opción que podrá ser
            // RED / BLACK
            colorChoice = Jugador.getChoice(0);

            if (!colorChoice.equals("RED") || !colorChoice.equals("BLACK")) {
              System.err.println(showAsError("\nPlease insert a RED OR BLACK\n"));
            }

          } while (!colorChoice.equals("RED") && !colorChoice.equals("BLACK"));

          break;
        case 2:
          insertAmount();

          do {
            System.out.print("Input the type: EVEN | ODD:");
            Jugador.addChoice(s.nextLine()); // Aquí el jugador va a elegir una opción que podrá ser
            // EVEN/ODD
            evenOddChoice = Jugador.getChoice(1);

            if (!evenOddChoice.equals("EVEN") && !evenOddChoice.equals("ODD")) {
              System.err.print(showAsError("Please insert a EVEN OR ODD"));
            }
          } while (!evenOddChoice.equals("EVEN") && !evenOddChoice.equals("ODD"));

          break;

        case 3:
          insertAmount();

          do {
            System.out.println("Input the type: HIGH(19-36) | LOW(1-19)");
            Jugador.addChoice(s.nextLine()); // Aquí el jugador va a elegir una opción que podrá ser
            // HIGH/LOW
            highLowChoice = Jugador.getChoice(2);

            if (!highLowChoice.equals("HIGH") && !highLowChoice.equals("LOW")) {
              System.err.println(showAsError("Please insert a HIGH OR LOW"));
            }
          } while (!highLowChoice.equals("HIGH") && !highLowChoice.equals("LOW"));

          break;
        case 4:
          Ruleta.pushRoulette();

          break;
        case 5:
          System.out.println(ConsoleColors.CYAN + "Goodbye");
          break;
        default:
          System.err.println(showAsError("Error in the input option"));
      }
    } while (option != 5);
  }

  static int rouletteMenu() {
    Menu roulette = new Menu("--ROULETTE MENU--", "Color bet", "Even or odd bet",
        "Higher or lower bet", "Spin roulette");
    return roulette.manage();
  }

  static void insertAmount() {
    Scanner s = new Scanner(System.in);
    System.out.print("Please insert the amount to bet: ");
    int moneyBetted = validateNumber();

    try {
      Jugador.betMoney(moneyBetted);
    } catch (NoMoneyException noMoney) {
      System.err.println(noMoney);
    }

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

  public static String showAsError(String error) {
    return ConsoleColors.RESET + ConsoleColors.RED + error + ConsoleColors.RESET;
  }

}
