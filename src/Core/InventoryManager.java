import java.util.ArrayList;

public class InventoryManager {
    /*
    *   Constructors
    */
    public InventoryManager() {
        inventoryItems = new ArrayList<InventoryItem>();
    }
    public InventoryManager(ArrayList<InventoryItem> items) {
        inventoryItems = new ArrayList<InventoryItem>(items);
    }
    
    /*
    *   Public Functions
    */
    public boolean deleteInventoryItem(InventoryItem item) {
        return inventoryItems.remove(item);
    }
    public boolean addInventoryItem(InventoryItem item) {
        return inventoryItems.add(item);
    }
    
    /*
    *   Getters
    */
    public ArrayList<InventoryItem> getInventoryItems() { return inventoryItems; }
    
    /*
    *   Private Variables
    */
    ArrayList<InventoryItem> inventoryItems;
}