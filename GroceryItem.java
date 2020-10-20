// --== CS400 File Header Information ==--
// Name: Frank Slavinsky and Ryan Cope
// Email: fslavinsky@wisc.edu and rlcope@wisc.edu
// Team: BB
// TA: Bri
// Lecturer: Gary
// Notes to Grader: Written by the two people listed.
/**
 * This class serves as a way to store data inside of GroceryItem nodes.
 * 
 * @author Frank Slavinsky
 * @author rlcope
 */
public class GroceryItem implements Comparable<GroceryItem> {
	// fields
	private String brand;
	private String department;
	private String name;
	private Integer UPC;

	/**
	 * This method serves as a constructor for the GroceryItem instance.
	 * 
	 * @param name  - The name of the item.
	 * @param brand - The brand of the item.
	 * @param dept  - The department where the item is found.
	 * @param UPC   - The number that is used to track and store the item.
	 */
	public GroceryItem(String name, String brand, String dept, Integer UPC) {
		this.name = name;
		this.brand = brand;
		this.department = dept;
		this.UPC = UPC;
	}

	/**
	 * This method acts as the getter for the brand field.
	 * 
	 * @return The brand of the GroceryItem.
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * This method acts as the getter for the department field.
	 * 
	 * @return The department of the GroceryItem.
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * This method acts as the getter for the name field.
	 * 
	 * @return The name of the GroceryItem.
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method acts as the getter for the UPC field.
	 * 
	 * @return The UPC of the GroceryItem.
	 */
	public Integer getUPC() {
		return UPC;
	}

	/**
	 * This method allows two GroceryItems to be compared by their individual UPCs.
	 * 
	 * @return 0 if the UPCs are equal, a negative number if anotherItem.UPC is
	 *         larger than this.UPC, and a positive number if anotherItem.UPC is
	 *         smaller than this.UPC.
	 */
	@Override
	public int compareTo(GroceryItem anotherItem) {
		return this.UPC.compareTo(anotherItem.UPC);
	}

	/**
	 * This method allows the user to return a string representation of some data
	 * contained in the GroceryItem node.
	 *
	 * @return A string representation of the name field of the GroceryItem.
	 */
	@Override
	public String toString() {
		return this.name;
	}
}
