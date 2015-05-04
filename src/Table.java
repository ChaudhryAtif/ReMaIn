public class Table {
    
	private String tableNumber;
	private String status;
	private String resName;
	private String resDate;
	private String resTime;
	private String resPhone;
	private String resHeads;
	private String resNotes;

	/**
	 * Constructs a new table
	 * @param tableNumber		The number of the table
	 * @param status			The status of the table
	 */
	public Table(String tableNumber, String status) {
		this.tableNumber = tableNumber;
		this.status = status;
		this.resName = "";
		this.resDate = "";
		this.resTime = "";
		this.resPhone = "";
		this.resHeads = "";
		this.resNotes = "";
	}
	
	/**
	 * Sets the status of the table
	 * @param status			The new status of the table
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Sets the reservation information
     * @param resName			The name for the reservation
     * @param resDate			The date of the reservation
     * @param resTime			The time of the reservation
     * @param resPhone			The phone number of the reserver
     * @param resHeads			The number of heads to be seated at the table
     * @param resNotes			Any notes for the reservation
	 */
	public void setReservationInfo(String resName, String resDate, String resTime, 
			     String resPhone, String resHeads, String resNotes) {
		this.resName = resName;
		this.resDate = resDate;
		this.resTime = resTime;
		this.resPhone = resPhone;
		this.resHeads = resHeads;
		this.resNotes = resNotes;
	}
	
	// Getters
	public String getTableNumber() { return tableNumber; }
	public String getStatus() { return status; }
	public String getResName() { return resName; }
	public String getResDate() { return resDate; }
	public String getResTime() { return resTime; }
	public String getResPhone() { return resPhone; }
	public String getResHeads() { return resHeads; }
	public String getResNotes() { return resNotes; }
	
}