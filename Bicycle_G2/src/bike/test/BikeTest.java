package bike.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bike.*;

class BikeTest {

	/**
	 * Tests if the IDs of two bikes are different
	 */
	@Test
	void testGetId() {
		Bike electricBike = new ElectricBike();
		Bike mechanicBike = new MechanicBike();
		assertTrue(electricBike.getId() != mechanicBike.getId());
	}

}
