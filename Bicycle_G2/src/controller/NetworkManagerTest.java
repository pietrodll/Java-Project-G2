package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class NetworkManagerTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testNetworkManager() {
		NetworkManager nm = new NetworkManager();
		assertNotNull(nm.getNetworks());
	}

	@Test
	void testSetupNetworkString() {
		
		fail("Not yet implemented");
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
