public class Order {
    /*
    *    Constructor
    */
    public Order(string order, int tableNumber, string waiter, string orderTime) {
        this.order = order;
        this.tableNumber = tableNumber;
        this.waiter = waiter;
        this.orderTime = orderTime;
    }
    
    /*
    *    Getters
    */
    public string getOrder() { return order; }
    public int getTableNumber() { return tableNumber; }
    public string getWaiter() { return waiter; }
    public string getOrderTime() { return orderTime; }

    /*
    *    Private Variables
    */
    private string order;        // String that represents the order
    private int tableNumber      // Table associated with the order
    private string waiter        // Waiter associated with the order
    private string orderTime     // Time of the order
}