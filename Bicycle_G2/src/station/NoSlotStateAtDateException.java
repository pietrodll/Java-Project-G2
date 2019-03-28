package station;

import java.time.LocalDateTime;

public class NoSlotStateAtDateException extends Exception {
	
	private static final long serialVersionUID = 2651831164435357865L;
	
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