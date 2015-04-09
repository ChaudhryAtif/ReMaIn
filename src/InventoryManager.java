import java.util.ArrayList;

public class InventoryManager {
	
	//************************************************
	// Private
	//************************************************
	private static ArrayList<InventoryItem> inventoryItems = new ArrayList<InventoryItem>();
	
	private static InventoryItem getItem(String itemID) {
    	for (InventoryItem item : inventoryItems) {
    		if (item.getItemID() == itemID) { return item; }
    	}
    	return null;
    }
	
	//************************************************
	// Public
	//************************************************
	/**
	 * Add an item to the ArrayList of items
	 * @param tableValues 	An array of values for the item being added
	 */
    public static void addInventoryItem(String tableValues[]) {
        inventoryItems.add(new InventoryItem(tableValues));
    }
    
    /**
     * Update an item in the ArrayList of items
     * @param itemID 		The ID of the item to be updated
     * @param tableValues 	An array of values for updating the item
     */
    public static void updateItem(String itemID, String tableValues[]) {
    	InventoryItem item = getItem(itemID);
    	if (item != null) { item.update(tableValues); }
    }
    
    /**
     * Remove an item from the ArrayList of items
     * @param itemID 		The ID of the item to be removed
     */
    public static void removeInventoryItem(String itemID) {
    	InventoryItem item = getItem(itemID);
        if (item != null) { inventoryItems.remove(item); }
    }
    
    /**
     * Returns all of the InventoryItems
     * @return				All of the InventoryItems in the ArrayList
     */
    public static Object[][] getInventoryItems() {
    	Object[][] items = {};
    	int count = 0;
    	for (InventoryItem item : inventoryItems) {
    		items[count++] = new Object[]{item.getItemID(), item.getItemName(), item.getItemQty(), item.getItemStock(), item.getItemNotes(), item.getStatus()};
    	}
    	return items;
    }
}