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
	
	
	public Slot(Station s) {
		this.s = s;
		id = SlotIDGenerator.getInstance().getSlotID(s);
		bike = null;
		isOnline = true;
		slotHistory = new ArrayList<SlotState>();
	}
	 
	public int indexSlotState (LocalDateTime t) throws NoSlotStateAtDateException{
		int i;
		int size = this.slotHistory.size();
		for (i=0; i<=size -2; i ++) {
			if (slotHistory.get(i).getStartTime().isBefore(t) && slotHistory.get(i).getEndTime().isAfter(t)){
				return i;
			}
			if (t.equals(slotHistory.get(i).getStartTime())) {
				return i;
			}
		}
		if (t.equals(slotHistory.get(size-1).getStartTime())) {
			return i;
		}
		SlotState a = slotHistory.get(size-1);
		if (t.isAfter(a.getStartTime())){
			return size - 1;
		}
		else {
			throw new NoSlotStateAtDateException(t);	
		}
	}
	
	
	public int computeOccupationTime (LocalDateTime startTime, LocalDateTime endTime) throws NegativeTimeException, NoSlotStateAtDateException {
		int totalOccupationTime = 0;
		int iStart;
		int iEnd;
		try {
			iEnd = indexSlotState (endTime);
		} catch (NoSlotStateAtDateException e){
			System.out.println("The Slot " + this + " did not exist during this interval of time ");
			return -1;
		} try {
			iStart = indexSlotState (startTime);
		} catch (NoSlotStateAtDateException e) {
			iStart = 0;
		}
		for (int i = iStart; i <= iEnd; i++) {
			SlotState state = slotHistory.get(i);
			if (state.getisOccupied() == true) {
				if (state.getEndTime() == null) { 
					totalOccupationTime += Date.computeTime (state.getStartTime(), endTime);	
				}
				else {totalOccupationTime += Date.computeTime (state.getStartTime(), state.getEndTime());
				}
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

	public void setOnline(boolean isOnline, LocalDateTime changeTime) throws NegativeTimeException {
		if (isOnline != this.isOnline) {
			this.isOnline = isOnline;
			if (slotHistory.size()!= 0) {
				SlotState lastState = slotHistory.get(slotHistory.size()-1);
				lastState.setEndTime (changeTime);}
			SlotState newState = new SlotState (changeTime, isOnline, this.getBike());
			slotHistory.add(newState);
		}
	}
	
	public void setBike(Bike bike, LocalDateTime changeTime) throws NegativeTimeException {
		if (bike != this.bike) {
			this.bike = bike;
			if (slotHistory.size()!= 0) {
				SlotState lastState = slotHistory.get(slotHistory.size()-1);
				lastState.setEndTime(changeTime);}
			SlotState newState = new SlotState (changeTime, this.isOnline(), bike);
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

