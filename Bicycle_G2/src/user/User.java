
package user;

import ride.Itinerary;
import ride.Network;
import ride.Ride;
import ride.path.PathStrategy;
import station.NoOngoingRideException;
import station.OngoingRideException;
import tools.NegativeTimeException;
import tools.NullDateException;
import tools.Point;

import java.time.LocalDateTime;
import java.util.Scanner;

import bike.Bike;
import card.Card;

/**
 * This class represents the users of the network. A {@code User} is created with a Username, and can have or not an initial position.
 * Each {@code User} has a unique {@code id} and its own {@code UserStat} class that contains his statistics.
 * A {@code User} can have an Ongoing {@code Ride} and can be following an {@code Itinerary}.
 * @author Chlo�
 * @see UserStat
 * @see UserIdGenerator
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
		id = UserIdGenerator.getInstance().getNextUserID();
		position = new Point (0,0);
		ongoingRide = null;
		itinerary = null;
		userStat = new UserStat();
	}
	
	public User(String userName, Point position) {
		super();
		this.userName = userName;
		id = UserIdGenerator.getInstance().getNextUserID();
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
	 * @throws OngoingRideException 
	 */
	public void startOngoingRide (Network net, Bike bike, LocalDateTime startRide, Card card) throws OngoingRideException {
		if (ongoingRide == null) {
			if (card.getUser() == this) {
				this.ongoingRide = new Ride (net, bike, this, card, startRide);
			}
		} else {throw new OngoingRideException();}
	}
	
	/**
	 * If the {@code User} has an ongoing {@code Ride}, this method ends the ongoing {@code Ride}, resets the {@code Itinerary}, computes the cost of the ride and updates the user's statistics.
	 * The method removes the {@code User} from the Observers of the arrival {@code Station}.
	 * @param endRide
	 * @throws NegativeTimeException
	 * @throws NullDateException 
	 * @throws NoOngoingRideException 
	 */
	public double endOngoingRide(LocalDateTime endRide) throws NegativeTimeException, NullDateException, NoOngoingRideException {
		if (this.ongoingRide != null) {
			if (this.itinerary != null) {
				this.itinerary.getEndStation().removeObserver(this);
				this.itinerary = null;	
			}
			ongoingRide.endRide(endRide);
			int timeRide = ongoingRide.getRideTime();
			float price = ongoingRide.getBike().ridePrice(ongoingRide.getCard(), timeRide);
			this.userStat.addRide();
			this.userStat.addAmount((double)price);	
			this.userStat.addTime(timeRide);
			this.ongoingRide = null;
			return price;
		} else {throw new NoOngoingRideException();}
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
	
	/**
	 * This method sets the {@code Itinerary} of a {@code User}. The {@code User} is added to the observer list of the arrival {@code Station}.
	 * @param itinerary
	 */
	public void setItinerary(Itinerary itinerary) { 
		this.itinerary = itinerary; 
		itinerary.getEndStation().registerObserver(this);
	}
	
	/**
	 * The {@code User} is notified if he has an {@code Itinerary} and if the arrival {@code Station} of its {@code Itinerary} becomes full. He has the possibility to recalculte the ending {@code Station}.
	 */
	@Override
	public void update(){
		System.out.println("The destination Station does not have any more available slots");
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to recalculate arrival station ? Answer 'yes' if you do. ");
		String s = sc.nextLine();
		sc.close();
		if (s.equals("yes")) {
			PathStrategy ps = this.itinerary.getPs();
			this.itinerary.setEndStation(ps.findEndStation(this.position, this.getItinerary().getEnd(), this.getOngoingRide().getBike()));
		} else {this.itinerary = null;}
		
	}
	
	public String getUserName() { return userName; }

	public Point getPosition() { return position; }
	public void setPosition(Point position) { this.position = position; }

	public Ride getOngoingRide() { return ongoingRide; }

	public Itinerary getItinerary() { return itinerary; }

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