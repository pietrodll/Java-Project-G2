package ui.clui.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import bike.ElectricBike;
import bike.MechanicBike;
import card.Card;
import card.VmaxCard;
import station.StandardStation;
import station.Station;
import tools.NegativeTimeException;
import tools.Point;
import ui.clui.CommandLineDisplay;
import user.User;

class CommandLineDisplayTest {
	
	static CommandLineDisplay cld;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		cld = new CommandLineDisplay();
	}

	@Test
	void testDisplayStation() throws NegativeTimeException {
		Station s = new StandardStation(new Point(1.055645,10.54896));
		s.addSlot(3);
		LocalDateTime time = LocalDateTime.of(2019, 01, 01, 00, 00);
		s.getParkingSlots().get(1).setBike(new ElectricBike(), time);
		s.getParkingSlots().get(2).setBike(new MechanicBike(), time);
		String disp = cld.display(s);
		String expected = "Station: id:0\n"
			+ "\tPosition: x=1.056 y=10.549\n"
			+ "Slots: 3\n"
			+ "\tid:0\tOccupied:false\n"
			+ "\tid:1\tOccupied:true\tBike id:0\tType: Electric\n"
			+ "\tid:2\tOccupied:true\tBike id:1\tType: Mechanic\n";
		assertEquals(expected, disp);
	}

	@Test
	void testDisplayCard() {
		String expected = "Card: id:0\n"
			+ "\tOwner: Pietro\n"
			+ "\tType: Vmax\n"
			+ "\tCredit: 10 minutes\n";
		Card card = new VmaxCard(new User("Pietro"));
		card.addCredit(10);
		String disp = cld.display(card);
		assertEquals(expected, disp);
	}

	@Test
	void testDisplayUser() {
		fail("Not yet implemented");
	}

	@Test
	void testDisplay() {
		fail("Not yet implemented");
	}

}
