import java.sql.*;
import java.util.*;

public class InventoryDB {
	
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
	
	public static void insert(String tableValues[]) {
		String query = " INSERT INTO inventory VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
			
			preparedStmt.setInt(1, new Integer(tableValues[0]));
			preparedStmt.setString(2, tableValues[1]);
			preparedStmt.setInt(3, new Integer(tableValues[2]));
			preparedStmt.setInt(4, new Integer(tableValues[3]));
			preparedStmt.setString(5, tableValues[4]);
			preparedStmt.setString(6, tableValues[5]);
			
			preparedStmt.execute();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public static void modify(int id, String tableValues[]) {
		for (int i = 0; i < tableValues.length; i++) {
			singleModify(id, i, tableValues[i]);
		}
	}
	
	public static void singleModify(int id, int field, String value) {
		String query = "update inventory set "+field+"= ? where id= ?";
		try {
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
			if (field == 0 || field == 2 || field == 3) {
				preparedStmt.setInt(1, new Integer(value));
			}
			else {
				preparedStmt.setString(1, value);
			}
			preparedStmt.setInt(2, id);
	
			preparedStmt.executeUpdate();
		}
		catch (Exception exc){
			exc.printStackTrace();
		}
	}
	
	public static ArrayList<InventoryItem> getAll() {
		String query = "SELECT * FROM inventory";
		try {
			Statement stmt = myConn.createStatement();
			ResultSet results = stmt.executeQuery(query);
			ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();
			
			while (results.next()) {
				String myList[] = new String[]{}; 
				for (int i = 0; i < 6; i++) { // 6 columns in the table
					myList[i] = results.getString(i);
				}
	            items.add(new InventoryItem(myList));
			}
			return items;
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}
	
	public static void remove(int id) {
		String query = "DELETE FROM inventory WHERE id=?";
		try {
		PreparedStatement preparedStmt = myConn.prepareStatement(query);
		preparedStmt.setInt(1, id);
	
  		preparedStmt.execute();
		}
		catch (Exception exc){
			exc.printStackTrace();
		}
	}
	
}
