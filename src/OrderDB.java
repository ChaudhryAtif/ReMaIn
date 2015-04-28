import java.sql.*;
import java.util.*;

public class OrderDB {
	
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
		String query = " INSERT INTO orders VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
			
			preparedStmt.setInt(1, new Integer(tableValues[0]));
			preparedStmt.setString(2, tableValues[1]);
			preparedStmt.setInt(3, new Integer(tableValues[2]));
			preparedStmt.setString(4, tableValues[3]);
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
		String query = "update orders set "+field+"= ? where id= ?";
		try {
			PreparedStatement preparedStmt = myConn.prepareStatement(query);
			if (field == 0 || field == 2) {
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
	
	public static ArrayList<Order> getAll() {
		String query = "SELECT * FROM orders";
		try {
			Statement stmt = myConn.createStatement();
			ResultSet results = stmt.executeQuery(query);
			ArrayList<Order> orders = new ArrayList<Order>();
			
			while (results.next()) {
				String myList[] = new String[]{};
				for (int i = 0; i < 6; i++) { // 6 columns in the table
					myList[i] = results.getString(i);
				}
				/*
				myList[0] = results.getString("id");
	            myList[1] = results.getString("table");
	            myList[2] = results.getString("order_detail");
	            myList[3] = results.getString("time_ordered");
	            myList[4] = results.getString("notes");
	            myList[5] = results.getString("order_status");*/
	            orders.add(new Order(myList));
			}
			return orders;
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}
	
	public static void remove(int id) {
		String query = "DELETE FROM orders WHERE id=?";
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
