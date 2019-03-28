
package user;

import ride.Itinerary;
import ride.Network;
import ride.Ride;
import ride.path.PathStrategy;
import tools.NegativeTimeException;
import tools.Point;

import java.time.LocalDateTime;
import bike.Bike;
import card.Card;

public class User implements Observer {
	
	private String userName;
	private Point position;
	private final int id;
	private Ride ongoingRide;
	private Itinerary itinerary;
	private UserStat userStat;
	
	
	public User(String userName) {
		super();
		this.userName = userName;
		id = UserIDGenerator.getInstance().getNextUserID();
		position = new Point (0,0);
		ongoingRide = null;
		itinerary = null;
		userStat = new UserStat();
	}
	
	public User(String userName, Point position) {
		super();
		this.userName = userName;
		id = UserIDGenerator.getInstance().getNextUserID();
		this.position = position;
		ongoingRide = null;
		itinerary = null;
		userStat = new UserStat();
	}
	
	
	public void startOngoingRide (Network net, Bike bike, LocalDateTime startRide, Card card) {
		if (ongoingRide == null) {
			this.ongoingRide = new Ride (net, bike, this, card, startRide);
		}
	}
	
	public void endOngoingRide(LocalDateTime endRide) throws NegativeTimeException {
		if (ongoingRide != null) {
			if (this.itinerary != null) {
				this.itinerary = null;	
			}
			int timeRide = ongoingRide.getRideTime();
			Bike bike = ongoingRide.getBike();
			Card card = ongoingRide.getCard();
			ongoingRide.endRide(endRide);
			float price = bike.ridePrice(card, timeRide);
			userStat.addRide();
			userStat.addAmount(price);	
			userStat.addTime(timeRide);
		}
	}
	
	public Itinerary calculateItinerary(Point arrival, PathStrategy ps) {
		Itinerary i1 = new Itinerary(this.position, arrival);
		i1.computePath(ps);
		return i1;		
	}
	
	public Itinerary calculateItinerary(Point start, Point arrival, PathStrategy ps) {
		Itinerary i1 = new Itinerary(start, arrival);
		i1.computePath(ps);
		return i1;		
	}
	
	public String getUserName() { return userName; }
	public void setLastName(String userName) { this.userName = userName; }

	public Point getPosition() { return position; }
	public void setPosition(Point position) { this.position = position; }


	public Ride getOngoingRide() { return ongoingRide; }
	public void setOngoingRide(Ride ongoingRide) { this.ongoingRide = ongoingRide; }

	public Itinerary getItinerary() { return itinerary; }
	public void setItinerary(Itinerary itinerary) { this.itinerary = itinerary; }

	public int getId() { return id; }

	public UserStat getUserStat() { return userStat; }
	public void setUserStat(UserStat userStat) { this.userStat = userStat; }
	
	
	@Override
	public void update(boolean isStationFull) {
	/*	System.out.println("The destination Station does not have any more available slots");
		Itinerary newItinerary = ongoingRide.getItinerary().computePath(ps, bikeType);
		ongoingRide.setItinerary(newItinerary);
		*/
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean res = false;
		if (obj instanceof User) {
			User other = (User) obj;
			res = this.getId() == other.getId();
		}
		return res;
	}
}