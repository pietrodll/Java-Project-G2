package ride.path.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bike.BikeFactory;
import bike.ElectricBike;
import bike.MechanicBike;
import ride.Network;
import ride.path.MinimalWalkingStrategy;
import station.Station;
import station.StationFactory;
import tools.Point;

class FastestPathStrategyTest {
	
	static Network net;
	static LocalDateTime changeTime = LocalDateTime.of(2019, 1, 1, 0, 0);
	static MinimalWalkingStrategy mws;
	static Point start;
	static Point destination;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		start = new Point(0,0);
		destination = new Point(0,100);
	}

	@BeforeEach
	void setUp() throws Exception {
		net = new Network();
		mws = new MinimalWalkingStrategy(net);
		StationFactory sf = new StationFactory(net);
		for (int i = 1; i <= 9; i++) {
			sf.createStation("Standard", new Point(0, 10*i));
			net.getStations().get(i-1).addSlot();
		}
	}

	@Test
	void testFindPathPointPointInt() {
		assertAll("ElectriBike",
			() -> {
				net.getStations().get(1).availableSlot().setBike(new ElectricBike(), changeTime);
				net.getStations().get(0).availableSlot().setBike(new MechanicBike(), changeTime);
				Station[] stations = mws.findPath(start, destination, BikeFactory.ELECTRIC);
				Station[] expectedStations = {net.getStations().get(1), net.getStations().get(8)};
				assertArrayEquals(expectedStations, stations, "Station 1 is closer than station 2 but has no ElectricBike");
			},
			() -> {
				net.getStations().get(0).getParkingSlots().get(0).setBike(new ElectricBike(), changeTime);
				Station[] stations = mws.findPath(start, destination, BikeFactory.ELECTRIC);
				Station[] expectedStations = {net.getStations().get(0), net.getStations().get(8)};
				assertArrayEquals(expectedStations, stations, "Station 1 is the closest and has ElectricBike");
			},
			() -> {
				net.getStations().get(4).availableSlot().setBike(new MechanicBike(), changeTime);
				Station[] stations = mws.findPath(start, destination, BikeFactory.ELECTRIC);
				Station[] expectedStations = {net.getStations().get(0), net.getStations().get(7)};
				assertArrayEquals(expectedStations, stations, "Station 9 is the closer to the destination but has no free slot");
			}
		);
		assertAll("MechanicBike",
			() -> {
				net.getStations().get(1).availableSlot().setBike(new MechanicBike(), changeTime);
				net.getStations().get(0).availableSlot().setBike(new ElectricBike(), changeTime);
				Station[] stations = mws.findPath(start, destination, BikeFactory.MECHANIC);
				Station[] expectedStations = {net.getStations().get(1), net.getStations().get(8)};
				assertArrayEquals(expectedStations, stations, "Station 1 is closer than station 2 but has no MechanicBike");
			},
			() -> {
				net.getStations().get(0).getParkingSlots().get(0).setBike(new MechanicBike(), changeTime);
				Station[] stations = mws.findPath(start, destination, BikeFactory.MECHANIC);
				Station[] expectedStations = {net.getStations().get(0), net.getStations().get(8)};
				assertArrayEquals(expectedStations, stations, "Station 1 is the closest and has MechanicBike");
			},
			() -> {
				net.getStations().get(4).availableSlot().setBike(new ElectricBike(), changeTime);
				Station[] stations = mws.findPath(start, destination, BikeFactory.MECHANIC);
				Station[] expectedStations = {net.getStations().get(0), net.getStations().get(7)};
				assertArrayEquals(expectedStations, stations, "Station 5 is the closer to the destination but has no free slot");
			}
		);
	}

	@Test
	void testFindPathPointPoint() {
		fail("Not yet implemented");
	}

	@Test
	void testFindEndStation() {
		fail("Not yet implemented");
	}

}
