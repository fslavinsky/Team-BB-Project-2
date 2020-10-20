<<<<<<< Updated upstream
import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;

public class TestSuiteRBT {

  /**
   * This method tests the functionality of FrontEndRBT, specifically that the correct confirmation
   * or error messages are returned to the user. Also tests that correct data contents are returned,
   * and that list of commands is returned when prompted by the user.
   */
  @Test
  public void testFrontEnd() {
    // test help command
    // if (FrontEndRBT.help() == null)
    // fail("FE 'help' failed to print list of valid commands.");
    // // test insert command
    // if (!FrontEndRBT.insert("Gala Apple", "US Fruit Comp.", "Produce", 12)
    // .contentEquals("CONFIRMATION MESSAGE"))
    // fail("FE 'insert' failed to print confirmation msg. Key-value paird should have been
    // added.");
    // if (!FrontEndRBT.insert("Granny Smith", "US Fruit Comp.", "Produce", 12)
    // .contentEquals("ERROR MESSAGE"))
    // fail("FE 'insert' failed to print error msg. Key-value pair should not have been added.");
    // // test search command
    // if (!FrontEndRBT.search(12).contentEquals("NODE CONTENTS"))
    // fail("FE 'search' failed to print contents of node. " + FrontEndRBT.search(12)
    // + " returned instead.");
    // if (!FrontEndRBT.search(11).contentEquals("ERROR MESSAGE"))
    // fail("FE 'search' failed to print error msg. " + FrontEndRBT.search(11)
    // + " returned instead.");
  }

  /**
   * This method tests the functionality of DataWranlgeRBT, specifically that the correct
   * confirmation or error messages are returned to the user. Also tests that correct data contents
   * are returned, and that list of commands is returned when prompted by the user.
   */
  @Test
  public void testFrontDataWrangle() {
    // test readCSV TODO figure out the outputs from readCSV-- are they booleans or exceptions?

    // test writeCSV TODO figure out the outputs from readCSV-- are they booleans or exceptions?

    // test GroceryItem TODO figure out what the get methods are, what the set methods are, etc
  }

  /**
   * This method tests the functionality of FrontEndRBT, specifically that the correct confirmation
   * or error messages are returned to the user. Also tests that correct data contents are returned,
   * and that list of commands is returned when prompted by the user.
   */
  @Test
  public void testBackEndRBT() {
    // test createTree method
    BackEndRBT.createTree("empty");
    if (BackEndRBT.tree == null)
      fail("Error!! Tree failed to intialize.");

    // test insert method
    GroceryItem banana = new GroceryItem("banana", "Chiquita", "Produce", 4011);
    BackEndRBT.insert(banana);
    if (!BackEndRBT.tree.toString().equals("[banana]")) // test valid insert
      fail("Error!! BackEnd failed to insert GroceryItem.");
    try {
      BackEndRBT.insert(banana); // test invalid insert, exception expected to be thrown
      fail("Error!! BackEnd allowed for item duplication.");
    } catch (IllegalArgumentException e) { // test passed, do nothing
    }
    
    // test groceryListToString method
    GroceryItem cheerios = new GroceryItem("Honey Nut Cheerios", "Kellogs", "Dry", 14);
    GroceryItem twoPercentMilk = new GroceryItem("2% Milk", "Lammers", "Dairy", 10);
    BackEndRBT.insert(cheerios);
    BackEndRBT.insert(twoPercentMilk);
    if(!BackEndRBT.groceryListToString().contentEquals("[Honey Nut Cheerios, 2% Milk, banana]"))
      fail("Error!! BackEnd failed to print grocery list correctly");
    
    // test getItem method
    if(!BackEndRBT.getItem(4011).equals(banana))
      fail("Error!! BackEnd failed to return item stored in the tree.");
    if(!BackEndRBT.getItem(14).equals(cheerios))
      fail("Error!! BackEnd failed to return item stored in the tree.");
    if(!BackEndRBT.getItem(10).equals(twoPercentMilk))
      fail("Error!! BackEnd failed to return item stored in the tree.");
    if(BackEndRBT.getItem(9) != null)
      fail("Error!! BackEnd returned a GroceryItem that does not exist in the tree.");
    
    // add more GroceryItem objects to test robustness of getItem method
    for(int i = 0; i < 500; i++) {
      Random random = new Random();
      Integer randomInt = random.nextInt(100000000) + 1;
      GroceryItem dummy = new GroceryItem("dummy", "dummy", "dummy", randomInt);
      BackEndRBT.insert(dummy);
      if(!BackEndRBT.getItem(randomInt).equals(dummy))
        fail("Error! BackEnd failed to return item stored in the tree");
    }
    
    // test getItem on an empty tree
    BackEndRBT.tree = new RedBlackTree<GroceryItem>();
    if(BackEndRBT.getItem(4011) != null)
      fail("Error!! Empty tree, thus null should have been returned."); 
  }

}
=======
// --== CS400 File Header Information ==--
// Name: Frank Slavinsky
// Email: fslavinsky@wisc.edu
// Team: BB
// Role: Test Engineer
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: N/A
import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;

