// --== CS400 File Header Information ==--
// Name: Ryan Cope
// Email: rlcope@wisc.edu
// Team: BB
// Role: Back End
// TA: Bri
// Lecturer: Gary
// Notes to Grader:

/**
 * This class acts as the backend code for our Grocery List application. It
 * contains the methods which allow access to specific points of data from the
 * Red Black Tree and the GroceryItem nodes that it stores.
 * 
 * @author rlcope
 */
public class BackEndRBT {

	protected static RedBlackTree<GroceryItem> tree; // instance of Red Black Tree that all data is added to

	/**
	 * This method takes an input string and uses it to initialize the tree field.
	 * The string input determines how the tree will be initialized, with a default
	 * tree created if a specific key-word is passed in, and a call to the
	 * DataWrangleRBT class to handle the string if it's a file location for data to
	 * be imported.
	 * 
	 * @param csvFileLocation - If it matches the key-word, initialize empty tree.
	 *                        Otherwise look for specified file location.
	 */
	protected static void createTree(String csvFileLocation) {
		if (csvFileLocation.equals("empty")) { // checks for matching key-word
			tree = new RedBlackTree<GroceryItem>();
		} else { // calls DataWrangleRBT.writeCSV to initialize with file at location specified
			tree = DataWrangleRBT.readCSV(csvFileLocation);
		}
	}

	/**
	 * Allows user to insert a specific GroceryItem instance.
	 * 
	 * @param item - GroceryItem to be inserted.
	 */
	protected static void insert(GroceryItem item) {
		tree.insert(item);
	}

	/**
	 * Allows user to print out a list of GroceryItems (and their data) in the tree
	 * 
	 * @return String representation of data from all GroceryItems in tree.
	 */
	protected static String groceryListToString() {
		return tree.toString();
	}

	/**
	 * Allows user to search for a specific GroceryItem from a given UPC, starting at the root node.
	 * 
	 * @param searchUPC - The Integer value that the user wants to search the tree
	 *                  for.
	 * @return The GroceryItem containing the searched for UPC, or null if there is
	 *         no GroceryItem with the UPC.
	 */
	protected static GroceryItem getItem(Integer searchUPC) {
		// method that searches through the tree for the desired item
		if (tree.root != null) {
			if ((int) tree.root.data.getUPC() > (int) searchUPC) {
				if (tree.root.leftChild != null)
					return getItemHelper(searchUPC, tree.root.leftChild);
			} else if ((int) tree.root.data.getUPC() < (int) searchUPC) {
				if (tree.root.rightChild != null)
					return getItemHelper(searchUPC, tree.root.rightChild);
			} else {
				return tree.root.data;
			}
		}
		return null;
	}

	/**
	 * Acts as a helper method for the getItem() method, by allowing recursive calls with a given node.
	 * 
	 * @param searchUPC - The Integer value that the user wants to search the tree
	 *                  for.
	 * @param node - The node of the tree that the user needs to search through.
	 * @return The GroceryItem containing the searched for UPC, or null if there is
	 *         no GroceryItem with the UPC.
	 */
	private static GroceryItem getItemHelper(Integer searchUPC, RedBlackTree.Node<GroceryItem> node) {
		if ((int) node.data.getUPC() > (int) searchUPC) {
			if (node.leftChild != null)
				return getItemHelper(searchUPC, node.leftChild);
		} else if ((int) node.data.getUPC() < (int) searchUPC) {
			if (node.rightChild != null)
				return getItemHelper(searchUPC, node.rightChild);
		} else {
			return node.data;
		}
		return null;
	}

}
