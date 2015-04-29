import java.sql.*;
import java.util.*;


public class TableDB {
	
	private static Connection myConn;
	
	public static void initialize() {
		try {
			//System.out.println("Connecting...\n");
			Class.forName("com.mysql.jdbc.Driver");
			myConn = DriverManager.getConnection(
				"jdbc:mysql://25.2.208.207:3306/MAnRI", "test" , "Maximus69");
			//System.out.println("Connected\n");
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
		//for (int i = 0; i < tableValues.length; i++) {
		singleModify(tableNumber, "status", tableValues[1]);
		singleModify(tableNumber, "name", tableValues[2]);
		singleModify(tableNumber, "date", tableValues[3]);
		singleModify(tableNumber, "time", tableValues[4]);
		singleModify(tableNumber, "phone", tableValues[5]);
		singleModify(tableNumber, "heads", tableValues[6]);
		singleModify(tableNumber, "notes", tableValues[7]);
		//}
	}
	
	public static void singleModify(int tableNumber, String field, String value) {
		initialize();
		String query = "update table_dir set "+field+"= ? where id= ?";
		try {
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
			if (field.equals("heads")) {	//////////////////////////////// UPDATE THIS IF
				preparedStmt.setInt	(1, Integer.parseInt(value));
			}
			else {
				preparedStmt.setString	(1, value);
			}
			preparedStmt.setInt	(2, tableNumber);
	
			preparedStmt.executeUpdate();
		}
		catch (Exception exc){
			exc.printStackTrace();
		}
		close();
	}
	
	public static ArrayList<Table> getAll() {
		initialize();
		String query = "SELECT * FROM table_dir";
		try {
			Statement stmt = myConn.createStatement();
			ResultSet results = stmt.executeQuery(query);
			ArrayList<Table> tables = new ArrayList<Table>();
			
			int count = 1;
			while (results.next()) {
				String[] myList = new String[8];
				//for (int i = 0; i < 8; i++) {
					myList[0] = results.getString("table_num");
					myList[1] = results.getString("status");
					myList[2] = results.getString("name");
					myList[3] = results.getString("date");
					myList[4] = results.getString("time");
					myList[5] = results.getString("phone");
					myList[6] = results.getString("heads");
					myList[7] = results.getString("notes");
				//}
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
		close();
		return null;
	}
	
}
