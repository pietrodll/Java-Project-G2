package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import card.Card;
import card.CardFactory;
import card.VlibreCard;
import ride.Network;
import station.Station;
import tools.Point;

class NetworkManagerTest {
	
	/*
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	*/
	
	@Test
	void testNetworkManager() {
		NetworkManager nm = new NetworkManager();
		assertAll(
			() -> assertNotNull(nm),
			() -> assertNotNull(nm.getNetworks())
		);
	}

	@Test
	void testSetupNetworkString() {
		NetworkManager nm = new NetworkManager();
		assertAll(
			() -> assertNotNull(nm.setupNetwork("Network"), "Creation of the network"),
			() -> {
				Network net = nm.setupNetwork("VLib");
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
		NetworkManager nm = new NetworkManager();
		assertAll(
			() -> assertNotNull(nm.setupNetwork("Network"), "Creation of the network"),
			() -> {
				Network net = nm.setupNetwork("VLib", 20, 11, 7.5, 150);
				ArrayList<Station> stations = net.getStations();
				assertAll("Properties of the network",
					() -> assertEquals(20, net.getStations().size()),
					() -> {
						for (int i = 0; i < stations.size(); i++) {
							assertEquals(11, stations.get(i).getParkingSlots().size(), "Number of slots of station number " + i);
						}
					},
					() -> {
						for (int i = 0; i < stations.size(); i++) {
							Station s = stations.get(i);
							String is = ((Integer) i).toString();
							assertAll(
								() -> assertTrue(0 <= s.getP().getX() && s.getP().getX() < 7.5, "X position of station number " + is),
								() -> assertTrue(0 <= s.getP().getY() && s.getP().getY() < 7.5, "Y position of station number " + is)
							);
						}
					}
				);
			},
			() -> assertThrows(ExistingNameException.class, () -> { Network net1 = nm.setupNetwork("VLib"); }),
			() -> assertThrows(ExistingNameException.class, () -> { Network net1 = nm.setupNetwork("VLib", 12, 15, 7.1, 3); })
		);
	}

	@Test
	void testAddUser() {
		NetworkManager nm = new NetworkManager();
		assertAll(
			() -> {
				Network net = nm.setupNetwork("VLib");
				nm.addUser("Pietro", CardFactory.VLIBRE, "Vlib");
				ArrayList<Card> cards = net.getCards();
				assertAll("Check user added",
					() -> assertEquals(1, cards.size(), "Check the number of cards on the network"),
					() -> assertEquals("Pietro", cards.get(0).getUser().getUserName(), "Check the name of the user"),
					() -> assertTrue(cards.get(0) instanceof VlibreCard, "Check the type of card")
				);
			},
			() -> {
				
			},
			() -> assertThrows(InexistingNetworkNameException.class, () -> { nm.addUser("Chloe", CardFactory.VMAX, "Network"); })
		);
	}

	@Test
	void testSetStationOffline() {
		fail("Not yet implemented");
	}

	@Test
	void testSetStationOnline() {
		fail("Not yet implemented");
	}
	
	@Test
	void testGetPointDistribution() {
		NetworkManager nm = new NetworkManager();
		Point[] points = nm.getPointDistribution(10, 4);
		assertAll(
			() -> assertNotNull(points),
			() -> {
				for (int i = 0; i < 10; i++) {
					assertTrue(points[i].getX() < 4 && points[i].getX() >= 0, "X position of point number " + i);
					assertTrue(points[i].getY() < 4 && points[i].getY() >= 0, "Y position of point number " + i);
				}
			},
			() -> {
				for (int i = 0; i < 9; i++) {
					for (int j = i + 1; j < 10; j++) {
						assertNotEquals(points[i], points[j]);
					}
				}
			}
		);
	}

}
