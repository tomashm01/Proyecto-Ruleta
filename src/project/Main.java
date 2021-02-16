package project;
import java.util.Scanner;
public class Main{
	public static void main(String[] args) {
	  int principal,roulette;
	  Scanner s=new Scanner(System.in);
	  String dni="",color="",evenOdd="",HighLow="";
	  Jugador aux=new Jugador("",1000);
	  int credit=0,finalMoney;
	  do {
	    principal=mainMenu();
	    switch(principal){
	      case 1://Menu ruleta
	        if(Jugador.players.size()==0) { //Si la lista esta vacia -> out
	          System.out.println(ConsoleColors.RED+"You need to add a player in the list"+ConsoleColors.RESET);
	          break;
	        }
	        do {//Bucle para validar que el jugador con ese dni existe y tiene dinero en su saldo
	          do {//Bucle para validar el dni
	            System.out.println("Please introduce a valid dni: ");
	            dni=s.nextLine();
	          }while(!Persona.validateDni(dni));
	          aux=new Jugador(dni,-1);
	        }while(!Jugador.players.contains(aux.getDni()) || aux.getMoney()==0);
	        do {
	          roulette=rouletteMenu();
	          switch(roulette) {//Menu ruleta
	            //Hay que mejorar esta parte para saber si el jugador tiene ese credito
                //Hay que crearlo en una funcion de la clase ruleta
	            case 1://Apuesta rojo o negro
	              
	                System.out.println("Introduce a credit to bet: ");
	                credit+=s.nextInt();
	                s.nextLine();
	                finalMoney=aux.getMoney()-credit;
	                if((aux.getMoney()-finalMoney)<=0) {
	                  System.out.println("You have not got this credit");
	                  continue;
	                }
                    aux.setMoney(finalMoney);
	              do {
	                System.out.println("Introduce a type of bet: RED || BLACK");
	                color=s.nextLine();
	                color.toUpperCase();
	              }while(!color.equals("RED") || !color.equals("BLACK"));
	              break;
	            case 2://Apuesta par o impar
	              System.out.println("Introduce a credit to bet: ");
                  credit+=s.nextInt();
                  s.nextLine();
                  finalMoney=aux.getMoney()-credit;
                  if((aux.getMoney()-finalMoney)<=0) {
                    System.out.println("You have not got this credit");
                    continue;
                  }
                  aux.setMoney(finalMoney);
                do {
                  System.out.println("Introduce a type of bet: EVEN || ODD");
                  evenOdd=s.nextLine();
                  evenOdd.toUpperCase();
                }while(!color.equals("EVEN") || !color.equals("ODD"));
	              break;
	            case 3://Apuesta mayor o menor
	              System.out.println("Introduce a credit to bet: ");
                  credit+=s.nextInt();
                  s.nextLine();
                  finalMoney=aux.getMoney()-credit;
                  if((aux.getMoney()-finalMoney)<=0) {
                    System.out.println("You have not got this credit");
                    continue;
                  }
                  aux.setMoney(finalMoney);
                do {
                  System.out.println("Introduce a type of bet: HIGH(19-36)|| LOW(1-19)");
                  HighLow=s.nextLine();
                  HighLow.toUpperCase();
                }while(!color.equals("HIGH") || !color.equals("LOW"));
	              break;
	            case 4://Girar ruleta y sacar un numero
	              break;
	          }
	        }while(roulette!=5);
	        break;
	      case 2://Menu persona
	        System.out.println("Persona");
	        break;
	      case 3://Fin programa
	        System.out.println("Adios");
	        break;
	    }
	  }while(principal!=3);
	}

	static int mainMenu() {
		Menu main = new Menu("--MAIN MENU--", "Play roulette", "Person menu");
		return main.manage();
	}
	static int rouletteMenu() {
	  Menu roulette=new Menu("--ROULETTE MENU--","Color bet","Even or odd bet","Higher or lower bet","Spin roulette");
	  return roulette.manage();
	}
	static int personMenu() {
	  Menu person=new Menu("--PERSON MENU--","Add player","Erase player","Print all the players","Change options of the person");
	  return person.manage();
	}
	static int changePersonMenu() {
	  Menu change=new Menu("--CHANGE PERSON MENU--","Change dni","Change name","Change surnames","Change age","Change location");
	  return change.manage();
	}
	
	
}