import java.util.ArrayList;

public class TableManager {

    private static int numOfTables = 11;
    private static ArrayList<Table> tables = new ArrayList<Table>(numOfTables + 1);
    
    public static void setStatus(int tableNumber, String status) {
    	tables.get(tableNumber).setStatus(status);
    }
    
    public static void setReservationInfo(int tableNumber, String resName, String resDate, String resTime, 
		     String resPhone, String resHeads, String resNotes) {
    	tables.get(tableNumber).setReservationInfo(resName, resDate, resTime, resPhone, resHeads, resNotes);
    }
    
    public static ArrayList<Table> getTables() {
    	/////// DON'T FORGET TO LEAVE INDEX 0 EMPTY
    	// Check DB to see if it is storing tables
    	// If it is, get them
    	// If not, fill the list with cleared tables and push them to the DB
    	for (int i = 1; i <= numOfTables; i++) {
    		tables.add(new Table("CLEARED"));
    	}
    	return tables;
    }
    
}
