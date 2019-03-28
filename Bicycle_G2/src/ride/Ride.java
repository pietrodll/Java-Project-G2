package ride;
import java.time.LocalDateTime;

import bike.*;
import card.Card;
import user.*;
import tools.Date;
import tools.NegativeTimeException;

public class Ride {
	
	private Bike bike;
	private User user;
	private Card card;
	private LocalDateTime startRide;
	private LocalDateTime endRide; 
	private Network net;
	
	
	public Ride(Network net, Bike bike, User user, Card card, LocalDateTime startRide) {
		super();
		this.net = net;
		this.bike = bike;
		this.user = user;
		this.card = card;
		this.startRide = startRide;
	}
	

	public Network getNet() { return this.net; }
	public Bike getBike() { return bike; }
	public User getUser() { return user; }
	public LocalDateTime getStartRide() { return startRide; }
	public LocalDateTime getEndRide() { return endRide; }
	public Card getCard() { return card; }
	

	public void endRide(LocalDateTime endRide) {
		this.endRide = endRide;
		net.archiveRide(this);
	}
	
	
	public int getRideTime() throws NegativeTimeException {
		int rideTime = -1;
		if (this.endRide != null) {
			rideTime = Date.computeTime(this.startRide, this.endRide);
		} else { System.out.println("Could not get the total ride time because the ride is not finished");
		}
		return rideTime;
	}
	

	

}
