
package user;

import ride.Ride;
import tools.Point;
import java.util.*;

public class User implements Observer {
	
	private String lastName;
	private String firstName; 
	private Point position;
	private final int id;
	private Ride ongoingRide;
	private UserStat userStat;
	
	
	
	public User(String lastName, String firstName) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		id = UserIDGenerator.getInstance().getNextUserID();
		position = new Point (0,0);
		ongoingRide = null;
		userStat = new UserStat();
	
	}
	
	public User(String lastName, String firstName, Point position) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		id = UserIDGenerator.getInstance().getNextUserID();
		this.position = position;
		ongoingRide = null;
		userStat = new UserStat();
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
		System.out.println("The destination Station does not have any more available slots");
		net = 
		Itinerary newItinerary = ongoingRide.getItinerary().computePath(net, ps, bikeType);
		ongoingRide.setItinerary(newItinerary);
	}
}