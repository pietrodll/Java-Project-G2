package station.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import station.*;
import tools.Point;

public class StationTest {
	
	private static StationFactory sf;
	
	@BeforeAll
	static void set() {
		sf = new StationFactory();
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
