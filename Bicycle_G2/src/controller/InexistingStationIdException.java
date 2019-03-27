package controller;

public class InexistingStationIdException extends Exception {
	
	private int stationID;

	public InexistingStationIdException(int id) {
		this.stationID = id;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getMessage() {
		String message = "Could not find a station with id " + this.stationID;
		return message;
	}

}
