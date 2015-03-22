public class InventoryItem {
    /*
    *   Constructor
    */
    public InventoryItem(String item, int quantityNeeded, int currentStock, String notes) {
        this.item = item;
        this.quantityNeeded = quantityNeeded;
        this.currentStock = currentStock;
        this.notes = notes;
    }
    
    /*
    *   Getters
    */
    public String getItem() { return item; }
    public int getQuantityNeeded() { return quantityNeeded; }
    public int getCurrentStock() { return currentStock; }
    public String getNotes() { return notes; }
    
    /*
    *   Private Variables
    */
    private String item;
    private int quantityNeeded;
    private int currentStock;
    private String notes;
}