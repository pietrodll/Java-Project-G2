/**
 * 
 */
package ride.path.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import station.Station;
import station.StationFactory;
import tools.Point;

/**
 * The test class for {@link StationComparator}. It contains the tests for each subclass.
 *
 */
class StationComparatorTest {
	
	private static Point point;
	private static Station s1;
	private static Station s2;

	/**
	 * This method creates a network which is used in each test.
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	void setUp() throws Exception {
		point = new Point(0, 0);
		StationFactory fact = new StationFactory();
		s1 = fact.createStation("Standard", new Point(0, 10));
		s2 = fact.createStation("Standard", new Point(0, 20));
		System.out.println(s1);
		System.out.println(s2);
	}

	/**
	 * Test method for {@link ride.path.StationComparator#getDistanceDiff(station.Station, station.Station)}.
	 */
	@Test
	void testGetDistanceDiff() {
		
		fail("Not yet implemented");
	}
	
	@Test
	void testDistanceBasicComparator() {
		fail("Not yet implemented");
	}

}
