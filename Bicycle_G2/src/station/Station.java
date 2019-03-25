package station;

import java.time.LocalDateTime;
import java.util.ArrayList;

import tools.*;
import user.User;
import user.UserIDGenerator;
import bike.*;
import card.Card;

/**
 * An abstract class to represent the stations
 *
 */
public abstract class Station {
	
	private int id;
	private Point p;
	private boolean isOnline;
	private ArrayList<Slot> parkingSlots;
	private int NumberSlots;
	
	private int totalRents;
	private int totalReturns;
	private int totalOperations;

	

	public Station(Point p) {
		this.p = p;
		id = StationIDGenerator.getInstance().getNextStationID();
	}






	public int getRateOccupation(LocalDateTime startTime, LocalDateTime endTime){
		int occupationRate = -1;
		try {
			int totalOccupationTime = 0;
			long delta = Date.computeTime (startTime, endTime);
			int N = this.NumberSlots;
			for (Slot slot : parkingSlots) {
				long time = slot.computeOccupationTime(startTime, endTime);
				totalOccupationTime =(int) + time;
			}
			occupationRate = (int) (totalOccupationTime / (delta * N));
		} catch (NegativeTimeException e) {
			System.out.println("Error : Could not calculate rate Occupation");
		}
		return occupationRate;
	}
	
	
	public boolean isStationFull() {
		for (Slot s : parkingSlots) {
			if (s.getisOccupied()==false) {
				return false;
			}
		}
		return true;
	}
	
	public Slot hasBikeAvailable() {
		for (Slot s : parkingSlots) {
			if (s.isOnline() == true && s.getBike() != null) {
				return s;
			}
		}
		return null;
	}
	/**
	 * Allows to look for a specific bike type
	 * @param type
	 * @return
	 */
	public Slot hasElectricBikeAvailable() {
		for (Slot s : parkingSlots) {
			if (s.isOnline() == true && s.getBike() instanceof ElectricBike);
				return s;
			}
		return null;
	}
	
	public Slot hasMechanicBikeAvailable() {
		for (Slot s : parkingSlots) {
			if (s.isOnline() == true && s.getBike() instanceof MechanicBike);
				return s;
			}
		return null;
	}

	
	public User identifyUser (Card card) {
		return card.getUser();
	}
	
	public synchronized void pickUpABike(User user, LocalDateTime pickUpTime) {
		
		if (this.hasBikeAvailable() == true) {
			for (Slot s : parkingSlots) {
				if (s.isOnline())
			}
		}
	
	
	}
	
	public Point getP() {
		return p;
	}


	public void setP(Point p) {
		this.p = p;
	}


	public boolean isOnline() {
		return isOnline;
	}


	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}


	public ArrayList<Slot> getParkingSlots() {
		return parkingSlots;
	}


	public void setParkingSlots(ArrayList<Slot> parkingSlots) {
		this.parkingSlots = parkingSlots;
		this.NumberSlots= parkingSlots.size();
	}


	public int getNumberSlots() {
		return NumberSlots;
	}

	
	public int getTotalRents() {
		return totalRents;
	}


	public void setTotalRents(int totalRents) {
		this.totalRents = totalRents;
		this.totalOperations = this.totalRents + this.totalReturns;
	}


	public int getTotalReturns() {
		return totalReturns;
	}


	public void setTotalReturns(int totalReturns) {
		this.totalReturns = totalReturns;
		this.totalOperations = this.totalRents + this.totalReturns;
	}
	
	
	public int getTotalOperations() {
		return totalOperations;
	}
	

	public int getId() {
		return id;
	}
	
	public static void main(String[] args) {
		Bike b = new ElectricBike();
		b = null;
		if (b instanceof ElectricBike) {
			System.out.println("yes");
		}
		else {System.out.println("no");}		
	}
}
