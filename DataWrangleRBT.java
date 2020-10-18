import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
// --== CS400 File Header Information ==--
// Name: Gabriel Alexander
// Email: gmalexander@wisc.edu
// Team: Team: BB
// TA: Bri
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

public class DataWrangleRBT {
    // private class variable of Red Black Tree
    private static RedBlackTree<GroceryItem> rbTree = new RedBlackTree<GroceryItem>();

    // Constructor
    public DataWrangleRBT() {}

    /**
     * Reads a file containing information about a grocery list and puts the values
     * into a Red Black Tree
     * 
     * @param pathToCsv - path to the csv file 
     * @return - the filled red black tree
     */
    public static RedBlackTree<GroceryItem> readCSV(String pathToCsv) {
        try {
            // creates reader for CSV file
            BufferedReader csvReader = Files.newBufferedReader(Paths.get(pathToCsv));

            // reads first row of file
            String row = csvReader.readLine(); // clears out the line with column titles

            while ((row = csvReader.readLine()) != null) {
                // converts row into data
                String[] info = row.split(",");
                // creates GroceryItem object from info array
                GroceryItem item =
                    new GroceryItem(info[0], info[1], info[2], Integer.parseInt(info[3]));
                // inserts item into the red black tree
                rbTree.insert(item);
            }
            // closes CSV reader
            csvReader.close();
        } catch (IOException ex) {
            return null; // file does not exist
        }
        return rbTree;
    }

    /**
     * Takes information provided by the user and inserts new Grocery Items into the red black
     * tree
     * @param list - Array List of array object that contain the name, brand, dept, and UPC
     *               of items to be added to the Red Black Tree
     * @return - returns the Red Black Tree after all the Grocery List items are added.
     */
    public static RedBlackTree<GroceryItem> writeCSV(ArrayList<String[]> list) {
        for (int i = 0; i < list.size(); i++) {
            String[] data = list.get(i);
            // creates GroceryItem from information stored in data array
            GroceryItem item =
                new GroceryItem(data[0], data[1], data[2], Integer.parseInt(data[3]));
            // inserts the item into the red black tree
            rbTree.insert(item);
        }
        return rbTree;
    }

}

