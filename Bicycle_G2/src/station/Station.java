package station;

import java.util.ArrayList;

import point.Point;
import user.UserIDGenerator;

/**
 * An abstract class to represent the stations
 *
 */
public abstract class Station {
	
	private double id;
	private Point p;
	private boolean isOnline;
	private ArrayList<Slot> parkingSlots;
	private int NumberSlots;
	
	private double totalRents;
	private double totalReturns;
	
	
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


	public double getTotalRents() {
		return totalRents;
	}


	public void setTotalRents(double totalRents) {
		this.totalRents = totalRents;
	}


	public double getTotalReturns() {
		return totalReturns;
	}


	public void setTotalReturns(double totalReturns) {
		this.totalReturns = totalReturns;
	}


	public double getId() {
		return id;
	}
	

	
}
