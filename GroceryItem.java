public class GroceryItem implements Comparable<GroceryItem>{
  // fields
  private String brand;
  private String department;
  private String name;
  private Integer UPC;
  
  //constructor
  public GroceryItem(String name, String brand, String dept, Integer UPC) {
    this.name = name;
    this.brand = brand;
    this.department = dept;
    this.UPC = UPC;
  }
  // getters
  /**
   * @return the brand
   */
  public String getBrand() {
    return brand;
  }
  /**
   * @return the department
   */
  public String getDepartment() {
    return department;
  }
  /**
   * @return the name
   */
  public String getName() {
    return name;
  }
  /**
   * @return the uPC
   */
  public Integer getUPC() {
    return UPC;
  }
  
  @Override
  public int compareTo(GroceryItem anotherItem) {
    return this.UPC.compareTo(anotherItem.UPC);
  }
  
//  Possibly add more information to output
  @Override
  public String toString() {
    return this.name ;
  }
}
