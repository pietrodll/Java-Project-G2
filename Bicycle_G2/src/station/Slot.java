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
	
	private Station s;
	private int id;
	private boolean isOnline;
	private Bike bike;
	private ArrayList<SlotState> slotHistory;
	
	//faire ID générator qui dépend de la station les trois derniers chiffres sont id du slot et l'ID de la station fois 1000
	//il y a un pb avec l'IDGenerator
	
	public Slot(Station s) {
		this.s = s;
		//id = s.getId()*1000 + SlotIDGenerator.getInstance().getNextSlotID();
		isOnline = false;
		slotHistory = new ArrayList<SlotState>();
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
	
	
	public boolean getisOccupied() {
		if (isOnline == false) {
			return true;
		}
		else if ( bike != null ) {
			return true;
		}
		else {return false;}
	}
	
	
	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline, LocalDateTime changeTime) {
		if (isOnline != this.isOnline) {
			this.isOnline = isOnline;
			SlotState lastState = slotHistory.get(slotHistory.size()-1);
			lastState.setEndTime (changeTime);
			SlotState newState = new SlotState (changeTime, isOnline, lastState.getBike());	
			slotHistory.add(newState);
		}
	}
	
	public void setBike(Bike bike, LocalDateTime changeTime) {
		if (bike != this.bike) {
			this.bike = bike;
			SlotState lastState = slotHistory.get(slotHistory.size()-1);
			lastState.setEndTime(changeTime);
			SlotState newState = new SlotState (changeTime, lastState.isOnline(), bike);
			slotHistory.add(newState);
			
		}
	}
	

	public ArrayList<SlotState> getSlotHistory() { return slotHistory; }

	public Station getS() { return s; }

	public double getId() { return id; }
	
	public Bike getBike() { return bike; }
	
	@Override
	public boolean equals(Object obj) {
		boolean res = false;
		if (obj instanceof Slot) {
			Slot other = (Slot) obj;
			res = this.getId() == other.getId();
		}
		return res;
	}


									
	
	

	
}

