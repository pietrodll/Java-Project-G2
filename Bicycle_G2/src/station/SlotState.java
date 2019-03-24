package station;

import java.time.LocalDateTime;

public class SlotState {
	
	// il y a le problème de l'initialisation et des getters and setters de isOnline et de bikeType. Il faut voir comment on ajoute un nouveau SlotState

	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private boolean isOnline;
	private int bikeType;
	
	public SlotState(LocalDateTime startTime, LocalDateTime endTime, boolean isOnline, int bikeType) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.isOnline = isOnline;
		this.bikeType = bikeType;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	
	public boolean isOnline() {
		return isOnline;
	}

	public int getBikeType() {
		return bikeType;
	}

	// no setters for the attributes that are already in slot
	
	/**
	 * This method returns whether or not a slot is occupied, notice that if the slot is out of order it is considered Occupied
	 * @return
	 */
	public boolean getisOccupied() {
		if (isOnline == false) {
			return true;
		}
		else if (getBikeType() == Slot.ELECTRIC || getBikeType()==Slot.MECHANIC) {
			return true;
		}
		else {return false;}
	}
	
	
}
