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
	
	
}
