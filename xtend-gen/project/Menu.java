package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("all")
public class Menu {
  private String title = null;
  
  private List<String> options;
  
  public Menu(final String title, final String... options) {
    this.title = title;
    List<String> _asList = Arrays.<String>asList(options);
    ArrayList<String> _arrayList = new ArrayList<String>(_asList);
    this.options = _arrayList;
  }
  
  private void showMenu() {
    throw new Error("Unresolved compilation problems:"
      + "\n+ cannot be resolved."
      + "\n< cannot be resolved."
      + "\n+ cannot be resolved."
      + "\n++ cannot be resolved."
      + "\n+ cannot be resolved.");
  }
  
  /**
   * This function returns a chosen option
   * @return int
   */
  private int selectOption() {
    throw new Error("Unresolved compilation problems:"
      + "\n< cannot be resolved."
      + "\n> cannot be resolved."
      + "\n+ cannot be resolved."
      + "\n+ cannot be resolved."
      + "\n+ cannot be resolved."
      + "\n< cannot be resolved."
      + "\n> cannot be resolved."
      + "\n+ cannot be resolved."
      + "\n|| cannot be resolved"
      + "\n|| cannot be resolved");
  }
  
  /**
   * This function print the menu and returns the select chosen option
   * @return int
   */
  public int manage() {
    this.showMenu();
    return this.selectOption();
  }
}
