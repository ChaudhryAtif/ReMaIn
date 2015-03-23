import java.util.Comparator;

public class Order extends Exception {
    /*
    *    Constructor
    */
    public Order(int orderID, String orderInfo, int tableNum, String waiter, String timePlaced, int heads, String additionalInfo) {
        this.orderID = orderID;
        this.orderInfo = orderInfo;
        this.tableNum = tableNum;
        this.waiter = waiter;
        this.timePlaced = timePlaced;
        this.heads = heads;
        this.additionalInfo = additionalInfo;
    }
    
    /*
    *    Getters
    */
    public int getOrderID() { return orderID; }
    public String getOrderInfo() { return orderInfo; }
    public int getTableNum() { return tableNum; }
    public String getWaiter() { return waiter; }
    public String getTimePlaced() { return timePlaced; }
    public int getHeads() { return heads; }
    public String getAdditionalInfro() { return additionalInfo; }

    /*
    *    Private Variables
    */
    private int orderID;             // ID of the order
    private String orderInfo;        // String that represents the order information
    private int tableNum;            // Table associated with the order
    private String status;           // Status of the order
    private String waiter;           // Waiter associated with the order
    private String timePlaced;       // Time of the order (Requires military time in form 99:99)
    private int heads;               // Number of customers in the order
    private String additionalInfo;   // Additional information about the order
}
