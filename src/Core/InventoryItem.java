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
    public getItem() { return item; }
    public getQuantityNeeded() { return quantityNeeded; }
    public getCurrentStock() { return currentStock; }
    public getNotes() { return notes; }
    
    /*
    *   Private Variables
    */
    private String item;
    private int quantityNeeded;
    private int currentStock;
    private String notes;
}