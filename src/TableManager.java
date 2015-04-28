import java.util.ArrayList;

public class TableManager {

	private static int x = 0;
    private static int numOfTables = 11;
    private static ArrayList<Table> tables = new ArrayList<Table>(numOfTables);
    
    public static void setStatus(int tableNumber, String status) {
    	tables.get(tableNumber-1).setStatus(status);
    	//TableDB.singleModify(tableNumber, 1, status);
    }
    
    public static void setReservationInfo(int tableNumber, String resName, String resDate, String resTime, 
		     String resPhone, String resHeads, String resNotes) {
    	tables.get(tableNumber-1).setReservationInfo(resName, resDate, resTime, resPhone, resHeads, resNotes);
    	//TableDB.modifyReservation(tableNumber, new String[]{resName, resDate, resTime, resPhone, resHeads, resNotes});
    }
    
    public static String[][] getTables() {
    	//tables = TableDB.getAll();
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
