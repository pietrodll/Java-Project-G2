package station;

import java.time.LocalDateTime;

import bike.Bike;

public class SlotState {
	

	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private boolean isOnline;
	private Bike bike;
	
	public SlotState(LocalDateTime startTime, boolean isOnline, Bike bike) {
		super();
		this.startTime = startTime;
		this.isOnline = isOnline;
		this.bike = bike;
	}
	
	public SlotState(LocalDateTime startTime, LocalDateTime endTime, boolean isOnline, Bike bike) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.isOnline = isOnline;
		this.bike = bike;
	}
	
// Il faut voir comment on crée le premier SlotState
	
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

	public Bike getBike() {
		return bike;
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
	
	
}
