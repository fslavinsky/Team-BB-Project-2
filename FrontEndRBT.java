// --== CS400 File Header Information ==--
// Name: Rohan Nadgir
// Email: nadgir@wisc.edu
// Team: BB
// TA: Bri Cochran
// Lecturer: Gary Dahl
// Notes to Grader:

import java.util.Scanner;
/*
 * Front End code for our Grocery List program. The user is given multiple
 * choices, and based on whether they choose to quit, create a new list, insert
 * a new node to the list, search for an item in the list, or print the list, 
 * the respective if or else-if statement will run.
 */
public class FrontEndRBT {

    public static void main(String[] args) {
        boolean quit = false;
        String command = null;
        Scanner scnr = new Scanner(System.in);
        
        //Option prompt
        String option = "What would you like to do?";

        System.out.println("Welcome to My Grocery List! What would you like to do? "
            + "To get help regarding the list of commands, please type \"h\"");
        
        //Asks for what the user's input is
        command = scnr.nextLine().toLowerCase().trim();
        
        while (quit == false) {
            
            //User chooses to quit
            if(command.equals("q")) {
                quit = true;
            }
            
            /*User chooses to get the list of all commands. A public 
            * helper method is incorporated into this file to comply with 
            * the TestSuiteRBT.java file. This helper method simply calls 
            * BackEndRBT.help (second to last method in this file).
            */
            else if(command.equals("h")) {
                System.out.println(help());
                System.out.println("");
                System.out.println(option);
                command = scnr.nextLine().toLowerCase().trim();
            }
            
            //User chooses to create a new list
            else if(command.equals("c")) {
                BackEndRBT.createTree("empty");
                System.out.println("A new grocery list has been successfully \n"
                    + "created.");
                System.out.println("");
                System.out.println(option);
                command = scnr.nextLine().toLowerCase().trim();
                }  
            
            /*User chooses to add a new item via a CSV or by simply inputting
             * the item's name, brand, department, and its UPC (Universal 
             * Product) Number. If CSV is chosen, the DataWrangleRBT.readCSV 
             * method is called, while if the user chooses to input the item 
             * themselves, then BackEndRBT.insert is called, which calls 
             * RedBlackTree.insert. For regular insert (not CSV), a public 
             * helper method is incorporated into this file to comply with 
             * the TestSuiteRBT.java file. This helper method simply calls 
             * BackEndRBT.insert (third to last method in this file).
             */
            else if(command.equals("i")) {
                System.out.println("Would you like to use a .CSV file? If you type \n"
                    + "no, you will be redirected to the insert option where \n"
                    + "you will be able to insert the item name, brand, \n"
                    + "department, and UPC Number. Please type \"yes\" or \"no\".");
                command = scnr.nextLine().toLowerCase().trim(); 
                if(!command.equals("yes") || !command.equals("no")) {
                    System.out.println("Sorry. This is not a invalid input. Please"
                        + " type yes or no.");
                    command = scnr.nextLine().toLowerCase().trim(); 
                }
                if(command.contentEquals("yes")) {
                    System.out.println("Please input the file path.");
                    command = scnr.nextLine().toLowerCase().trim();
                    DataWrangleRBT.readCSV(command);
                    System.out.println(option);
                    command = scnr.nextLine().toLowerCase().trim();
                }
                else if (command.contentEquals("no")) {
                    System.out.println("Please enter the item name.");
                    String itemName = scnr.nextLine();
                    
                    System.out.println("Please enter the item brand.");
                    String itemBrand = scnr.nextLine();
                    
                    System.out.println("Please enter the department this item "
                        + "is found in.");
                    String itemDepartment = scnr.nextLine();
                    
                    System.out.println("Please enter the item UPC.");
                    String itemUPCString = scnr.nextLine().trim();
                    Integer itemUPCNumber =  Integer.parseInt(itemUPCString);
                    
                    insert(itemName, itemBrand, itemDepartment, itemUPCNumber);
                    
                    System.out.println("Item has been succesfully added.");
                    System.out.println("");
                    System.out.println(option);
                    command = scnr.nextLine().toLowerCase().trim();
                }
            }
            
            /*User can search for a particular item in the grocery list. A
             * public helper method is incorporated into this file to comply 
             * with the TestSuiteRBT.java file. This helper method simply calls 
             * BackEndRBT.search (last method in this file).
             */
            else if(command.equals("s")) {
                System.out.println("Please enter the item UPC number.");
                String UPCString = scnr.nextLine().trim();
                Integer UPCNumber =  Integer.parseInt(UPCString);
                search(UPCNumber);
                System.out.println("");
                System.out.println(option);
                command = scnr.nextLine().toLowerCase().trim();
            }
            
            //User can print their list
            else if(command.equals("p")) {
                System.out.println(BackEndRBT.groceryListToString());
                System.out.println("");
                System.out.println(option);
                command = scnr.nextLine().toLowerCase().trim();  
            }
            
            //Any command that is not 'q', 'c', 'i', 's', or 'p'.
            else {
                System.out.println("Sorry, your input is not a valid command. "
                    + "Please input a valid command. \n " + option);
                command = scnr.nextLine().toLowerCase().trim();
                
            }
        }
        
        System.out.println("Thank you for using My Grocery List!");

    }
    
    public static String insert(String name, String brand, String department, Integer UPCNumber) {
        GroceryItem groceryItem = new GroceryItem(name, brand, department, UPCNumber);
        BackEndRBT.insert(groceryItem);
        return groceryItem.toString();
    }
    
    public static String help() {
        String helpOptions = "These are the list of commands: "
            + "\n c - create a new grocery list "
            + "\n i - insert a new item "
            + "\n s - search for an item "
            + "\n p - print the entire grocery list "
            + "\n q - quit the program.";
        
        return helpOptions;
    }
    
    public static String search(Integer UPCNumber) {
        BackEndRBT.getItem(UPCNumber);
        String upcString = UPCNumber.toString();
        return upcString;
    }
}
