import java.sql.*;
import java.util.*;

public class OrderDB {
	
	private static Connection myConn;	// Connection variable to interface with the database
	
	/**
     * Initializes the connection to the database
     */
	public static void initialize() {
		try {
			//System.out.println("Connecting...\n");
			Class.forName("com.mysql.jdbc.Driver");
			myConn = DriverManager.getConnection(
				"jdbc:mysql://25.2.208.207:3306/MAnRI", "test" , "Maximus69");
			//System.out.println("Connected\n");
		}
		catch (Exception exc){ exc.printStackTrace(); }
	}
	
	/**
     * Closes the connection to the database
     */
	public static void close() {
		try { myConn.close(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	/**
     * Inserts a new order with the specified table values into the database
     * @param tableValues	An array of table values for the order
     */
	public static void insert(String tableValues[]) {
		initialize();
		String query = " INSERT INTO orders VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
			
			preparedStmt.setInt(1, new Integer(tableValues[0]));
			preparedStmt.setInt(2, new Integer(tableValues[1]));
			preparedStmt.setString(3, tableValues[2]);
			preparedStmt.setString(4, tableValues[3]);
			preparedStmt.setString(5, tableValues[4]);
			preparedStmt.setString(6, tableValues[5]);
			
			preparedStmt.execute();
		}
		catch (Exception exc) { exc.printStackTrace(); }
		close();
	}
	
	/**
     * Modifies the order with the provided table values
     * @param id			The ID of the order to modify
     * @param tableValues	An array of table values for the order
     */
	public static void modify(int id, String tableValues[]) {
		singleModify(id, "table_num", tableValues[1]);
		singleModify(id, "order_detail", tableValues[2]);
		singleModify(id, "time_ordered", tableValues[3]);
		singleModify(id, "notes", tableValues[4]);
		singleModify(id, "order_status", tableValues[5]);

	}
	
	/**
     * Modifies a single attribute of an order
     * @param id			The ID of the order to modify
     * @param field			The field to be modified
     * @param value			The new value to put into the field
     */
	public static void singleModify(int id, String field, String value) {
		initialize();
		String query = "update orders set "+field+"= ? where id= ?";
		try {
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
			if (field.equals("table_num")) {
				preparedStmt.setInt	(1, Integer.parseInt(value));
			}
			else {
				preparedStmt.setString	(1, value);
			}
			preparedStmt.setInt	(2, id);
	
			preparedStmt.executeUpdate();
		}
		catch (Exception exc){ exc.printStackTrace(); }
		close();
	}
	
	/**
     * Gets all of the order in the database
     * @return 			An arraylist of all order in the database
     */
	public static ArrayList<Order> getAll() {
		initialize();
		String query = "SELECT * FROM orders";
		try {
			Statement stmt = myConn.createStatement();
			ResultSet results = stmt.executeQuery(query);
			ArrayList<Order> orders = new ArrayList<Order>();
			
			while (results.next()) {
				String[] myList = new String[6];
				myList[0] = results.getString("id");
                myList[1] = results.getString("table_num");
                myList[2] = results.getString("order_detail");
                myList[3] = results.getString("time_ordered");
                myList[4] = results.getString("notes");
                myList[5] = results.getString("order_status");
                orders.add(new Order(myList));
			}
			return orders;
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		close();
		return null;
	}
	
	/**
     * Removes the specified order from the database
     * @param id		The ID of the order to be removed
     */
	public static void remove(int id) {
		initialize();
		String query = "DELETE FROM orders WHERE id=?";
		try {
		PreparedStatement preparedStmt = myConn.prepareStatement(query);
		preparedStmt.setInt(1, id);
	
  		preparedStmt.execute();
		}
		catch (Exception exc){ exc.printStackTrace(); }
		close();
	}
	
}
