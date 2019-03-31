package ui.clui;

public enum Command {
	
	SETUP("setup", "setup <networkName> or setup <networkName> <nStations> <nSlots> <side> <nBikes>"),
	RUNTEST("runtest", "runtest <fileName>"),
	STATION_ONLINE("setStationOnline", "stationOnline <networkName> <stationID>"),
	STATION_OFFLINE("setStationOffline", "stationOffline <networkName> <stationID>"),
	SLOT_ONLINE("setSlotOnline", "slotOnline <networkName> <slotID> <time>"),
	SLOT_OFFLINE("setSlotOffline", "slotOffline <networkName> <slotID> <time>"),
	ADD_STATION("addStation", "addStation <networkName> <type> <numSlots> <positionX> <positionY>"),
	ADD_SLOT("addSlot", "addSlot <networkName> <stationID> <numSlots>"),
	ADD_BIKE("addBike", "addBike <networkName> <type> <time>"),
	ADD_USER("addUser", "addUser <username> <cardType> <networkName>"),
	RETURN_BIKE("returnBike", "returnBike <userID> <stationID> <time> <networkName>"),
	RENT_BIKE("rentBike", "rentBike <userID> <stationID> <bikeType> <time> <networkName>"),
	DISPLAY_USER("displayUser", "displayUser <networkName> <userID>"),
	DISPLAY_STATION("displayStation", "displayStation <networkName> <stationID>"),
	DISPLAY("display", "display <networkName>"),
	SORT_STATION("sortStation", "sortStation <networkName> <more-used> or sortStation <networkName> <least-occupied> <startTime> <endTime>"),
	CALCULATE_ITINERARY("calculateItinerary", "calculateItinerary <networkName> <userID> <startX> <startY> <destinationX> <destinationY> <pathStrategy>");
	
	
	private String keyword;
	private String commandFormat;
	
	Command(String s, String t) {
		this.keyword = s;
		this.commandFormat = t;
	}
	
	public String getKeyword() { return this.keyword; }
	public String getFormat() { return this.commandFormat; }

}
