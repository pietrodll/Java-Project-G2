/**
 * 
 */
package ride.path.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ride.path.DistanceBasicComparator;
import station.Station;
import station.StationFactory;
import tools.Point;

/**
 * The test class for {@link StationComparator}. It contains the tests for each subclass.
 *
 */
class StationComparatorTest {
	
	private static Point point;
	private static StationFactory fact;

	/**
	 * This method creates a network which is used in each test.
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUp() throws Exception {
		point = new Point(0, 0);
		fact = new StationFactory();
	}

	/**
	 * Test method for {@link ride.path.StationComparator#getDistanceDiff(station.Station, station.Station)}.
	 */
	@Test
	void testGetDistanceDiff() {
		DistanceBasicComparator dbc = new DistanceBasicComparator(point);
		assertAll("Many distance differences tests",
			() -> {
				Station s1 = fact.createStation("Standard", new Point(0, 10));
				Station s2 = fact.createStation("Standard", new Point(0, 20));
				assertEquals(10, dbc.getDistanceDiff(s2, s1));
			},
			() -> {
				Station s1 = fact.createStation("Standard", new Point(5, 0));
				Station s2 = fact.createStation("Standard", new Point(10, 0));
				assertEquals(5, dbc.getDistanceDiff(s2, s1));
			},
			() -> {
				Station s1 = fact.createStation("Standard", new Point(10, 10));
				Station s2 = fact.createStation("Standard", new Point(20, 20));
				assertEquals(10*Math.sqrt(2), dbc.getDistanceDiff(s2, s1));
			}
		);
	}
	
	@Test
	void testDistanceBasicComparator() {
		DistanceBasicComparator dbc = new DistanceBasicComparator(point);
		assertAll(
			() -> {
				Station s1 = fact.createStation("Standard", new Point(0, 10));
				Station s2 = fact.createStation("Standard", new Point(0, 20));
				assertTrue(dbc.compare(s1, s2) < 0);
			},
			() -> {
				Station s1 = fact.createStation("Standard", new Point(0, 10));
				Station s2 = fact.createStation("Standard", new Point(0, 10));
				assertTrue(dbc.compare(s1, s2) == 0);
			},
			() -> {
				Station s1 = fact.createStation("Standard", new Point(0, 30));
				Station s2 = fact.createStation("Standard", new Point(0, 20));
				assertTrue(dbc.compare(s1, s2) > 0);
			}
		);
	}

}
