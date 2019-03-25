
package user;

import ride.Itinerary;
import ride.Network;
import ride.Ride;
import tools.NegativeTimeException;
import tools.Point;

import java.time.LocalDateTime;
import java.util.*;
import bike.Bike;
import card.Card;

public class User implements Observer {
	
	private String lastName;
	private String firstName; 
	private Point position;
	private final int id;
	private Ride ongoingRide;
	private Itinerary itinerary;
	private UserStat userStat;
	
	
	
	public User(String lastName, String firstName) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		id = UserIDGenerator.getInstance().getNextUserID();
		position = new Point (0,0);
		ongoingRide = null;
		itinerary = null;
		userStat = new UserStat();
	
	}
	
	public User(String lastName, String firstName, Point position) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		id = UserIDGenerator.getInstance().getNextUserID();
		this.position = position;
		ongoingRide = null;
		itinerary = null;
		
		userStat = new UserStat();
	}
	
	//la fonction startOngoingRide est forcément utilsée par la fonction pickUpBike qui vérifie deja si ongoing riede
	public void startOngoingRide (Network net, Bike bike, LocalDateTime startRide, Card card) {
		this.ongoingRide = new Ride (net, bike, this, card, startRide);
	}
	
 // when finishing a ride you have to add all the user stat : time and credit
	public void endOngoingRide(LocalDateTime endRide) throws NegativeTimeException {
		int timeRide = ongoingRide.getRideTime();
		Bike bike = ongoingRide.getBike();
		Card card = ongoingRide.getCard();
		ongoingRide.endRide(endRide);
		float price = bike.ridePrice(card, timeRide);
		userStat.setNumberRides(userStat.getNumberRides() + 1 );	
		userStat.setTotalAmount(userStat.getTotalAmount() + price);
		userStat.setTotalTime(userStat.getTotalTime() + timeRide);
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}


	public Ride getOngoingRide() {
		return ongoingRide;
	}

	public void setOngoingRide(Ride ongoingRide) {
		this.ongoingRide = ongoingRide;
	}

	public Itinerary getItinerary() {
		return itinerary;
	}

	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}

	public int getId() {
		return id;
	}

	public UserStat getUserStat() {
		return userStat;
	}

	public void setUserStat(UserStat userStat) {
		this.userStat = userStat;
	}
	
	
	@Override
	public void update(boolean isStationFull) {
	/*	System.out.println("The destination Station does not have any more available slots");
		Itinerary newItinerary = ongoingRide.getItinerary().computePath(ps, bikeType);
		ongoingRide.setItinerary(newItinerary);
		*/
	}
}