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
public class Jugador extends Persona {
  private int money;
  static ArrayList <Jugador> players=new ArrayList<Jugador>();
  //Constructor
  Jugador(String dni_,int money_){
    super(dni_);
    if(money_<=0) {
      money_=1000;
    }
    money=money_;
  }
  
  //Getters
  public int getMoney() {return money;}
  
  //Setters
  public boolean setMoney(int money_) {
    if(money_<=0) {
      return false;
    }
    money=money_;
    return true;
  }
  /**
   * This function add a Player to the List
   * @param Jugador
   */
  static void addJugadorToList(Jugador j) {
    if(j.getAge()>=18) {
      players.add(j);
      System.out.println("Player added successfully");
    }
  }
  /**
   * This function erase a player of the list
   * @param Jugador
   * @return boolean
   */
  static boolean erasePersona(Jugador aux){
    for(int i=0;i<players.size();i++){
      if(aux.getDni().equals(players.get(i).getDni())){
        players.remove(i);//Erase the element in the i position
        //Person erased successfully
        return true;
      }
    } 
    //Person dont found in the list
    return false;
  }
}