package project;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		menu();
	}

	static void menu() {
		Menu mainMenu = new Menu("--MAIN MENU--", "Play roulette", "Person menu");
		mainMenu.manage();
	}

	static void menuChanges() {
		System.out.println(ConsoleColors.BLUE_BOLD + "                --PERSON CHANGE MENU-- ");
		System.out.println(ConsoleColors.RESET);
		System.out.println(ConsoleColors.YELLOW + "1.Change name");
		System.out.println(ConsoleColors.RESET);
		System.out.println(ConsoleColors.YELLOW + "2.Change surnames");
		System.out.println(ConsoleColors.RESET);
		System.out.println(ConsoleColors.YELLOW + "3.Change age");
		System.out.println(ConsoleColors.RESET);
		System.out.println(ConsoleColors.YELLOW + "4.Change location");
		System.out.println(ConsoleColors.RESET);
		System.out.println(ConsoleColors.YELLOW + "5.Change Dni");
		System.out.println(ConsoleColors.RESET);
		System.out.println(ConsoleColors.RED + "6.Go back");
		System.out.println(ConsoleColors.RESET);
	}

	static void menuPersona() {
		System.out.println(ConsoleColors.BLUE_BOLD + "                --PERSON MENU-- ");
		System.out.println(ConsoleColors.RESET);
		System.out.println(ConsoleColors.WHITE + "1.Add Player");
		System.out.println(ConsoleColors.RESET);
		System.out.println(ConsoleColors.WHITE + "2.Erase player");
		System.out.println(ConsoleColors.RESET);
		System.out.println(ConsoleColors.WHITE + "3.Print all the players");
		System.out.println(ConsoleColors.RESET);
		System.out.println(ConsoleColors.WHITE + "4.Change options of the person");
		System.out.println(ConsoleColors.RESET);
		System.out.println(ConsoleColors.RED + "5.Go back");
		System.out.println(ConsoleColors.RESET);
	}
}