package viewControllerTerminal;
/**
 * Authors: Jesús Díaz, Tomás Hidalgo
 * Auxiliary class to print a Menu and choose an option.
 */
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

  public void showMenu() {
    System.out.printf("\n%40s\n\n", this.title);
    options.forEach(option -> System.out.printf("%d.%s\n",options.indexOf(option)+1,option));
  }

  /**
   * This function check if the input option is correct ant returns it
   * 
   * @return chosen Option
   */

  public int selectOption(String customChooseMessage) {
    Scanner s = new Scanner(System.in);
    int chosenOption;
    System.out.printf(customChooseMessage + "(1-%d):",this.options.size());
    chosenOption = Main.getValidNumber();
    return chosenOption;
  }

}

