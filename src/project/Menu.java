package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
  private String title = null;
  private List<String> options;

  // Constructor

  public Menu(String title, String... options) {
    this.title = title;
    this.options = new ArrayList<>(Arrays.asList(options));
  }

  private void showMenu() {
    System.out.printf("\n%40s\n\n", ConsoleColors.BLUE_BOLD + this.title);
    for (int i = 0; i < this.options.size(); i++) {
      System.out.printf(ConsoleColors.GREEN + "option %d: %s\n", (i + 1), this.options.get(i));
    }
    // As all menus have the option to finish I will add it by default.
    System.out.printf(
        ConsoleColors.RESET + ConsoleColors.RED + "option %d: Go back" + ConsoleColors.RESET,
        (this.options.size() + 1));
  }

  /**
   * @return chosen Option
   */
  private int selectOption() {
    Scanner s = new Scanner(System.in);
    int chosenOption;
    do {

      System.out.printf(
          ConsoleColors.RESET + ConsoleColors.BLUE_BOLD + "\n\nSelect one option(1-%d):",
          this.options.size() + 1);
      System.out.print(ConsoleColors.RESET + ConsoleColors.YELLOW);
      chosenOption = Main.validateNumber();
      if (chosenOption < 1 || chosenOption > this.options.size() + 1) {
        System.out.println(ConsoleColors.RED + "Error en la entrada" + ConsoleColors.RESET);
        showMenu();
      }

    } while (chosenOption < 1 || chosenOption > this.options.size() + 1);
    return chosenOption;
  }

  /**
   * This function print menu and return given option
   * @return int
   */
  public int manage() {
    showMenu();
    return selectOption();
  }
  
}

