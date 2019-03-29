package ui.clui;

public enum Commands {
	
	SETUP("setup"),
	RUNTEST("runtest"),
	STATION_ONLINE("setStationOnline"),
	STATION_OFFLINE("setStationOffline"),
	SLOT_ONLINE("setSlotOnline"),
	SLOT_OFFLINE("setSlotOffline"),
	ADD_STATION("addStation"),
	ADD_SLOT("addSlot"),
	ADD_BIKE("addBike"),
	RETURN_BIKE("returnBike"),
	RENT_BIKE("rentBike"),
	CALCULATE_ITINERARY("calculateItinerary");
	
	
	private String keyword;
	
	Commands(String s) {
		this.keyword = s;
	}
	
	public String getKeyword() { return this.keyword; }

}
