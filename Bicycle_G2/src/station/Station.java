package station;

import java.time.LocalDateTime;
import java.util.ArrayList;

import tools.*;
import user.UserIDGenerator;
import bike.*;

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
	
	public boolean hasBikeAvailable() {
		for (Slot s : parkingSlots) {
			if (s.isOnline() == true && s.getBike() != null) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Allows to look for a specific bike type
	 * @param type
	 * @return
	 */
	public boolean hasElectricBikeAvailable() {
		for (Slot s : parkingSlots) {
			if (s.isOnline() == true && s.getBike() instanceof ElectricBike);
				return true;
			}
		return false;
	}
	
	public boolean hasMechanicBikeAvailable() {
		for (Slot s : parkingSlots) {
			if (s.isOnline() == true && s.getBike() instanceof MechanicBike);
				return true;
			}
		return false;
	}
// not finished
/*
	public void pickUpABike() {
		for (Slot s : parkingSlots) {
			if (s.isOnline() == true && s.getBikeType() != 0) {
				
			}
		
		}
	}
*/		
	
	
	
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
