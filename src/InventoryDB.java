import java.sql.*;
import java.util.*;

public class InventoryDB {
	
	private static Connection myConn;
	
	public static void initialize() {
		try {
			//  System.out.println("Connecting...\n");
			Class.forName("com.mysql.jdbc.Driver");
			myConn = DriverManager.getConnection(
				"jdbc:mysql://25.2.208.207:3306/MAnRI", "test" , "Maximus69");
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
		initialize();
		String query = " INSERT INTO inventory VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
			
			preparedStmt.setInt(1, new Integer(tableValues[0]));
			preparedStmt.setInt(2, new Integer(tableValues[1]));
			preparedStmt.setString(3, tableValues[2]);
			preparedStmt.setInt(4, new Integer(tableValues[3]));
			preparedStmt.setString(5, tableValues[4]);
			preparedStmt.setString(6, tableValues[5]);
			
			preparedStmt.execute();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		close();
	}
	
	public static void modify(int id, String tableValues[]) {
		System.out.println("shits modifying");

		singleModify(id, "qty_need", tableValues[1]);
		singleModify(id, "description", tableValues[2]);
		singleModify(id, "in_stock", tableValues[3]);
		singleModify(id, "notes", tableValues[4]);
 		singleModify(id, "status", tableValues[5]);
	}
	
	public static void singleModify(int id, String field, String value) {
		initialize();
		String query = "update inventory set "+field+"= ? where id= ?";
		try {
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
			if ( (field.equals("qty_need")) || (field.equals("in_stock")) ) {
				preparedStmt.setInt	(1, Integer.parseInt(value));
			}
			else {
				preparedStmt.setString	(1, value);
			}
			preparedStmt.setInt	(2, id);
	
			preparedStmt.executeUpdate();
		}
		catch (Exception exc){
			exc.printStackTrace();
		}
		close();
	}
	
	public static ArrayList<InventoryItem> getAll() {
		initialize();
		String query = "SELECT * FROM inventory";
		try {
			Statement stmt = myConn.createStatement();
			ResultSet results = stmt.executeQuery(query);
			ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();
			
			while (results.next()) {
				String[] myList = new String[6]; 
				//for (int i = 0; i < 6; i++) { // 6 columns in the table
				myList[0] = results.getString("id");
				myList[1] = results.getString("qty_need");
				myList[2] = results.getString("description");
				myList[3] = results.getString("in_stock");
				myList[4] = results.getString("notes");
				myList[5] = results.getString("status");
				//}
	            		items.add(new InventoryItem(myList));
			}
			return items;
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		close();
		return null;
	}
	
	public static void remove(int id) {
		initialize();
		String query = "DELETE FROM inventory WHERE id=?";
		try {
		PreparedStatement preparedStmt = myConn.prepareStatement(query);
		preparedStmt.setInt	(1, id);
	
  		preparedStmt.execute();
		}
		catch (Exception exc){
			exc.printStackTrace();
		}
		close();
	}
	
}
