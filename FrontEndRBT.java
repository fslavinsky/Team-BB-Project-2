// --== CS400 File Header Information ==--
// Name: Matthew Hillmer
// Email: mhillmer@wisc.edu
// Team: BB
// Role: Front End
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: na
import java.util.Scanner;

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
    initializeRBT();
    boolean exit = false;
    while (exit == false) {
      System.out.println("\n" + cmd);
      command = scnr.nextLine().toLowerCase().trim();
      if (command.equals("exit")) {
        System.out.println(exit());
        exit = true;
      }
      if (command.equals("help")) {
        System.out.println(help());
      }
      if (command.equals("insert")) {
        System.out.println(
            "You have selected to add a new item to the list. Please type the UPC to add:");
        String inputUPC = scnr.nextLine().trim();
        int upcInt = Integer.parseInt(inputUPC);
        System.out.println("Please enter the name of the item to add:");
        String inputName = scnr.nextLine().trim();
        System.out.println("Please enter the brand of the item to add:");
        String inputBrand = scnr.nextLine().trim();
        System.out.println("Please enter the department of the item to add:");
        String inputDept = scnr.nextLine().trim();
        System.out.println(insert(inputName, inputBrand, inputDept, upcInt));
      }
      if (command.equals("search")) {
        System.out.println("What item would you like to find? Please type the UPC of the item:");
        String upc = scnr.nextLine().trim();
        int upcInt = Integer.parseInt(upc);
        System.out.println(search(upcInt));
      }
      if (command.equals("print")) {
        System.out.println(print());
      }
      if ((!command.equals("help")) && (!command.equals("insert")) && (!command.equals("search"))
          && (!command.equals("print")) && (!command.equals("exit"))) {
        System.out.println("Must use valid command.");
      }
    }
  }

  public static String print() {
    return data.toString();

  }

  public static String search(int UPC) {
    if (BackEndRBT.getItem(UPC).toString() == null) {
      return "There is no item on the list using that UPC";
    } else {
      return BackEndRBT.getItem(UPC).toString();
    }
  }

  public static String insert(String name, String brand, String dept, Integer UPC) {
    GroceryItem newItem = new GroceryItem(name, brand, dept, UPC);
    data.insert(newItem);
    return "Succesfully add " + newItem.toString() + " to the grocery list.";

  }

  public static String help() {
    return "print = print the grocery list in order.\nsearch = look for a specific item in the "
        + "grocery list.\ninsert = insert a new grocery item into the grocery"
        + " list.\nhelp = get a list of commands.\nexit = exit the program.";

  }

  public static String exit() {
    return "Exiting the program. Have a good day!";
  }

  public static void initializeRBT() {
    System.out.println(
        "Now running \"My Grocery List\". Would you like create a new grocery list or import a grocery list?"
            + "\nTo create a new grocery list, enter new, and to import a grocery list, enter import.");
    command = scnr.nextLine().toLowerCase().trim();
    boolean inputRBT = false;
    while (inputRBT == false) {
      if (command.equals("import")) {
        System.out.println(
            "Please input the file path of the csv file you would like to scan for the grocery list.");
        String filePath = scnr.nextLine().trim();
        BackEndRBT.createTree(filePath);
        if (data != null) {
          inputRBT = true;
        }
        if (data == null) {
          System.out.println("Invalid file path. Please start over.");
        }
      }
      if (command.equals("new")) {
        data = new BackEndRBT();
        if (data != null) {
          inputRBT = true;
        }
      }
      if ((!command.equals("new")) && (!command.equals("import"))) {
        System.out.println("Please input a valid command.");
        command = scnr.nextLine().toLowerCase().trim();
      }
    }
    System.out.println("Your grocery list has been created.");
  }
}
