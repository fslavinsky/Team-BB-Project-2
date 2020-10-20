// --== CS400 File Header Information ==--
// Name: Frank Slavinsky
// Email: fslavinsky@wisc.edu
// Team: BB
// Role: Test Engineer
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: N/A
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Random;
import org.junit.Test;

/**
 * This class contains Junit5 tests for the classes BackEndRBT.java, FrontEndRBT.java, and
 * DataWrangle.java. Tests for each class are contained in a respective method. In each test method,
 * each method for the respective class is tested. NOTE: This test class was designed for the
 * following: Data Wrangler 1, Back End 2, and Front End 1. 
 * 
 * @author Frank Slavinsky
 */
public class TestSuiteRBT {

  /**
   * This method tests the functionality of FrontEndRBT, specifically that the correct confirmation
   * or error messages are returned to the user. Also tests that correct data contents are returned,
   * and that list of commands is returned when prompted by the user. Note, I did not test the print
   * method as that method is effectively tested via my back end tests (see below).
   */
  @Test
  public void testFrontEnd() {
    // test help command
    if (FrontEndRBT.help() == null)
      fail("FE 'help' failed to print list of valid commands.");

    // test insert command on new tree
    String insertConfirmMsg = "Succesfully add Gala Apple to the grocery list.";
    String insertErrorMsg = "This item already exists in your list!";
    BackEndRBT.createTree("empty");
    if (!FrontEndRBT.insert("Gala Apple", "US Fruit Comp.", "Produce", 12)
        .contentEquals(insertConfirmMsg))
      fail("FE 'insert' failed to print confirmation msg. GroceryItem should have been added.");
    if (!FrontEndRBT.insert("Gala Apple", "US Fruit Comp.", "Produce", 12)
        .contentEquals(insertErrorMsg))
      fail("FE 'insert' failed to print error msg. GroceryItem should not have been added.");

    // test search command
    if (!FrontEndRBT.search(12).contentEquals("Gala Apple"))
      fail("FE 'search' failed to print contents of node. " + FrontEndRBT.search(12)
          + " returned instead.");
    if (!FrontEndRBT.search(11).contentEquals("There is no item on the list using that UPC"))
      fail("FE 'search' failed to print error msg. " + FrontEndRBT.search(11)
          + " returned instead.");

    // test search command on tree initialized via DataWrangleRBT methods.
    BackEndRBT.tree = null; // first, reset the tree
    DataWrangleRBT.rbTree = new RedBlackTree<GroceryItem>();
    BackEndRBT.createTree("test_file.csv");

    if (!FrontEndRBT.search(4011).contentEquals("banana"))
      fail("FE 'search' failed to print contents of node. " + FrontEndRBT.search(4011)
          + " returned instead.");
    if (!FrontEndRBT.search(11).contentEquals("There is no item on the list using that UPC"))
      fail("FE 'search' failed to print error msg. " + FrontEndRBT.search(11)
          + " returned instead.");
  }

  /**
   * This method tests the functionality of DataWranlgeRBT by testing the classes two methods:
   * readCSV, which creates and returns a RBT using data stored in a CSV file, and writeCSV which
   * creates and returns a RBT using an array list of String arrays which contain the data fields
   * for grocery items.
   */
  @Test
  public void testDataWrangle() {
    // test readCSV
    RedBlackTree<GroceryItem> treeFromCSV = DataWrangleRBT.readCSV("test_file.csv");
    if (treeFromCSV == null) // readCSV was passed a valid file path, this should not happen.
      fail("readCSV was passed valid file path but threw FileIOException.");
    if (!treeFromCSV.toString().contentEquals("[Honey Nut Cheerios, 2% Milk, banana]"))
      fail("readCSV failed to correctly insert grocery items from valid csv file. Instead added "
          + treeFromCSV.toString());
    treeFromCSV = DataWrangleRBT.readCSV("src/INVALID_PATH");
    if (treeFromCSV != null)
      fail("readCSV was passed invalid file path and failed to throw FileIOException.");

    // test writeCSV
    ArrayList<String[]> rawGroceryList = new ArrayList<String[]>(3); // initialize ArrayList
    String[] rawBanana = {"banana", "Chiquita", "Produce", "4011"}; // create String[]s
    String[] rawMilk = {"2% Milk", "Lammers", "Dairy", "10"};
    String[] rawCheerios = {"Honey Nut Cheerios", "Kellogs", "Dry", "14"};
    rawGroceryList.add(rawBanana); // add string[]s to array list
    rawGroceryList.add(rawCheerios);
    rawGroceryList.add(rawMilk);

    DataWrangleRBT.rbTree = new RedBlackTree<GroceryItem>(); // re-initialize RBT

    RedBlackTree<GroceryItem> treeFromArray = DataWrangleRBT.writeCSV(rawGroceryList);
    if (!treeFromArray.toString().contentEquals("[Honey Nut Cheerios, 2% Milk, banana]"))
      fail("writeCSV failed to correctly initalize tree from arraylist of string[]'s.");
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
      } catch (IllegalArgumentException e) {
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
