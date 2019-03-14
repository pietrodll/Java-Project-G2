package card.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bike.ElectricBike;
import bike.MechanicBike;
import card.InsufficientCreditException;
import card.VlibreCard;
import card.VmaxCard;

class CardTest {

	@Test
	void testComputeRidePriceElectricBikeInt() {
		VlibreCard VLibre = new VlibreCard();
		VmaxCard VMax = new VmaxCard();
		ElectricBike bike = new ElectricBike();
		assertAll("Ride prices for electric bikes",
			() -> {
				assertAll("With no time credit",
					() -> assertEquals(0, VMax.computeRidePrice(bike, 15)),
					() -> assertEquals(3, VLibre.computeRidePrice(bike, 120)),
					() -> assertEquals(1, VMax.computeRidePrice(bike, 120)),
					() -> assertEquals(0.25, VLibre.computeRidePrice(bike, 15))
				);
			},
			() -> {
				VLibre.setTimeCredit(20);
				VMax.setTimeCredit(20);
				assertAll("With time credit",
					() -> assertEquals(1.5, VLibre.computeRidePrice(bike, 95)),
					() -> assertEquals(0, VMax.computeRidePrice(bike, 75)),
					() -> assertEquals(0, VLibre.getTimeCredit()),
					() -> assertEquals(5, VMax.getTimeCredit())
				);
			}
		);
	}

	@Test
	void testComputeRidePriceMechanicBikeInt() {
		VlibreCard VLibre = new VlibreCard();
		VmaxCard VMax = new VmaxCard();
		MechanicBike bike = new MechanicBike();
		assertAll("Ride prices for electric bikes",
			() -> {
				assertAll("With no time credit",
					() -> assertEquals(0, VMax.computeRidePrice(bike, 15)),
					() -> assertEquals(1, VLibre.computeRidePrice(bike, 120)),
					() -> assertEquals(1, VMax.computeRidePrice(bike, 120)),
					() -> assertEquals(0, VLibre.computeRidePrice(bike, 15))
				);
			},
			() -> {
				VLibre.setTimeCredit(20);
				VMax.setTimeCredit(20);
				assertAll("With 20 minutes time credit",
					() -> assertEquals(0.25, VLibre.computeRidePrice(bike, 95), "95 minutes"),
					() -> assertEquals(0, VMax.computeRidePrice(bike, 75), "75 minutes"),
					() -> assertEquals(0, VLibre.getTimeCredit(), "Vlibre time credit"),
					() -> assertEquals(5, VMax.getTimeCredit(), "Vmax time credit")
				);
			}
		);
	}

	@Test
	void testSetTimeCredit() {
		VlibreCard Vlibre = new VlibreCard();
		Vlibre.setTimeCredit(10);
		assertEquals(10, Vlibre.getTimeCredit());
	}

	@Test
	void testAddCredit() {
		VlibreCard vlibre = new VlibreCard();
		VmaxCard vmax = new VmaxCard();
		vlibre.addCredit(10);
		vmax.addCredit(10);
		assertAll("Adding Time Credit",
			() -> assertEquals(10, vlibre.getTimeCredit()),
			() -> assertEquals(10, vmax.getTimeCredit())
		);
	}

	@Test
	void testUseCredit() {
		VlibreCard vlibre = new VlibreCard();
		VmaxCard vmax = new VmaxCard();
		vlibre.addCredit(10);
		vmax.addCredit(10);
		assertAll("Using time credit",
			() -> {
				vlibre.useCredit(5);
				vmax.useCredit(5);
				assertAll("When there is enough credit",
					() -> assertEquals(5, vlibre.getTimeCredit()),
					() -> assertEquals(5, vmax.getTimeCredit())
				);
			},
			() -> {
				assertAll("When the credit is insufficient",
					() -> assertThrows(InsufficientCreditException.class, () -> { vlibre.useCredit(20); }),
					() -> assertThrows(InsufficientCreditException.class, () -> { vmax.useCredit(20); })
				);
			}
		);
	}

}
