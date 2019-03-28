
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

/**
 * This class represents the users of the network. A {@code User} is created with a Username, and can have or not an initial position.
 * Each {@code User} has a unique {@code id} and its own {@code UserStat} class that contains his statistics.
 * A {@code User} can have an Ongoing {@code Ride} and can be following an {@code Itinerary}.
 * @author Chloé
 *@see UserStat
 *@see UserIDGenerator
 */
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
	
	/**
	 * If the {@code User} does not have an ongoing {@code Ride}, this method sets a new ongoing {@code Ride}. This method checks if the card used belongs to the user.
	 * @param net
	 * @param bike
	 * @param startRide
	 * @param card
	 */
	public void startOngoingRide (Network net, Bike bike, LocalDateTime startRide, Card card) {
		if (ongoingRide == null) {
			if (card.getUser() == this) {
				this.ongoingRide = new Ride (net, bike, this, card, startRide);
			}
		}
	}
	
	/**
	 * If the {@code User} has an ongoing {@code Ride}, this method ends the ongoing {@code Ride}, resets the {@code Itinerary}, computes the cost of the ride and updates the user's statistics.
	 * @param endRide
	 * @throws NegativeTimeException
	 */
	public void endOngoingRide(LocalDateTime endRide) throws NegativeTimeException {
		if (this.ongoingRide != null) {
			if (this.itinerary != null) {
				this.itinerary = null;	
			}
			ongoingRide.endRide(endRide);
			int timeRide = ongoingRide.getRideTime();
			float price = ongoingRide.getBike().ridePrice(ongoingRide.getCard(), timeRide);
			
			userStat.addRide();
			userStat.addAmount(price);	
			userStat.addTime(timeRide);
			this.ongoingRide = null;
		}
	}
	
	/**
	 * This method calculates an itinerary given an arrival position and a {@code PathStrategy}. The start position is the current position of the {@code} User.
	 * @param arrival
	 * @param ps
	 * @return itinerary
	 */
	public Itinerary calculateItinerary(Point arrival, PathStrategy ps) {
		Itinerary i1 = new Itinerary(this.position, arrival);
		i1.computePath(ps);
		return i1;		
	}
	
	/**
	 * This method calculates an itinerary given a starting position, an arrival position and a {@code PathStrategy}. 
	 * @param start
	 * @param arrival
	 * @param ps
	 * @return itinerary
	 */
	public Itinerary calculateItinerary(Point start, Point arrival, PathStrategy ps) {
		Itinerary i1 = new Itinerary(start, arrival);
		i1.computePath(ps);
		return i1;		
	}
	
	
	@Override
	public void update(boolean isStationFull) {
	/*	System.out.println("The destination Station does not have any more available slots");
		Itinerary newItinerary = ongoingRide.getItinerary().computePath(ps, bikeType);
		ongoingRide.setItinerary(newItinerary);
		*/
	}
	
	public String getUserName() { return userName; }

	public Point getPosition() { return position; }
	public void setPosition(Point position) { this.position = position; }

	public Ride getOngoingRide() { return ongoingRide; }

	public Itinerary getItinerary() { return itinerary; }
	public void setItinerary(Itinerary itinerary) { this.itinerary = itinerary; }

	public int getId() { return id; }

	public UserStat getUserStat() { return userStat; }
	
	/**
	 * This method redefines the equals method for the {@code User} class. {@code User} are compared on their ID.
	 */
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