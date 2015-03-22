import java.util.ArrayList;
import java.util.Collections;

public class OrderManager {
    /*
    *    Constructor
    */
    public OrderManager() {
    	orders = new ArrayList<Order>();
    }
    
    /*
    *   Getters
    */
    public ArrayList<Order> getOrdersByTable() {
        Collections.sort(orders, Order.comparatorByTable());
        return orders;
    }
    public ArrayList<Order> getOrdersByTime() {
        Collections.sort(orders, Order.comparatorByTime());
        return orders;
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
    private ArrayList<Order> orders;
}