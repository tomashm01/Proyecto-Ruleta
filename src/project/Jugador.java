package project;
/*

 * Name: Ruleta.java
 * 
 * Author: Tomás Hidalgo Martín
 * 
 * Description: Class Jugador, this class generate a player object. 
 * 
 * Version:1.0
 * 
 */
import java.util.ArrayList;
import java.util.Scanner;
public class Jugador{
  private static int money;
  static final double MAX = 99999999;
  private String dni;
  //Constructor
  Jugador(String dni_){
    if(!validateDni(dni_)) {
      dni_=randomDni();
    }
    dni=dni_;
    money=500;
  }
  public static int getMoney() {return money;}
  /**
   * Function that validate if the dni which is passed by parameter is correct or incorrect
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