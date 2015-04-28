public class Table {
    
	private String tableNumber;
	private String status;		// The status of the table: Cleared, Occupied, or Reserved
	private String resName;
	private String resDate;
	private String resTime;
	private String resPhone;
	private String resHeads;
	private String resNotes;

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
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setReservationInfo(String resName, String resDate, String resTime, 
			     String resPhone, String resHeads, String resNotes) {
		this.resName = resName;
		this.resDate = resDate;
		this.resTime = resTime;
		this.resPhone = resPhone;
		this.resHeads = resHeads;
		this.resNotes = resNotes;
	}
	
	public String getTableNumber() { return tableNumber; }
	public String getStatus() { return status; }
	public String getResName() { return resName; }
	public String getResDate() { return resDate; }
	public String getResTime() { return resTime; }
	public String getResPhone() { return resPhone; }
	public String getResHeads() { return resHeads; }
	public String getResNotes() { return resNotes; }
	
}