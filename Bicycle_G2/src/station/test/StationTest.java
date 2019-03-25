package station.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ride.Network;
import station.*;
import tools.Point;

public class StationTest {
	
	private static Network net;
	private static StationFactory sf;
	
	@BeforeAll
	static void set() {
		net = Network.getNetwork();
		sf = new StationFactory(net);
	}
	
	@Test
	void testDifferentIdStation() throws TypeStationException, StationSamePositionException {
		Point p1 = new Point (1,3);
		Point p2 = new Point (2,3);
		Point p3 = new Point (4,9);
		Station s1 = sf.createStation("Standard", p1);
		Station s2 = sf.createStation("Standard", p2);
		Station s3 = sf.createStation("Plus", p3);
		assertAll(
				() -> assertEquals(0, s1.getId()),
				() -> assertEquals(1, s2.getId()),
				() -> assertEquals(2, s3.getId())
		);
					
	}
				
	

	@Test 
	void testSamePositionStation() throws TypeStationException, StationSamePositionException {
		Point p1 = new Point(3, 10);
		Station s1 = sf.createStation("Standard", p1);
		assertThrows(StationSamePositionException.class,
				() -> {
					Station s2 = sf.createStation("Standard", p1);
				}
		);
		
	}
	

}
