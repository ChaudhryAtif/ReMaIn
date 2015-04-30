public class InventoryItem {

	private String itemID;
    private String itemName;
    private String itemQty;
    private String itemStock;
    private String itemNotes;
    private String status;

    /**
     * Construct an InventoryItem
     * @param tableValues 	An array of values for the item
     */
    public InventoryItem(String tableValues[]) {
    	update(tableValues);
    }
    
    /**
     * Update the item
     * @param tableValues 	An array of values to update the item
     */
    public void update(String tableValues[]) {
    	this.itemID = tableValues[0];
    	this.itemName = tableValues[1];
    	this.itemQty = tableValues[2];
    	this.itemStock = tableValues[3];
    	this.itemNotes = tableValues[4];
    	this.status = tableValues[5];
    }
    
    /**
	 * Set the status of the item
	 * @param status		The new status of the item
	 */
	public void setStatus(String status) {
		this.status = status;
	}
    
	// Getters
    public String getItemID() { return itemID; }
    public String getItemName() { return itemName; }
    public String getItemQty() { return itemQty; }
    public String getItemStock() { return itemStock; }
    public String getItemNotes() { return itemNotes; }
    public String getStatus() { return status; }
}
