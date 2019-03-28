package user.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import bike.Bike;
import bike.ElectricBike;
import card.Card;
import card.VlibreCard;
import ride.Network;
import tools.NegativeTimeException;
import tools.Point;
import user.User;

public class UserTest {
	Network net = new Network();
	Point p1 = new Point (3, 9);
	User u1 = new User("PietroDellino");
	User u2 = new User("ChloéGentgen", p1);
	User u3 = new User("PietroDellino");
	Bike b1 = new ElectricBike();
	Card c1 = new VlibreCard(u1);
	LocalDateTime t1 = LocalDateTime.of(2019,  03, 10, 16, 00);
	LocalDateTime t2 = LocalDateTime.of(2019,  03, 10, 16, 30);
		
	@Test
	void testDifferentIDUsers() {
		assertNotEquals(u1.getId(), u2.getId());
	}

	@Test
	void testDifferentUsers() {
		assertNotEquals(u1, u3);
	}
	

	@Test
	void testStartOngoingRide () {
		u1.startOngoingRide(net, b1, t1, c1);
		assertAll("Checks if the created ride is correct",
				() -> assertEquals(net, u1.getOngoingRide().getNet()),
				() -> assertEquals(b1, u1.getOngoingRide().getBike()),
				() -> assertEquals(t1, u1.getOngoingRide().getStartRide()),
				() -> assertEquals(null, u1.getOngoingRide().getEndRide()),
				() -> assertEquals(c1, u1.getOngoingRide().getCard()),
				() -> assertNotNull(u1.getOngoingRide()),
				() -> assertEquals(0, u1.getUserStat().getNumberRides())
		);
		
		
	}
	
	@Test
	void testEndOngoingRide () throws NegativeTimeException {
		u1.startOngoingRide(net, b1, t1, c1);
		u1.endOngoingRide(t2);
		assertAll("Checks if the ended ride is correct",
				() -> assertNull(u1.getOngoingRide()),
				() -> assertEquals(1, net.getRideHistory().size()),
				() -> assertEquals(t2, net.getRideHistory().get(0).getEndRide()),
				() -> assertEquals(30, net.getRideHistory().get(0).getRideTime()),
				() -> assertEquals (1, u1.getUserStat().getNumberRides()),
				() -> assertEquals(30, u1.getUserStat().getTotalTime())
				
		);
		
		
	}
}
