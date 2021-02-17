package project;

import java.util.ArrayList;
import java.util.List;

public class Ruleta {
  //Arraylist 
  static List <Integer> moneyBets= new ArrayList <Integer>();
  static List <Boolean> optionBets= new ArrayList <Boolean>();

  public static void Ruleta() {
	  boolean rojo = false;
	  boolean negro = false;
	  
	  boolean par = false;
	  boolean impar = false;
	  
	  boolean higher = false;
	  boolean lower = false;
	  
	  int color=0;
	  int evenOdd=0;
	  int highLow=0;
	  
	  optionBets.add(rojo);
	  optionBets.add(negro);
	  optionBets.add(par);
	  optionBets.add(impar);
	  optionBets.add(higher);
	  optionBets.add(lower);
	  
	  moneyBets.add(color);
	  moneyBets.add(evenOdd);
	  moneyBets.add(highLow);
  }
  
  /**
   * This function generate a random value for the ball between 0-36
   * 
   * @return int
   */
  static int randomBall() {
    int aleatorio = (int) (0 + Math.random() * (36 - 0) + 1);
    return aleatorio;
  }

  /**
   * This function returns the color of the number
   * 
   * @param n
   * @return String
   */
  static String colorBall(int n) {
    String val = "";
    int size = 18;
    int red[] = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
    int black[] = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
    if (n == 0) {
      val = "GREEN";
      return val;
    }
    for (int i = 0; i < size; i++) {
      if (n == red[i]) {
        val = "RED";
        return val;
      } else if (n == black[i]) {
        val = "BLACK";
        return val;
      }
    }
    return val;
  }

  /**
   * This function return if the number is Even or Odd
   * 
   * @param n
   * @return String
   */
  static String EvenOddBall(int n) {
    String val = "";
    if (n == 0) {
      val = "NULL";
      return val;
    } else if (n % 2 == 0) {
      val = "EVEN";
      return val;
    } else {
      val = "ODD";
      return val;
    }
  }

  /**
   * This function return if the number is between 1-18, 19-36 or is 0.
   * 
   * @param n
   * @return
   */
  static String HigherLowerThan(int n) {
    String val = "";
    if (n == 0) {
      val = "NULL";
      return val;
    } else if (n < 19) {
      val = "LOWER";
      return val;
    } else if (n > 18) {
      val = "HIGHER";
      return val;
    }
    return val;
  }
}
