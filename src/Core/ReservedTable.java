public class ReservedTable extends Table {
    /*
    *   Constructor
    */
    public ReservedTable(int heads, String reservationTime) {
        this.heads = heads;
        this.reservationTime = reservationTime;
    }
    
    /*
    *   Getters
    */
    public int getHeads() { return heads; }
    public string getReservationTime() { return reservationTime; }
    
    /*
    *   Private Variables
    */
    private int heads;                 // Expected number of people to sit at the table
    private int reservationTime;    // Expected arrival time of the guests
}