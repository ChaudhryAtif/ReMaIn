public class InventoryItem {
    /*
    *   Constructor
    */
    public InventoryItem(int ID, String item, int quantityNeeded, int currentStock, String notes, String status) {
        this.item = item;
        this.quantityNeeded = quantityNeeded;
        this.currentStock = currentStock;
        this.notes = notes;
        this.ID = ID;
        this.status = status;
    }
    
    /*
    *   Getters
    */
    public int getID() { return ID; }
    public String getItem() { return item; }
    public int getQuantityNeeded() { return quantityNeeded; }
    public int getCurrentStock() { return currentStock; }
    public String getNotes() { return notes; }
    public String getStatus() { return status; }
    
    /*
    *   Private Variables
    */
    private int ID;
    private String item;
    private int quantityNeeded;
    private int currentStock;
    private String notes;
    private String status;
}