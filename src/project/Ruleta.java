package project;

/*
 * Name: Ruleta.java
 * 
 * Author: Tomás Hidalgo Martín
 * 
 * Description: Class Ruleta, this class have got a bank(the money os the roulette) and a number(the
 * random number generate).
 * 
 * Version:1.0
 * 
 */
public class Ruleta {
  private int bank, number;

  // Constructor
  Ruleta(int bank_) {
    if (bank_ <= 1000000) {
      bank_ = 1000000;
    }
    bank = bank_;
    number = -1;
  }

  // Getters
  public int getBanca() {
    return bank;
  }

  public int getNum() {
    return number;
  }

  // Setters
  public boolean setBanca(int bank_) {
    if (bank_ < 0) {
      return false;
    }
    bank = bank_;
    return true;
  }

  public boolean setNum(int n) {
    if (n < 0 || n > 36) {
      return false;
    }
    number = n;
    return true;
  }

  /**
   * This function generate a random value for the ball between 0-36
   * 
   * @return int
   */
  static int randomBall() {
    int n = (int) Math.random() % 37;
    return n;
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
