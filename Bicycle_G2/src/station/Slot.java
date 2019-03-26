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
	 
	//revoir la méthode avec le throw
	public int indexSlotState (LocalDateTime t) throws NoSlotStateAtDateException{
		int index = -1;
		for (SlotState state : this.slotHistory) {
			if (state.getStartTime().isBefore(t) && state.getEndTime().isAfter(t)){
				return this.slotHistory.indexOf(state);
			}
			if (t.equals(state.getStartTime())) {
				return this.slotHistory.indexOf(state);
			}
		}	
		int size = this.slotHistory.size();
		SlotState a = slotHistory.get(size-1);
		if (t.isAfter(a.getStartTime())){
			return size - 1;
		}
		throw new NoSlotStateAtDateException(t);	// possible que quand c'est avant le premier slot enregistré
	}
	
	
	//crée la méthode si date avant le début
	public int computeOccupationTime (LocalDateTime startTime, LocalDateTime endTime) throws NegativeTimeException, NoSlotStateAtDateException {
		int totalOccupationTime = 0;
		int iStart = indexSlotState (startTime);
		int iEnd = indexSlotState (endTime);
		
		for (int i = iStart; i <= iEnd; i++) {
			SlotState state = slotHistory.get(i);
			if (state.getisOccupied() == true) {
				if (state.getEndTime() == null) { 
					int time = Date.computeTime (state.getStartTime(), endTime);
				}
				else {int time = Date.computeTime (state.getStartTime(), state.getEndTime());
				totalOccupationTime =+ time;
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

