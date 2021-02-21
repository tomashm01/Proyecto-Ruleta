package project;

public class Jugador {
  
  private static int money = 500;
  private static String dni = "";
  
  //Getters
  public static String getDni() {
    return dni;
  }
  public static int getMoney() {
    return money;
  }
  
  //Setters 
  public static void setDni(String dni) {
    Jugador.dni = dni;
  }
  
  public static void restartGame() {
    Jugador.money = 500;
  }

  /*
   * This function calculate the total money to deliver to the player
   */
  public static void setFinalMoney(Jugada miJugada) {
   Jugador.money += miJugada.getFinalBalance();
  }

  /*
   * This function validate if the dni which is passed by parameter is correct or incorrect
   * 
   * @param dni
   * 
   * @return boolean
   */
  static boolean validateDni(String dni) {
    String pattern = "^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$(?i)";
    //^[0-9]{8} -> los 8 primeras caracteres deben ser numeros
    //$ -> el caracter final
    //(?i) -> es case insensitive (le da igual mayusculas o minusculas)
    if (!dni.matches(pattern)) {//Si no se ajusta al patrón de la expresión regular -> no será válido
      return false;
    }
    
    String validChars = pattern.substring(10, 33); //Guardo las letras en una cadena
    char letter = dni.toUpperCase().charAt(8); //Cojo la última letra del dni
    int charIndex = Integer.parseInt(dni.replace(dni.charAt(8), ' ').trim()) % 23; //Paso el dni a número y devuelvo el resto
    
    if (validChars.charAt(charIndex) == letter) { //Si coincide la posicion de la letra con la cadena de letras será válido
      return true;
    }else {
      return false;
    }
  }

  /*
   * This function generate a random Dni and return it
   * 
   * @return String
   */
  
  public static String randomDni() {
    int MAX = 99999999;
    // Generate a random dni
    String dni;
    int num = (int) (Math.random() * MAX);
    String numDni = Integer.toString(num);
    int rest = num % 23;
    // Calculate the letter of the dni that corresponds to the number
    String[] letters = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z",
        "S", "Q", "V", "H", "L", "C", "K", "E"};
    String letterDni = letters[rest];
    // Concatenate all and returns a String
    dni = numDni + letterDni;
    return dni;
  }
  
}
