package user;

public class UserStat {
	
	private int numberRides;
	private double totalTime = 0;
	private double totalAmount = 0;
	private double totalCreditEarned = 0;
	
	public UserStat() { super(); }

	public int getNumberRides() { return numberRides; }

	public void addRide() {
		this.numberRides++;
	}

	public double getTotalTime() { return totalTime; }

	public void addTime(double time) {
		this.totalTime += time;
	}

	public double getTotalAmount() { return totalAmount; }

	public void addAmount(double amount) {
		this.totalAmount += amount;
	}

	public double getTotalCreditEarned() { return totalCreditEarned; }

	public void addCreditEarned(double creditEarned) {
		this.totalCreditEarned += creditEarned;
	}
	
	
	
	

}
