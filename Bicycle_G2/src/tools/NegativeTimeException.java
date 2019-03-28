package tools;

import java.time.LocalDateTime;

public class NegativeTimeException extends Exception {
	
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	public NegativeTimeException(LocalDateTime startTime, LocalDateTime endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		System.out.println("Error : The starting date [" + startTime + "] is after the ending date [" + endTime + "]."  );
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}
	
	
	

}
