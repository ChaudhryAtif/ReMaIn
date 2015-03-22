import java.util.Comparator;

public class Order extends Exception {
    /*
    *    Constructor
    */
    public Order(int orderID, string orderInfo, int tableNum, string waiter, string timePlaced, int heads, string additionalInfo) {
        this.orderID = orderID;
        this.orderInfo = orderInfo;
        this.tableNum = tableNum;
        this.waiter = waiter;
        if (!(Character.isDigit(timePlace.charAt(0))) || !(Character.isDigit(timePlace.charAt(1))) || !(timePlaced.charAt(2) == ":") || !(Character.isDigit(timePlace.charAt(3))) || !(Character.isDigit(timePlace.charAt(4))))
            throw new InvalidTimeException("Invalid Time Submitted: Use military time in form: 99:99");
        else
            this.timePlaced = timePlaced;
        this.heads = heads;
        this.additionalInfro = additionalInfo;
    }
    
    public static Comparator<Order> comparatorByTable() {
        return new
            Comparator<Order>() {
                public int compare(Order order1, Order order2) {
                    return order1.compareTo(order2);
                }
            };
    }
    
    public static Comparator<Order> comparatorByTime() {
        return new
            Comparator<Order>() {
                public int compare(Order order1, Order order2) {
                    return order1.compareTo(order2);
                }
            };
    }
    
    /*
    *    Getters
    */
    public int getOrderID() { return orderID; }
    public string getOrderInfo() { return orderInfo; }
    public int getTableNum() { return tableNumber; }
    public string getWaiter() { return waiter; }
    public string getTimePlaced() { return timePlaced; }
    public int getHeads() { return heads; }
    public string getAdditionalInfro() { return additionalInfo; }

    /*
    *    Private Variables
    */
    private int orderID;             // ID of the order
    private string orderInfo;       // String that represents the order information
    private int tableNum;           // Table associated with the order
    private string waiter;          // Waiter associated with the order
    private string timePlaced;      // Time of the order (Requires military time in form 99:99)
    private int heads;              // Number of customers in the order
    private string additionalInfo;   // Additional information about the order
}