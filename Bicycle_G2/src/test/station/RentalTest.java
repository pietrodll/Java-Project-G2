package test.station;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import bike.Bike;
import bike.ElectricBike;
import bike.MechanicBike;
import card.*;
import ride.Network;
import station.*;
import tools.Point;
import user.User;

public class RentalTest {
	
	private Network net;
	private StationFactory sf;
	private CardFactory cf;
	private User u1;
	private User u2;
	
	@Test
	void setUp() throws TypeStationException, StationSamePositionException {
		net = new Network();
		sf = new StationFactory(net);
		cf = new CardFactory(net);
		
		u1 = new User ("DellinoPietro");
		u2 = new User ("GentgenChloé");
		
		Point p1 = new Point (1,3);
		Point p2 = new Point (2,3);
		Point p3 = new Point (4,9);
		
		Station s1 = sf.createStation("Standard", p1);
		Station s2 = sf.createStation("Standard", p2);
		Station s3 = sf.createStation("Plus", p3);
		
		Card c1 = cf.createCard(1,u1);
		Card c2 = cf.createCard(2, u2);
		
		Bike b1 = new ElectricBike();
		Bike b2 = new MechanicBike();
		Bike b3 = new MechanicBike();
		Bike b4 = new ElectricBike();
		Bike b5 = new ElectricBike();
		Bike b6 = new MechanicBike();
		Bike b7 = new MechanicBike();
		Bike b8 = new MechanicBike();
		
		s1.addSlot(5);
		s2.addSlot(3);
		s3.addSlot(2);
		
		Slot slot11 = s1.getParkingSlots().get(0);
		Slot slot12 = s1.getParkingSlots().get(1);
		Slot slot13 = s1.getParkingSlots().get(2);
		Slot slot14 = s1.getParkingSlots().get(3);
		Slot slot15 = s1.getParkingSlots().get(4);
		Slot slot21 = s2.getParkingSlots().get(0);
		Slot slot22 = s2.getParkingSlots().get(1);
		Slot slot23 = s2.getParkingSlots().get(2);
		Slot slot31 = s3.getParkingSlots().get(0);
		Slot slot32 = s3.getParkingSlots().get(1);
		
		
		LocalDateTime t1 = LocalDateTime.of(2019,03,26,14,56);
		LocalDateTime t2 = LocalDateTime.of(2019,03,26,15,30);
		LocalDateTime t3 = LocalDateTime.of(2019,03,26,15,57);
		LocalDateTime t4 = LocalDateTime.of(2019,03,26,16,32);
		LocalDateTime t5 = LocalDateTime.of(2019,03,26,16,45);
		LocalDateTime t1t2 = LocalDateTime.of(2019, 03, 26, 15, 00);
		LocalDateTime t4t5 = LocalDateTime.of(2019,  03, 26, 16, 40);
		LocalDateTime t6 = LocalDateTime.of(2019, 03, 26, 16, 50);
		
		assertAll("Scenario 1 of picking up bike",
				() -> {
					slot11.setOnline(false, t1);
					slot12.setBike(b1, t1);
					slot13.setBike(b2, t1);
					slot14.setOnline(false,t1);
					
					slot21.setBike(b3, t1);
					
					s1.pickUpElectricBike(c1, t2); //u1 a pris le vélo b1 de la station s1 à t2
				
					assertAll("Manage to pick up bike, checking ride",
							() -> assertNotEquals(null, u1.getOngoingRide()),
							() -> assertEquals(b1, u1.getOngoingRide().getBike()),
							() -> assertEquals(c1, u1.getOngoingRide().getCard()),
							() -> assertEquals(t2, u1.getOngoingRide().getStartRide()),
							() -> assertEquals(null, u1.getOngoingRide().getEndRide()),
							() -> assertEquals(-1, u1.getOngoingRide().getRideTime())
					);	
					
					assertAll("Manage to pick up bike, checking station and slot",
							() -> assertEquals(null,slot12.getBike()),
							() -> assertEquals(1, s1.getTotalRents()),
							() -> assertEquals(1, s1.getTotalOperations()),
							() -> assertEquals(0, s1.getTotalReturns()),
							() -> assertEquals(2, slot12.getSlotHistory().size()),
							() -> assertEquals(t2, slot12.getSlotHistory().get(0).getEndTime()),
							() -> assertEquals(t2, slot12.getSlotHistory().get(1).getStartTime()),
							() -> assertEquals(null, slot12.getSlotHistory().get(1).getEndTime()),
							() -> assertEquals(slot13, s1.hasBikeAvailable()),
							() -> assertEquals(slot13, s1.hasMechanicBikeAvailable()),
							() -> assertEquals(null, s1.hasElectricBikeAvailable())
							
					);	
					
					s2.pickUpBike(c1, t3); // cela ne fait rien, u1 ne peut pas reprendre un second vélo
					assertAll("Cannot pick up an other bike",
							() -> assertNotEquals(null, slot21.getBike()),
							() -> assertEquals(b3, slot21.getBike()),
							() -> assertEquals(b1, u1.getOngoingRide().getBike()),
							() -> assertEquals(1, slot21.getSlotHistory().size())
							
							
					);
					
					/*
					s2.dropBike(c1,t4);
					
					assertAll ("Manage to drop bike, checking ride and rideHistory",
							() -> assertEquals(null,u1.getOngoingRide())//,
							//() -> assertEquals(1, net.getRideHistory().size()),
							//() -> assertEquals(null, net.getRideHistory().get(0).getEndRide()),
							//() -> assertEquals(b1, net.getRideHistory().get(0).getBike())
							//() -> assertEquals(-1, net.getRideHistory().get(0).getRideTime(),"1")
							
							
							
					);
					
					/*s2.dropBike(c2,t4);
					
					assertAll ("Cannot drop bike Manage to drop bike, checking ride and rideHistory",
						() -> assertEquals(null, u2.getOngoingRide())	
							
							
					);*/
					
					
					
				}
		);
	}
	
	
	

}
