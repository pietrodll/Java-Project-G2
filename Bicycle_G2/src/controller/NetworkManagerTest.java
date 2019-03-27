package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ride.Network;
import station.Station;

class NetworkManagerTest {
	
	/*
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	*/
	
	@Test
	void testNetworkManager() {
		NetworkManager nm = new NetworkManager();
		assertNotNull(nm.getNetworks());
	}

	@Test
	void testSetupNetworkString() {
		NetworkManager nm = new NetworkManager();
		assertAll(
			() -> {
				System.out.println("bonjout");
				Network net = nm.setupNetwork("VLib");
				System.out.println("bonjout");
				ArrayList<Station> stations = net.getStations();
				assertAll("Properties of the network",
					() -> assertEquals(10, net.getStations().size()),
					() -> {
						for (int i = 0; i < stations.size(); i++) {
							assertEquals(10, stations.get(i).getParkingSlots().size(), "Number of slots of station number " + i);
						}
					},
					() -> {
						for (int i = 0; i < stations.size(); i++) {
							Station s = stations.get(i);
							String is = ((Integer) i).toString();
							assertAll(
								() -> assertTrue(0 <= s.getP().getX() && s.getP().getX() < 4, "X position of station number " + is),
								() -> assertTrue(0 <= s.getP().getY() && s.getP().getY() < 4, "Y position of station number " + is)
							);
						}
					}
				);
			},
			() -> assertThrows(ExistingNameException.class, () -> { Network net1 = nm.setupNetwork("VLib"); })
		);
	}

	@Test
	void testSetupNetworkStringIntIntDoubleInt() {
		fail("Not yet implemented");
	}

	@Test
	void testAddUser() {
		fail("Not yet implemented");
	}

	@Test
	void testSetStationOffline() {
		fail("Not yet implemented");
	}

	@Test
	void testSetStationOnline() {
		fail("Not yet implemented");
	}

}
