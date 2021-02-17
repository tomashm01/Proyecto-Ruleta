package project;

/*
 * Name: Ruleta.java
 * 
 * Author: Tomás Hidalgo Martín
 * 
 * Description: Class Persona, this class create a person object.
 * 
 * Version:1.0
 * 
 */
public class Persona {
  private String dni, name, surnames, location;
  private int age;
  static final double MAX = 99999999;
  static final String v = "EMPTY";

  // Constructor
  public Persona(String dni_, String name_, String surname_, int age_, String location_) {
    if (!setDni(dni_)) {
      System.out.println(ConsoleColors.RED + "INCORRECT DNI");
      System.out.println(ConsoleColors.RESET);
      // GENERATE RANDOM DNI
      System.out.println(ConsoleColors.WHITE + "CALCULATING A RANDOM DNI...");
      System.out.println(ConsoleColors.RESET);
      dni_ = randomDni();
    }
    if (name_.isEmpty()) {
      name_ = v;
    }
    if (surname_.isEmpty()) {
      surname_ = v;
    }
    if (age_ < 0) {
      age_ = 18;
    }
    if (location_.isEmpty()) {
      location_ = v;
    }
    dni = dni_;
    name = name_;
    surnames = surname_;
    age = age_;
    location = location_;
  }

  /**
   * Constructor Person with only 1 parameter
   * 
   * @param dni
   */
  public Persona(String dni_) {
    if (!setDni(dni_)) {
      System.out.println("DNI INCORRECTO");
      // GENERATE DNI RANDOM
      dni_= randomDni();
    }
    dni = dni_;
    name = v;
    surnames = v;
    age = 18;
    location = v;
  }

  // GETTERS
  public String getDni() {
    return dni;
  }

  public String getName() {
    return name;
  }

  public String getSurnames() {
    return surnames;
  }

  public int getAge() {
    return age;
  }

  public String getLocation() {
    return location;
  }

  // SETTERS
  public boolean setNombre(String name_) {
    if (name_.isEmpty()) {
      return false;
    }
    name = name_;
    return true;
  }

  public boolean setApellidos(String surnames_) {
    if (surnames_.isEmpty()) {
      return false;
    }
    surnames = surnames_;
    return true;
  }

  public boolean setEdad(int age_) {
    if (age_ < 0) {
      return false;
    }
    age = age_;
    return true;
  }

  public boolean setDireccion(String location_) {
    if (location_.isEmpty()) {
      return false;
    }
    location = location_;
    return true;
  }

  public boolean setDni(String dni_) {
    if (!validateDni(dni_)) {
      return false;
    }
    dni = dni_;
    return true;
  }

  /**
   * Function that validate if the dni which is passed by parameter is correc or incorrect
   * 
   * @param dni
   * @return boolean
   */
  static boolean validateDni(String dni) {
    // Array with all the possibles letters
    char[] lettersDni = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z',
        'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
    String num = "";
    int rest = 0;
    if (dni.length() < 9) {// Check if the dni have 9 position
      return false;
    }
    // Check if the 9º position is a letter
    if (!Character.isLetter(dni.charAt(8))) {
      return false;
    }
    // Check is the size of the dni is 9
    if (dni.length() != 9) {
      return false;
    }
    // Check if the first 8 digits are numbers
    for (int i = 0; i < 8; i++) {
      if (!Character.isDigit(dni.charAt(i))) {
        return false;
      }
      // if is a number with 8 digits, i keep it in a integer
      num += dni.charAt(i);
    }
    // Convert the number to a String
    rest = Integer.parseInt(num);
    // Calculate the letter that corresponds to the number
    rest %= 23;
    // Verify that the letter of the dni corresponds to that of the array
    if ((Character.toUpperCase(dni.charAt(8))) != lettersDni[rest]) {
      return false;
    }
    // DNI CORRECT
    return true;
  }

  /**
   * This function generate a random Dni
   * 
   * @return String
   */
  public static String randomDni() {
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
