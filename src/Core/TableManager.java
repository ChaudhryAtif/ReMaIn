import java.util.ArrayList;

public class TableManager {
    /*
    *   Constructor
    */
    public TableManager(int numOfTables) {
        this.numOfTables = numOfTables;
        tables = new ArrayList<Table>(numOfTables);
		for (int i = 0; i < numOfTables; i++) {
			tables.add(new OpenTable());
		}
    }
    
    /*
    *   Getters
    */
    public int getNumOfTables() { return numOfTables; }
	public ArrayList<Table> getTables() { return tables; }
	// To Do: Make sure table numbers are lined up with indices
	public Table getTable(int tableNumber) { return tables.get(tableNumber); }

	/*
	*	Setters
	*/ 
	public void setTable(int tableNumber, Table table) { tables.set(tableNumber, table); }
    
    /*
    *   Private Variables
    */
    private ArrayList<Table> tables;    // Array of tables
    private int numOfTables;              // Number of tables in restaurant
}
