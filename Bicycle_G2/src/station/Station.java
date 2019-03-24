package station;

import java.time.LocalDateTime;
import java.util.ArrayList;

import tools.Date;
import tools.NegativeTimeException;
import tools.Point;
import user.UserIDGenerator;

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
	}


	public int getNumberSlots() {
		return NumberSlots;
	}


	public void setNumberSlots(int numberSlots) {
		NumberSlots = numberSlots;
	}


	public int getTotalRents() {
		return totalRents;
	}


	public void setTotalRents(int totalRents) {
		this.totalRents = totalRents;
	}


	public int getTotalReturns() {
		return totalReturns;
	}


	public void setTotalReturns(int totalReturns) {
		this.totalReturns = totalReturns;
	}


	public int getId() {
		return id;
	}


	public int getTotalOperations() {
		return getTotalRents()+ getTotalReturns() ;
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
			if (s.isOnline() == true && s.getBikeType() != 0) {
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
	public boolean hasBikeAvailable(int type) {
		for (Slot s : parkingSlots) {
			if (s.isOnline() == true && s.getBikeType() != type) {
				return true;
			}
		}
		return false;
	}
// not finished
	public void pickUpABike() {
		for (Slot s : parkingSlots) {
			if (s.isOnline() == true && s.getBikeType() != 0) {
				
			}
		
		}
	}
		
	
}
