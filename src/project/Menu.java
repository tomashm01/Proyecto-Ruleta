package project;
import java.util.Arrays;
import java.util.Scanner;
public class Menu {
  private String title = null;
  private String[] options = null;
  // Constructor
  public Menu (String title_, String... options_) {
    this.title = title_;
    this.options = options_;
  }
  /**
   * This function add a new option
   * @param nuevaOpcion
   */
  public void addOption(String newOption){
    this.options = Arrays.copyOf(this.options, this.options.length+1);
    this.options[this.options.length-1] = newOption;  
  }
  
  
  /**
   * This function shows the menu
   */
  private void showMenu() {
    System.out.printf("\n%40s\n\n",ConsoleColors.BLUE_BOLD + this.title);
    for (int i =0 ; i < this.options.length ; i++) {
      System.out.printf(ConsoleColors.GREEN + "option %d: %s\n",(i+1), this.options[i]);
    }
    //As all menus have the option to finish I will add it by default.
    System.out.printf(ConsoleColors.RESET + ConsoleColors.RED + "option %d: Go back" + ConsoleColors.RESET,(this.options.length+1));
  }
  
  private int selectOption() {
   Scanner s = new Scanner(System.in);
   int chosenOption;
   do {
     System.out.printf(ConsoleColors.RESET + ConsoleColors.BLUE_BOLD + "\n\nSelect one option(1-%d):",this.options.length+1);
     System.out.print(ConsoleColors.RESET + ConsoleColors.YELLOW);
     chosenOption = s.nextInt();
   }while (chosenOption < 1 || chosenOption > this.options.length+1);
  return chosenOption; 
  }
  
  public int manage() {
    showMenu();
    return selectOption();
  } 
}