/**
 * @author fslav
 *
 */
public class TestSuiteRBT {

  /**
   * This method tests the functionality of FrontEndRBT, specifically that the correct confirmation
   * or error messages are returned to the user. Also tests that correct data contents are returned,
   * and that list of commands is returned when prompted by the user.
   */
  @Test
  public void testFrontEnd() {
    // test help command
    // if (FrontEndRBT.help() == null)
    // fail("FE 'help' failed to print list of valid commands.");
    // // test insert command
    // if (!FrontEndRBT.insert("Gala Apple", "US Fruit Comp.", "Produce", 12)
    // .contentEquals("CONFIRMATION MESSAGE"))
    // fail("FE 'insert' failed to print confirmation msg. Key-value paird should have been
    // added.");
    // if (!FrontEndRBT.insert("Granny Smith", "US Fruit Comp.", "Produce", 12)
    // .contentEquals("ERROR MESSAGE"))
    // fail("FE 'insert' failed to print error msg. Key-value pair should not have been added.");
    // // test search command
    // if (!FrontEndRBT.search(12).contentEquals("NODE CONTENTS"))
    // fail("FE 'search' failed to print contents of node. " + FrontEndRBT.search(12)
    // + " returned instead.");
    // if (!FrontEndRBT.search(11).contentEquals("ERROR MESSAGE"))
    // fail("FE 'search' failed to print error msg. " + FrontEndRBT.search(11)
    // + " returned instead.");
  }

  /**
   * This method tests the functionality of DataWranlgeRBT TODO finish descrpt.
   */
  @Test
  public void testFrontDataWrangle() {
    // test readCSV TODO figure out the outputs from readCSV-- are they booleans or exceptions?

    // test writeCSV TODO figure out the outputs from readCSV-- are they booleans or exceptions?

    // test GroceryItem TODO figure out what the get methods are, what the set methods are, etc
  }

  /**
   * This method tests the functionality of BackEndRBT, specifically the createTree, insert,
   * grocerListToString, and getItem methods. 
   */
  @Test
  public void testBackEndRBT() {
    // test createTree method
    BackEndRBT.createTree("empty");
    if (BackEndRBT.tree == null)
      fail("Error!! Tree failed to intialize.");

    // test insert method
    GroceryItem banana = new GroceryItem("banana", "Chiquita", "Produce", 4011);
    BackEndRBT.insert(banana);
    if (!BackEndRBT.tree.toString().equals("[banana]")) // test valid insert
      fail("Error!! BackEnd failed to insert GroceryItem.");
    try {
      BackEndRBT.insert(banana); // test invalid insert, exception expected to be thrown
      fail("Error!! BackEnd allowed for item duplication.");
    } catch (IllegalArgumentException e) { // test passed, do nothing
    }

    // test groceryListToString method
    GroceryItem cheerios = new GroceryItem("Honey Nut Cheerios", "Kellogs", "Dry", 14);
    GroceryItem twoPercentMilk = new GroceryItem("2% Milk", "Lammers", "Dairy", 10);
    BackEndRBT.insert(cheerios);
    BackEndRBT.insert(twoPercentMilk);
    if (!BackEndRBT.groceryListToString().contentEquals("[Honey Nut Cheerios, 2% Milk, banana]"))
      fail("Error!! BackEnd failed to print grocery list correctly.");

    // test getItem method
    if (!BackEndRBT.getItem(4011).equals(banana))
      fail("Error!! BackEnd failed to return item stored in the tree.");
    if (!BackEndRBT.getItem(14).equals(cheerios))
      fail("Error!! BackEnd failed to return item stored in the tree.");
    if (!BackEndRBT.getItem(10).equals(twoPercentMilk))
      fail("Error!! BackEnd failed to return item stored in the tree.");
    if (BackEndRBT.getItem(9) != null) // GroceryItem with this UPC does not exist in the tree
      fail("Error!! BackEnd returned a GroceryItem that does not exist in the tree.");

    // add more GroceryItem objects to test robustness of getItem method
    for (int i = 0; i < 500; i++) {
      Random random = new Random();
      Integer randomInt = random.nextInt(100000000) + 1;
      GroceryItem dummy = new GroceryItem("dummy", "dummy", "dummy", randomInt);
      try {
      BackEndRBT.insert(dummy);
      } catch(IllegalArgumentException e) {
        // random selection of int for UPC resulted in repeated value, ignore for purpose of test
      }
      if (!BackEndRBT.getItem(randomInt).equals(dummy))
        fail("Error! BackEnd failed to return item stored in the tree.");
    }

    // test getItem on an empty tree
    BackEndRBT.tree = new RedBlackTree<GroceryItem>();
    if (BackEndRBT.getItem(4011) != null)
      fail("Error!! Empty tree, thus null should have been returned.");
  }

}
>>>>>>> Stashed changes
