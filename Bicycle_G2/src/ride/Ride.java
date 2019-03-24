package ride;
import java.time.LocalDateTime;

import bike.*;
import user.*;
import ride.*;

public class Ride {
	
	private Itinerary itinerary;
	private Bike bike;
	private User user;
	private LocalDateTime startRide;
	private LocalDateTime endRide; 
	
	public Ride(Itinerary itinerary, Bike bike, User user, LocalDateTime startRide) {
		super();
		this.itinerary = itinerary;
		this.bike = bike;
		this.user = user;
		this.startRide = startRide;
	}
	

	public Itinerary getItinerary() {
		return itinerary;
	}

	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}

	public Bike getBike() {
		return bike;
	}

	public User getUser() {
		return user;
	}

	public LocalDateTime getStartRide() {
		return startRide;
	}

	public LocalDateTime getEndRide() {
		return endRide;
	}


	public void endRide(LocalDateTime endRide) {
		this.endRide = endRide;
		Network.archiveRide(this);
	}
	
	

	
	

}
