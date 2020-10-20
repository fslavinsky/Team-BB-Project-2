// --== CS400 File Header Information ==--
// Name: Matthew Hillmer
// Email: mhillmer@wisc.edu
// Team: BB
// Role: Front End
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: na
import java.util.Scanner;

/**
 * Front end class that creates a UI for the user to navigate with. It will call RedBlackTree and
 * BackEndRBT to utilize the RBT.
 * 
 * @author swimk
 *
 */
public class FrontEndRBT {
  public static String command = null; // string that stores the command
  public static Scanner scnr = new Scanner(System.in); // input from the user
  public static BackEndRBT data = null; // object that holds the RBT
  public static String cmd =
      "Please select a command. If you do not any commands type 'help' to get a list of commands.";

  /**
   * This method initializes the RBT object and runs a while loop that calls methods to help run the
   * program.
   * 
   * @param args
   */
  public static void main(String[] args) {
    initializeRBT();// create RBT
    boolean exit = false;// boolean to check if program should end
    while (exit == false) {// while loop to run UI commands
      System.out.println("\n" + cmd);// get command
      command = scnr.nextLine().toLowerCase().trim();
      if (command.equals("exit")) {// exit command
        System.out.println(exit());// calls exit command
        exit = true;// change to true to exit program to exit while loop
      }
      if (command.equals("help")) {// help command
        System.out.println(help());// calls the help method
      }
      if (command.equals("insert")) {// insert command
        System.out.println(
            "You have selected to add a new item to the list. Please type the UPC to add:");
        String inputUPC = scnr.nextLine().trim();// gets UPC
        int upcInt = Integer.parseInt(inputUPC);// converts UPC to a int
        System.out.println("Please enter the name of the item to add:");
        String inputName = scnr.nextLine().trim();// gets Name
        System.out.println("Please enter the brand of the item to add:");
        String inputBrand = scnr.nextLine().trim();// gets Brand
        System.out.println("Please enter the department of the item to add:");
        String inputDept = scnr.nextLine().trim();// gets Department
        System.out.println(insert(inputName, inputBrand, inputDept, upcInt));
      }
      if (command.equals("search")) {// search command
        System.out.println("What item would you like to find? Please type the UPC of the item:");
        String upc = scnr.nextLine().trim();// gets UPC
        int upcInt = Integer.parseInt(upc);// converts UPC to a int
        System.out.println(search(upcInt));// call search method helper
      }
      if (command.equals("print")) {// print command
        System.out.println(print());// call print helper method
      }
      if ((!command.equals("help")) && (!command.equals("insert")) && (!command.equals("search"))
          && (!command.equals("print")) && (!command.equals("exit"))) {// checks for proper command
        System.out.println("Must use valid command.");// error statement
      }
    }
  }

  /**
   * print method to print the entire grocery list in level order by calling RBT
   * 
   * @return the RBT string
   */
  public static String print() {
    return BackEndRBT.tree.toString();

  }

  /**
   * method to search the RBT for a specific grocery item via a UPC
   * 
   * @param UPC the UPC to search with
   * @return the grocery item as a string if it exists
   */
  public static String search(int UPC) {
    if (BackEndRBT.getItem(UPC).toString() == null) {// checks if item exists
      return "There is no item on the list using that UPC";
    } else {
      return BackEndRBT.getItem(UPC).toString();
    }
  }

  /**
   * method to insert a new grocery item into the list
   * 
   * @param name  name of the item to be added
   * @param brand brand of the item to be added
   * @param dept  department of the item to be added
   * @param UPC   UPC of the item to be added
   * @return a string that will print to state that the new item was added
   */
  public static String insert(String name, String brand, String dept, Integer UPC) {
    GroceryItem newItem = new GroceryItem(name, brand, dept, UPC);// creates new object
    try {// try block
      BackEndRBT.insert(newItem);// try to insert new object
    } catch (NullPointerException e) {// catch if error
      return "The program threw a NullPointerException";
    }
    return "Succesfully add " + newItem.toString() + " to the grocery list.";

  }

  /**
   * method to print the list of commands for the UI
   * 
   * @return string of the user commands
   */
  public static String help() {
    return "print = print the grocery list in order.\nsearch = look for a specific item in the "
        + "grocery list.\ninsert = insert a new grocery item into the grocery"
        + " list.\nhelp = get a list of commands.\nexit = exit the program.";

  }

  /**
   * method to exit the program
   * 
   * @return print statement
   */
  public static String exit() {
    return "Exiting the program. Have a good day!";
  }

  /**
   * creates a new RBT either empty or via a csv file, calls the wrangler method and backEnd to
   * implement
   */
  public static void initializeRBT() {
    System.out.println(
        "Now running \"My Grocery List\". Would you like create a new grocery list or import a grocery list?"
            + "\nTo create a new grocery list, enter new, and to import a grocery list, enter import.");
    command = scnr.nextLine().toLowerCase().trim();// scanner
    boolean inputRBT = false;// checker if the tree was implemented
    while (inputRBT == false) {// while loop to check if tree has been made
      if (command.equals("import")) {// if importing csv
        System.out.println(
            "Please input the file path of the csv file you would like to scan for the grocery list.");
        String filePath = scnr.nextLine().trim();// scanner for file path
        BackEndRBT.createTree(filePath);// calls BackEndRBT to implement the RBT from CSV
        if (data != null) {// if implemented exit
          inputRBT = true;// change checker
        }
        if (data == null) {// if it did not work
          System.out.println("Invalid file path. Please start over.");// error statement
        }
      }
      if (command.equals("new")) {// if empty is selected
        BackEndRBT.createTree("empty");// creates new RBT
        inputRBT = true;// change checker
        if (data != null) {// if implemented
          inputRBT = true;
        }
      }
      if ((!command.equals("new")) && (!command.equals("import"))) {// if neither command is entered
        System.out.println("Please input a valid command.");// error statement
        command = scnr.nextLine().toLowerCase().trim();// scanner for command
      }
    }
    System.out.println("Your grocery list has been created.");
  }
}
