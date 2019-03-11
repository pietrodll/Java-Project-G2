package bike.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bike.*;

class BikeTest {

	/**
	 * Tests if there is no error when each type of bike is created.
	 */
	@Test
	void testBike() {
		Bike electricBike = new ElectricBike();
		Bike mechanicBike = new MechanicBike();
	}

	@Test
	void testGetId() {
		Bike electricBike = new ElectricBike();
		Bike mechanicBike = new MechanicBike();
		System.out.println("testGetId");
		System.out.println("electricBike id : " + electricBike.getId());
		System.out.println("mechanicBike id : " + mechanicBike.getId());
		assertTrue(electricBike.getId() != mechanicBike.getId());
	}

	@Test
	void testGetNumBike() {
		for (int i = 0; i < 10; i++) {
			Bike electricBike = new ElectricBike();
		}
		for (int i = 0; i < 5; i++) {
			Bike mechanicBike = new MechanicBike();
		}
		assertEquals(15, Bike.getNumBike());
	}

	@Test
	void testGetCost() {
		Bike electricBike = new ElectricBike();
		Bike mechanicBike = new MechanicBike();
		assertAll("Cost",
			() -> assertEquals(2, electricBike.getCost()),
			() -> assertEquals(1, mechanicBike.getCost())
		);
	}

}
