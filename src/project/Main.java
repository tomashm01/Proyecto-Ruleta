package project;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		mainMenu();
	}

	static void mainMenu() {
		Menu main = new Menu("--MAIN MENU--", "Play roulette", "Person menu");
		main.manage();
	}
	static void rouletteMenu() {
	  Menu roulette=new Menu("--ROULETTE MENU--","Color bet","Even or odd bet","Higher or lower bet");
	  roulette.manage();
	}
	static void personMenu() {
	  Menu person=new Menu("--PERSON MENU--","Add player","Erase player","Print all the players","Change options of the person");
	  person.manage();
	}
	static void changePersonMenu() {
	  Menu change=new Menu("--CHANGE PERSON MENU--","Change dni","Change name","Change surnames","Change age","Change location");
	  change.manage();
	}
	
	
}