import java.util.ArrayList;

public class TableManager {

	private static int x = 0;
    private static int numOfTables = 11;
    private static ArrayList<Table> tables = new ArrayList<Table>(numOfTables);
    
    /**
     * Sets the status of the table
     * @param tableNumber		The table number of the table to be modified
     * @param status			The new status for the table
     */
    public static void setStatus(int tableNumber, String status) {
    	tables.get(tableNumber-1).setStatus(status);
    }
    
    /**
     * Sets the reservation info for the table
     * @param tableNumber		The table number of the table to be modified
     * @param resName			The name for the reservation
     * @param resDate			The date of the reservation
     * @param resTime			The time of the reservation
     * @param resPhone			The phone number of the reserver
     * @param resHeads			The number of heads to be seated at the table
     * @param resNotes			Any notes for the reservation
     */
    public static void setReservationInfo(int tableNumber, String resName, String resDate, String resTime, 
		     String resPhone, String resHeads, String resNotes) {
    	tables.get(tableNumber-1).setReservationInfo(resName, resDate, resTime, resPhone, resHeads, resNotes);
    }
    
    /**
     * Returns all of the tables
     * @return					A 2D array of all of the tables and their information
     */
    public static String[][] getTables() {
    	if (x == 0) {
	    	for (int i = 0; i < numOfTables; i++) {
	    		tables.add(new Table(Integer.toString(i), "CLEARED"));
	    	}
	    	x = 1;
    	}
    	String[][] tempTables = new String[12][8];
    	int count = 1;
    	for (Table table : tables) {
    		String tempStrings[] = new String[]{table.getTableNumber(), table.getStatus(), table.getResName(),
    				table.getResDate(), table.getResTime(), table.getResPhone(), table.getResHeads(), table.getResNotes()};
    		tempTables[count++] = tempStrings;
    	}
    	return tempTables;
    }
}
