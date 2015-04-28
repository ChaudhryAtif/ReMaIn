import java.sql.*;
import java.util.*;


public class TableDB {
	
	private static Connection myConn;
	
	public static void initialize() {
		try {
			//  System.out.println("Connecting...\n");
			Class.forName("com.mysql.jdbc.Driver");
			myConn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/test", "root" , "Maximus69");
			//	System.out.println("Connected\n");
		}
		catch (Exception exc){
			exc.printStackTrace();
		}
	}
	
	public static void close() {
		try {
			myConn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void modify(int tableNumber, String tableValues[]) {
		for (int i = 0; i < tableValues.length; i++) {
			singleModify(tableNumber, i, tableValues[i]);
		}
	}
	
	public static void singleModify(int tableNumber, int field, String value) {
		String query = "update orders set "+field+"= ? where id= ?";
		try {
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
			if (field == 0 || field == 2) {	//////////////////////////////// UPDATE THIS IF
				preparedStmt.setInt(1, new Integer(value));
			}
			else {
				preparedStmt.setString(1, value);
			}
			preparedStmt.setInt(2, tableNumber);
	
			preparedStmt.executeUpdate();
		}
		catch (Exception exc){
			exc.printStackTrace();
		}
	}
	
	public static ArrayList<Table> getAll() {
		String query = "SELECT * FROM tables";
		try {
			Statement stmt = myConn.createStatement();
			ResultSet results = stmt.executeQuery(query);
			ArrayList<Table> tables = new ArrayList<Table>();
			
			int count = 1;
			while (results.next()) {
				String myList[] = new String[]{};
				for (int i = 0; i < 8; i++) {
					myList[i] = results.getString(i);
				}
				Table tempTable = new Table(Integer.toString(count++), myList[1]);
	            if (myList[1] == "Reserved") {
	            	tempTable.setReservationInfo(myList[2], myList[3], myList[4], myList[5], myList[6], myList[7]);
	            }
	            tables.add(tempTable);
			}
			return tables;
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}
	
}
