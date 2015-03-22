import java.util.ArrayList;
import java.util.Collections;

public class OrderManager {
    /*
    *    Constructor
    */
    public OrderManager() { }
    
    /*
    *   Getters
    */
    public ArrayList getOrdersByTable() {
        Collections.sort(orders, Order.comparatorByTable());
    }
    public ArrayList getOrdersByTime() {
        Collections.sort(orders, Order.comparatorByTime());
    }
    
    /*
    *   Add an order to the ArrayList orders
    *
    *   @param order The order to be added to the ArrayList orders
    */
    public void addOrder(Order order) { orders.add(order); }
    
    /*
    *   Delete an order from the ArrayList orders
    *
    *   @param order The order to be deleted from the ArrayList orders
    */
    public void deleteOrder(Order order) { orders.remove(order); }
    
    /*
    *   Private Variables
    */
    private ArrayList orders = new ArrayList();
}