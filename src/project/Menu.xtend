package project

import java.util.ArrayList
import java.util.Arrays
import java.util.List
import java.util.Scanner

class Menu {
	String title = null
	// private String[] options = null;
	List<String> options

	// Constructor
	new(String title, String... options) {
		this.title = title
		this.options = new ArrayList(Arrays::asList(options))
	}

	def private void showMenu() {
		System::out.printf("\n%40s\n\n", ConsoleColors::BLUE_BOLD + this.title)
		for (var int i = 0; i < this.options.size(); i++) {
			System::out.printf('''«ConsoleColors::GREEN»option %d: %s
'''.toString, (i + 1), this.options.get(i))
		}
		// As all menus have the option to finish I will add it by default.
		System::out.printf(
			'''«ConsoleColors::RESET»«ConsoleColors::RED»option %d: Go back«ConsoleColors::RESET»'''.toString,
			(this.options.size() + 1))
	}

	/** 
	 * This function returns a chosen option
	 * @return int
	 */
	def private int selectOption() {
		var Scanner s = new Scanner(System::in)
		var int chosenOption
		do {
			System::out.printf('''«ConsoleColors::RESET»«ConsoleColors::BLUE_BOLD»

Select one option(1-%d):'''.toString, this.options.size() + 1)
			System::out.print(ConsoleColors::RESET + ConsoleColors::YELLOW)
			chosenOption = Main::validateNumber()
			if (chosenOption < 1 || chosenOption > this.options.size() + 1) {
				System::out.println('''«ConsoleColors::RED»Error en la entrada«ConsoleColors::RESET»'''.toString)
				showMenu()
			}
		} while (chosenOption < 1 || chosenOption > this.options.size() + 1)
		return chosenOption
	}

	/** 
	 * This function print the menu and returns the select chosen option
	 * @return int
	 */
	def int manage() {
		showMenu()
		return selectOption()
	}
}
