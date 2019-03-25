package ride;
import java.time.LocalDateTime;
import java.util.ArrayList;

import bike.*;
import card.Card;
import user.*;
import ride.*;
import tools.Date;
import tools.NegativeTimeException;

public class Ride implements Observable{
	
	private Bike bike;
	private User user;
	private Card card;
	private LocalDateTime startRide;
	private LocalDateTime endRide; 
	private boolean changed;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	
	public Ride(Bike bike, User user, Card card, LocalDateTime startRide) {
		super();
		this.bike = bike;
		this.user = user;
		this.card = card;
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


	public Card getCard() {
		return card;
	}


	public void endRide(LocalDateTime endRide) {
		this.endRide = endRide;
		Network.archiveRide(this);
	}
	
	public int getRideTime() throws NegativeTimeException {
		int rideTime = -1;
		if (endRide != null) {
			rideTime = Date.computeTime(startRide, endRide);
		} else { System.out.println("Could not get the total ride time because the ride is not finished");
		}
		return rideTime;
	}
	
	@Override
	public void registerObserver (Observer observer) {
		observers.add(observer);}


	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);}


	@Override 
	public void notifyObservers () {
		/*if (this.itinerary.getEndStation().isStationFull()) {
			for (Observer ob : observers)
				ob.update(this.itinerary.getEndStation().isStationFull());
		}*/
	}

}
