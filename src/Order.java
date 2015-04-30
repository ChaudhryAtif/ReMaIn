
public class Order {

	private String orderID;          // ID of the order
    private String orderTableNo;     // Table associated with the order
    private String orderInfo;        // String that represents the order information
    private String orderTime;        // Time of the order (Requires military time in form 99:99)
    private String orderNotes;       // Additional information about the order
    private String status;           // Status of the order

    /**
     * Construct an Order
     * @param tableValues 	An array of values for the Order
     */
	public Order(String tableValues[]) {
		update(tableValues);
	}
    
	/**
	 * Update the values in the order
	 * @param tableValues 	An array of values to update the order
	 */
	public void update(String tableValues[]) {
		this.orderID = tableValues[0];
		this.orderTableNo = tableValues[1];
	    this.orderInfo = tableValues[2];
	    this.orderTime = tableValues[3];
	    this.orderNotes = tableValues[4];
	    this.status = tableValues[5];
	}
	
	/**
	 * Set the status of the Order
	 * @param status		The new status of the Order
	 */
	public void setStatus(String status) {
		this.status = status;
	}

    // Getters
    public String getOrderID() { return orderID; }
    public String getOrderTableNo() { return orderTableNo; }
    public String getOrderInfo() { return orderInfo; }
    public String getOrderTime() { return orderTime; }
    public String getOrderNotes() { return orderNotes; }
    public String getStatus() { return status; }
    
}
