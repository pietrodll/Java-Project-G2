package station;

import java.time.LocalDateTime;

public class NoSlotStateAtDateException extends Exception {
	
	private LocalDateTime time;

	public NoSlotStateAtDateException(LocalDateTime time) {
		super();
		this.time = time;
		System.out.println("There is no Slot State saved for time : [" + time + "].");
	}

	public LocalDateTime getTime() {
		return time;
	}


	
	

}