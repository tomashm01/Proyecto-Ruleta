package project;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
	  int n;
	  do {
	    n=mainMenu();
	    switch(n){
	      case 1:
	        System.out.println("Ruleta");
	        break;
	      case 2:
	        System.out.println("Persona");
	        break;
	      case 3:
	        System.out.println("Adios");
	        break;
	    }
	  }while(n!=3);
	}

	static int mainMenu() {
		Menu main = new Menu("--MAIN MENU--", "Play roulette", "Person menu");
		return main.manage();
	}
	static int rouletteMenu() {
	  Menu roulette=new Menu("--ROULETTE MENU--","Color bet","Even or odd bet","Higher or lower bet");
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