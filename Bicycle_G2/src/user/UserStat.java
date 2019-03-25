package user;

public class UserStat {
	
	private double numberRides;
	private double totalTime =0;
	private double totalAmount=0;
	private double totalCreditEarned=0;
	
	public UserStat() {
		super();
		
	}

	public double getNumberRides() {
		return numberRides;
	}

	public void setNumberRides(double numberRides) {
		this.numberRides = numberRides;
	}

	public double getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getTotalCreditEarned() {
		return totalCreditEarned;
	}

	public void setTotalCreditEarned(double totalCreditEarned) {
		this.totalCreditEarned = totalCreditEarned;
	}
	
	
	
	

}
