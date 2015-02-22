public class TableManager {
    /*
    *   Constructor
    */
    public TableManager(int numOfTables) {
        this.numOfTables = numOfTables;
        tables = new ArrayList<Table>(numOfTables);
    }
    
    /*
    *   Getters
    */
    public int getNumOfTables() { return numOfTables; }
    
    /*
    *   Private Variables
    */
    private ArrayList<Table> tables;    // Array of tables
    private int numOfTables;              // Number of tables in restaurant
}