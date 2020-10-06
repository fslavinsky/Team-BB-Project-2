// --== CS400 File Header Information ==--
// Name: Frank Slavinsky
// Email: fslavinsky@wisc.edu
// Team: BB
// Role: Test Engineer
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: N/A
import static org.junit.jupiter.api.Assertions.*; // TODO delete the ".api" ones, uncomment others
import org.junit.jupiter.api.Test;

// import org.junit.Test;
// import static org.junit.Assert.*;
/**
 * This class contains junit 5 tests for the classes FrontEndRBT, BackEndRBT, and DataWrangleRBT.
 * 
 * @author Frank Slavinsky
 */
class TestSuiteRBT {
  /**
   * This method tests the functionality of FrontEndRBT, specifically that the correct confirmation
   * or error messages are returned to the user. Also tests that correct data contents are returned,
   * and that list of commands is returned when prompted by the user.
   */
  @Test
  void testFrontEnd() { // TODO check out "void" vs "protected/public void..."
    if (FrontEndRBT.help() == null)
      fail("FE 'help' failed to print list of valid commands.");
    if (!FrontendRBT.insert("KEY", "DATA").contentEquals("CONFIRMATION MESSAGE"))
      fail("FE 'insert' failed to print confirmation msg. Key-value paird should have been added.");
    if (!FrontendRBT.insert("KEY", "DATA").contentEquals("ERROR MESSAGE"))
      fail("FE 'insert' failed to print error msg. Key-value pair should not have been added.");
    if (!FrontendRBT.search("KEY").contentEquals("NODE CONTENTS"))
      fail("FE 'search' failed to print contents of node. " + FrontendRBT.search("KEY")
          + " returned instead.");
    if (!FrontendRBT.insert("KEY").contentEquals("ERROR MESSAGE"))
      fail("FE 'search' failed to print error msg. " + FrontendRBT.search("KEY")
          + " returned instead.");
    // BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
    // tree.insert(42);
    // tree.insert(27);
    // tree.insert(64);
    // tree.insert(16);
    // tree.insert(32);
    // tree.insert(57);
    // tree.insert(82);
    // tree.rotate(tree.root.leftChild, tree.root);
    // String correct = "[27, 16, 42, 32, 64, 57, 82]";
    // if (!tree.toString().equals(correct))
    // fail("Right rotation performed incorrectly.");
  }

}
