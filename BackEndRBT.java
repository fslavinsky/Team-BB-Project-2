// --== CS400 File Header Information ==--
// Name: Ryan Cope
// Email: rlcope@wisc.edu
// Team: BB
// Role: Back End
// TA: Bri
// Lecturer: Gary
// Notes to Grader: GroceryItem class written by Frank Slavinsky
public class BackEndRBT {
	protected static RedBlackTree<GroceryItem> tree;

	protected static void createTree(String csvFileLocation) {
		if (csvFileLocation.equals("something")) { // something that signifies a default tree
			tree = new RedBlackTree<GroceryItem>();
		} else {
			tree = DataWrangleRBT.writeCSV(csvFileLocation);
		}
	}

	protected static void insert(GroceryItem item) {
		tree.insert(item);
	}

//	if unable to access tree from front end, create a toString method which calls the RBT toString method

	protected static GroceryItem getItem(Integer searchUPC) {
		// method that searches through the tree for the desired item
		return null;
		// should return a GroceryItem
	}
}
