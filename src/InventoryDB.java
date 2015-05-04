import java.sql.*;
import java.util.*;

public class InventoryDB {

    private static Connection myConn;	// Connection variable to interface with the database

    /**
     * Initializes the connection to the database
     */
    public static void initialize() {
        try {
            //  System.out.println("Connecting...\n");
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(
                    "jdbc:mysql://25.2.208.207:3306/MAnRI", "test" , "Maximus69");
            //	System.out.println("Connected\n");
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
     * Inserts a new inventory item with the specified table values into the database
     * @param tableValues	An array of table values for the item
     */
    public static void insert(String tableValues[]) {
        initialize();
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
        catch (Exception exc) { exc.printStackTrace(); }
        close();
    }

    /**
     * Modifies the inventory item with the provided table values
     * @param id			The ID of the item to modify
     * @param tableValues	An array of table values for the item
     */
    public static void modify(int id, String tableValues[]) {
        singleModify(id, "description", tableValues[1]);
        singleModify(id, "qty_need", tableValues[2]);
        singleModify(id, "in_stock", tableValues[3]);
        singleModify(id, "notes", tableValues[4]);
        singleModify(id, "status", tableValues[5]);
    }

    /**
     * Modifies a single attribute of an inventory item
     * @param id			The ID of the item to modify
     * @param field			The field to be modified
     * @param value			The new value to put into the field
     */
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
        catch (Exception exc){ exc.printStackTrace(); }
        close();
    }

    /**
     * Gets all of the inventory items in the database
     * @return 			An arraylist of all inventory items in the database
     */
    public static ArrayList<InventoryItem> getAll() {
        initialize();
        String query = "SELECT * FROM inventory";
        try {
            Statement stmt = myConn.createStatement();
            ResultSet results = stmt.executeQuery(query);
            ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();

            while (results.next()) {
                String[] myList = new String[6];
                myList[0] = results.getString("id");
                myList[1] = results.getString("description");
                myList[2] = results.getString("qty_need");
                myList[3] = results.getString("in_stock");
                myList[4] = results.getString("notes");
                myList[5] = results.getString("status");
                items.add(new InventoryItem(myList));
            }
            return items;
        }
        catch (Exception exc) { exc.printStackTrace(); }
        close();
        return null;
    }

    /**
     * Removes the specified item from the database
     * @param id		The ID of the item to be removed
     */
    public static void remove(int id) {
        initialize();
        String query = "DELETE FROM inventory WHERE id=?";
        try {
            PreparedStatement preparedStmt = myConn.prepareStatement(query);
            preparedStmt.setInt	(1, id);

            preparedStmt.execute();
        }
        catch (Exception exc){ exc.printStackTrace(); }
        close();
    }
}
