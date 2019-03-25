package station.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ride.Network;
import station.*;
import tools.Point;

public class StationTest {
	
	private Network net;
	private StationFactory sf;
	
	@BeforeEach
	void setUp() {
		net = new Network();
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
				() -> assertEquals(s1.getId()+1, s2.getId()),
				() -> assertEquals(s1.getId()+2, s3.getId())
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
	
	@Test
	void testIfNoSlotThenStationFull() throws TypeStationException, StationSamePositionException {
		Point p1 = new Point(3, 10);
		Station s1 = sf.createStation("Standard", p1);
		assertTrue(s1.isStationFull());
	}
	
	void testIfNoSlotThenSlotAvailableNull() throws TypeStationException, StationSamePositionException {
		Point p1 = new Point(3, 10);
		Station s1 = sf.createStation("Standard", p1);
		assertEquals(null, s1.availableSlot());
	}
	

}
