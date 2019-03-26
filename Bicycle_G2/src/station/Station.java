package station;

import java.time.LocalDateTime;
import java.util.ArrayList;

import tools.*;
import user.User;
import user.UserIDGenerator;
import bike.*;
import card.Card;
import ride.Network;

/**
 * An abstract class to represent the stations
 *
 */
public abstract class Station {
	
	private int id;
	private Point p;
	private boolean isOnline;
	private ArrayList<Slot> parkingSlots;
	private Network net;
	
	private int totalRents;
	private int totalReturns;
	private int totalOperations;

	

	public Station(Point p) {
		this.p = p;
		id = StationIDGenerator.getInstance().getNextStationID();
		this.parkingSlots = new ArrayList<Slot>();
	}


	public int getRateOccupation(LocalDateTime startTime, LocalDateTime endTime) throws NoSlotStateAtDateException{
		int occupationRate = -1;
		try {
			int totalOccupationTime = 0;
			long delta = Date.computeTime (startTime, endTime);
			int N = parkingSlots.size();
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
			if (!s.getisOccupied()) {
				return false;
			}
		}
		return true;
	}
	
	public Slot availableSlot() {
		for (Slot s : parkingSlots) {
			if (s.getisOccupied() == false) {
				return s;
			}
		}
		return null;
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
	
	public synchronized void pickUpBike(Card card, LocalDateTime pickUpTime) throws NegativeTimeException {
		User user = identifyUser (card);
		if (user.getOngoingRide() == null) {
			Slot s = hasBikeAvailable();
			if (s != null) {
				Bike b = s.getBike();
				user.startOngoingRide(this.net, b, pickUpTime, card);
				s.setBike(null, pickUpTime);
				this.setTotalRents(getTotalRents()+1);
			} else { System.out.println("Could not pick up a bike from Station" + this + ", no bike available");
			}
		} else {System.out.println("Could not pick up a bike because there is already an ongoing Ride ");	
		}
	}
	
	public synchronized void pickUpElectricBike(Card card, LocalDateTime pickUpTime) throws NegativeTimeException {
		User user = identifyUser (card);
		if (user.getOngoingRide() == null) {
			Slot s = hasElectricBikeAvailable();
			if (s != null) {
				Bike b = s.getBike();
				user.startOngoingRide(this.net, b, pickUpTime, card);
				s.setBike(null, pickUpTime);
				this.setTotalRents(getTotalRents()+1);
			}else { System.out.println("Could not pick up an electric bike from Station" + this + ", no electric bike available");
			}
		} else {System.out.println("Could not pick up a bike because there is already an ongoing Ride ");	
		}
	}
	
	public synchronized void pickUpMechanicBike(Card card, LocalDateTime pickUpTime) throws NegativeTimeException {
		User user = identifyUser (card);
		if (user.getOngoingRide() == null) {
			Slot s = hasMechanicBikeAvailable();
			if (s != null) {
				Bike b = s.getBike();
				user.startOngoingRide(this.net, b, pickUpTime, card);
				s.setBike(null, pickUpTime);
				this.setTotalRents(getTotalRents()+1);
			}else { System.out.println("Could not pick up a mechanic bike from Station" + this + ", no mechanic bike available");
			}
		} else {System.out.println("Could not pick up a bike because there is already an ongoing Ride ");	
		}
	}
	
	public synchronized void dropBike (Card card, LocalDateTime dropTime) throws NegativeTimeException {
		User user = identifyUser (card);
		if (user.getOngoingRide() != null) {
			if (this.isStationFull() == false) {
				Slot s = availableSlot();
				s.setBike(user.getOngoingRide().getBike(), dropTime);
				this.setTotalReturns(getTotalReturns()+1);
				user.endOngoingRide(dropTime);
			}else {System.out.println("Could not drop bike because Station" + this + "is full");
			}
		} else {System.out.println("Could not drop bike because there is no Ongoing Ride");}
	}
	
	// faire les méthodes pour quand une station tombe en panne
	
	public Point getP() { return p; }

	public void setP(Point p) { this.p = p; }

	public boolean isOnline() { return isOnline; }

	public void setOnline(boolean isOnline) { this.isOnline = isOnline; }

	public ArrayList<Slot> getParkingSlots() { return parkingSlots; }

	public void addSlot() {
		this.parkingSlots.add(new Slot(this)); }
	
	public void removeSlot (Slot slot) {
		for (Slot s : parkingSlots) {
			if (s.equals(slot)) {
				parkingSlots.remove(s);
			}
		}
	}

	public int getTotalRents() { return totalRents; }

	public void setTotalRents(int totalRents) {
		this.totalRents = totalRents;
		this.totalOperations = this.totalRents + this.totalReturns;
	}

	public int getTotalReturns() { return totalReturns; }

	public void setTotalReturns(int totalReturns) {
		this.totalReturns = totalReturns;
		this.totalOperations = this.totalRents + this.totalReturns;
	}
	
	public int getTotalOperations() { return totalOperations; }
	
	public int getId() { return id; }
	
	public static void main(String[] args) {
		Bike b = new ElectricBike();
		b = null;
		if (b instanceof ElectricBike) {
			System.out.println("yes");
		}
		else {System.out.println("no");}		
	}
	
	@Override
	public abstract boolean equals(Object obj);
}
