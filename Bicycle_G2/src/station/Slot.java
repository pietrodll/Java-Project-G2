package station;

import java.time.LocalDateTime;
import java.util.ArrayList;

import bike.Bike;
import tools.Date;
import tools.NegativeTimeException;
import user.UserIDGenerator;

/**
 * A class representing parking slots
 *
 */
public class Slot {
	
	public static final int EMPTY = 0;
	public static final int ELECTRIC = 1;
	public static final int MECHANIC = 2;

	private Station s;
	private double id;
	private boolean isOnline;
	private int bikeType = Slot.EMPTY;
	private Bike bike;
	private ArrayList<SlotState> slotHistory;
	
	//faire ID générator qui dépend de la station les trois derniers chiffres sont id du slot et l'ID de la station fois 1000

	
	//il y a un pb avec l'IDGenerator
	public Slot(Station s) {
		this.s = s;
		id = s.getId()*1000 + SlotIDGenerator.getInstance().getNextSlotID();
		isOnline = false;
	}

	public int indexSlotState (LocalDateTime t) {
		int index = -1;
		for (SlotState state : slotHistory) {
			if (state.getStartTime().isBefore(t) && t.isBefore(state.getStartTime())){
				index = slotHistory.indexOf(s);
			}
			else { System.out.println("Error : no slot state has been saved for time [" + t + "].");
			}	
		}
	return index;
	}
	
	public long computeOccupationTime (LocalDateTime startTime, LocalDateTime endTime) throws NegativeTimeException {
		long totalOccupationTime = 0;
		int iStart = indexSlotState (startTime);
		int iEnd = indexSlotState (endTime);
		
		for (int i = iStart; i <= iEnd; i++) {
			SlotState state = slotHistory.get(i);
			if (state.getisOccupied() == true) {
				long time = Date.computeTime (state.getStartTime(), state.getEndTime());
				totalOccupationTime =+ time;
			}
			
		}
		return totalOccupationTime;
	}
	
	
	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public int getBikeType() {
		return bikeType;
	}

	public void setBikeType(int bikeType) {
		this.bikeType = bikeType;
	}

	public ArrayList<SlotState> getSlotHistory() {
		return slotHistory;
	}

	public void setSlotHistory(ArrayList<SlotState> slotHistory) {
		this.slotHistory = slotHistory;
	}

	public Station getS() {
		return s;
	}

	public double getId() {
		return id;
	}
	
	public boolean getisOccupied() {
		if (isOnline == false) {
			return true;
		}
		else if (getBikeType() == Slot.ELECTRIC || getBikeType()==Slot.MECHANIC) {
			return true;
		}
		else {return false;}
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

										
	
	

	
}
