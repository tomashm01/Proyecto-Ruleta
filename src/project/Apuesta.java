package project;

public class Apuesta extends Jugador{
  private int amount;
  private String type;
  Apuesta(String type,int amount){
    if(amount<0 || (super.getMoney()-amount)<0) {
      amount=0;
    }
    this.amount=amount;
    this.type=type;
  }
  
}
