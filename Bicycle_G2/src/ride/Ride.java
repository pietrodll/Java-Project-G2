package ride;
import java.time.LocalDateTime;
import java.util.ArrayList;

import bike.*;
import user.*;
import ride.*;

public class Ride implements Observable{
	
	private Bike bike;
	private User user;
	private LocalDateTime startRide;
	private LocalDateTime endRide; 
	private boolean changed;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	
	public Ride(Bike bike, User user, LocalDateTime startRide) {
		super();
		this.bike = bike;
		this.user = user;
		this.startRide = startRide;
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
	
	@Override
	public void registerObserver (Observer observer) {
		observers.add(observer);}


	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);}

	@Override 
	public void notifyObservers () {
		if (this.itinerary.getEndStation().isStationFull()) {
			for (Observer ob : observers)
				ob.update(this.itinerary.getEndStation().isStationFull());
		}
	}
	

}
