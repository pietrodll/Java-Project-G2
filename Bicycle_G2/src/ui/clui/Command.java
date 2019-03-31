package ui.clui;

public enum Command {
	
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
	DISPLAY_USER("displayUser"),
	DISPLAY_STATION("displayStation"),
	DISPLAY("display"),
	SORT_STATION("sortStation"),
	CALCULATE_ITINERARY("calculateItinerary");
	
	
	private String keyword;
	
	Command(String s) {
		this.keyword = s;
	}
	
	public String getKeyword() { return this.keyword; }

}
