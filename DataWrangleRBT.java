import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
// --== CS400 File Header Information ==--
// Name: Jacob Revnew
// Email: revnew@wisc.edu
// Team: Team: BB
// TA: Bri Cochran
// Lecturer: Florian Heirmerl
// Notes to Grader: <optional extra notes>

/**
 * Will take data from a file or from the user and create an new RedBlackTree or add to it
 * 
 * @author JacRe
 *
 */
public class DataWrangleRBT {
  protected static RedBlackTree<GroceryItem> tree; // private class variable of Red Black Tree

  /**
   * Constructor that initiates a new Red Black Tree with nodes of GroceryItem
   * 
   */
  public DataWrangleRBT() {
    tree = new RedBlackTree<GroceryItem>();
  }

  /**
   * Reads a file of the grocery list and puts the values into a Red Black Tree
   * 
   * @param filePath - path to the file given by user
   * @return - the filled red black tree
   */
  public static RedBlackTree<GroceryItem> readCSV(String filePath) {
    try {
      // CSV file delimiter
      String DELIMITER = ",";

      // create a reader
      BufferedReader br = Files.newBufferedReader(Paths.get(filePath));

      // read the file line by line
      String line = br.readLine(); // clears out the line with column titles

      while ((line = br.readLine()) != null) {
        // convert line into tokens
        String[] tokens = line.split(DELIMITER);
        GroceryItem item =
            new GroceryItem(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3])); // create
                                                                                           // new
                                                                                           // grocery
                                                                                           // item
                                                                                           // with
                                                                                           // the
                                                                                           // given
                                                                                           // information
        tree.insert(item);; // Insert item into Red Black Tree
      }

      // close the reader
      br.close();

    } catch (IOException ex) {
      return null; // this tells the front end that the file was not found
    }
    return tree;
  }

  /**
   * Takes given information provided by the user and inserts Grocery Items into the red black tree
   * 
   * @param list - ArrayList of string arrays with the needed information to create GroceryItems to
   *             add to the red black tree
   * @return - returns the tree after a node is inserted
   */
  public static RedBlackTree<GroceryItem> writeCSV(ArrayList<String[]> list) {
    for (int i = 0; i < list.size(); ++i) { // goes through each array in the ArrayList
      GroceryItem item = new GroceryItem(list.get(i)[0], list.get(i)[1], list.get(i)[2],
          Integer.parseInt(list.get(i)[3])); // creates a new GroceryItem
      tree.insert(item); // inserts GroceryItem item into the red black tree
    }

    return tree;
  }
}
