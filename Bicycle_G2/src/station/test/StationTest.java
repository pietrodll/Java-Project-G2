package station.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bike.Bike;
import bike.ElectricBike;
import bike.MechanicBike;
import card.*;
import ride.Network;
import station.*;
import tools.Point;
import user.User;

public class StationTest {
	
	private Network net;
	private StationFactory sf;
	private User u1;
	private User u2;
	
	@BeforeEach
	void setUp() {
		net = new Network();
		sf = new StationFactory(net);
		u1 = new User ("DellinoPietro");
		u2 = new User ("GentgenChloé");
	}
	
	@Test
	void testDifferentIdStation() throws TypeStationException, StationSamePositionException {
		Point p1 = new Point (1,3);
		Point p2 = new Point (2,3);
		Point p3 = new Point (4,9);
		
		Station s1 = sf.createStation("Standard", p1);
		Station s2 = sf.createStation("Standard", p2);
		Station s3 = sf.createStation("Plus", p3);
		assertAll(
				() -> assertEquals(s1.getId()+1, s2.getId()),
				() -> assertEquals(s1.getId()+2, s3.getId())
		);				
	}
				
	@Test 
	void testSamePositionStation() throws TypeStationException, StationSamePositionException {
		Point p1 = new Point(3, 10);
		Station s1 = sf.createStation("Standard", p1);
		assertThrows(StationSamePositionException.class,
				() -> {
					Station s2 = sf.createStation("Standard", p1);
				}
		);
		
	}
	
	@Test
	void testIfNoSlotThenStationFull() throws TypeStationException, StationSamePositionException {
		Point p1 = new Point(3, 10);
		Station s1 = sf.createStation("Standard", p1);
		assertTrue(s1.isStationFull());
	}
	
	@Test
	void testIfNoSlotThenSlotAvailableNull() throws TypeStationException, StationSamePositionException {
		Point p1 = new Point(3, 10);
		Station s1 = sf.createStation("Standard", p1);
		assertEquals(null, s1.availableSlot());
	}
	
	@Test
	void testCardIdentification() throws TypeStationException, StationSamePositionException {
		Point p1 = new Point(3, 10);
		Station s1 = sf.createStation("Standard", p1);
		Card c1 = CardFactory.createCard(1,u1);
		Card c2 = CardFactory.createCard(2, u2);
		assertEquals(u1, s1.identifyUser(c1), "ll");
		assertEquals(u2, s1.identifyUser(c2), "mm");
		
	}	
	
	@Test
	void testSlotandState() throws TypeStationException, StationSamePositionException {
		Point p1 = new Point(3, 10);
		Point p2 = new Point (60, 4);
		Station s1 = sf.createStation("Standard", p1);
		Station s2 = sf.createStation("Standard", p2);
		Bike b1 = new ElectricBike();
		Bike b2 = new MechanicBike();
		Bike b3 = new MechanicBike();
		Bike b4 = new ElectricBike();
		Bike b5 = new ElectricBike();
		Bike b6 = new MechanicBike();
		Bike b7 = new MechanicBike();
		Bike b8 = new MechanicBike();
		s1.addSlot();
		s2.addSlot();
		s1.addSlot();
		s1.addSlot();
		s2.addSlot();
		assertAll("Creating Stations and Slots",
				() -> {
					assertAll("Number of Slots in Stations",
							() -> assertEquals(3, s1.getParkingSlots().size(), "Number of Slots in S1"),
							() -> assertEquals(2, s2.getParkingSlots().size(), "Number of Slots in S2")
				
					);	
		
				},
				() -> {
					int idS1 = s1.getId();
					int idS2 = s2.getId();
					Slot slot11 = s1.getParkingSlots().get(0);
					Slot slot12 = s1.getParkingSlots().get(1);
					Slot slot13 = s1.getParkingSlots().get(2);
					Slot slot21 = s2.getParkingSlots().get(0);
					LocalDateTime t1 = LocalDateTime.of(2019,03,26,14,56);
					LocalDateTime t2 = LocalDateTime.of(2019,03,26,15,30);
					LocalDateTime t3 = LocalDateTime.of(2019,03,26,15,57);
					LocalDateTime t4 = LocalDateTime.of(2019,03,26,16,32);
					LocalDateTime t5 = LocalDateTime.of(2019,03,26,16,45);
					slot11.setBike(b1,t1);
					slot11.setBike(null, t2);
					slot11.setOnline(false, t3);
					slot11.setOnline(true, t4);
					slot11.setBike(b2, t5);
					slot12.setOnline(false, t1);
					slot12.setOnline(true, t3);
					slot13.setOnline(false,t1);
					slot13.setBike(b3, t2);
					slot13.setOnline(true, t3);
					slot13.setBike(null, t4);
					slot13.setOnline(false, t5);
					
					SlotState slotstate111 = slot11.getSlotHistory().get(0);
					SlotState slotstate112 = slot11.getSlotHistory().get(1);
					SlotState slotstate113 = slot11.getSlotHistory().get(2);
					SlotState slotstate114 = slot11.getSlotHistory().get(3);
					SlotState slotstate115 = slot11.getSlotHistory().get(4);
					
					assertAll("Station and Slots",
					() -> {
						assertAll("ID Station and Slots",
								() -> assertNotEquals(idS1, idS2, "Different ID for station"),
								() -> assertEquals(idS1+1, idS2, "Succesive ID for succesive stations"),
								() -> assertEquals(idS1*1000, slot11.getId(), "Correct ID for First Slot of S1" ),
								() -> assertEquals(idS1*1000 + 1, slot12.getId(), "Correct ID for Second Slot of S2"),
								() -> assertEquals(idS2*1000, slot21.getId(), "Correct ID for First Slot of S2")
						);
					},
					() -> {
						assertAll("Correct Slot State for Slot 1 of S1 between t1 and t6",
								() -> assertEquals(t1, slotstate111.getStartTime(), "1"),
								() -> assertEquals(t2, slotstate111.getEndTime(), "2"),
								() -> assertEquals(true, slotstate111.isOnline(), "3"),
								() -> assertEquals(b1, slotstate111.getBike(), "4"),
								() -> assertEquals(t2, slotstate112.getStartTime(), "5"),
								() -> assertEquals(t3, slotstate112.getEndTime(), "6"),
								() -> assertEquals(true, slotstate112.isOnline(), "7"),
								() -> assertEquals(null, slotstate112.getBike(), "8"),
								() -> assertEquals(t3, slotstate113.getStartTime(), "9"),
								() -> assertEquals(t4, slotstate113.getEndTime(), "10"),
								() -> assertEquals(false, slotstate113.isOnline(), "11"),
								() -> assertEquals(null, slotstate113.getBike(), "12"),
								() -> assertEquals(t4, slotstate114.getStartTime(), "13"),
								() -> assertEquals(t5, slotstate114.getEndTime(), "14"),
								() -> assertEquals(true, slotstate114.isOnline(), "15"),
								() -> assertEquals(null, slotstate114.getBike(), "16"),
								() -> assertEquals(t5, slotstate115.getStartTime(), "17"),
								() -> assertEquals(true, slotstate115.isOnline(), "18"),
								() -> assertEquals(b2, slotstate115.getBike(), "19"),
								() -> assertEquals(null, slotstate115.getEndTime(), "20")
						);
					},
					() -> {
						assertAll("Stations methods isStationFull/hasBikeAvailable",
								() -> assertFalse(s1.isStationFull()),
								() -> assertEquals(slot11, s1.hasBikeAvailable()),
								() -> assertNull(s1.hasElectricBikeAvailable()),
								() -> assertEquals(slot11, s1.hasMechanicBikeAvailable()),
								() -> assertFalse(b2 instanceof ElectricBike),
								() -> assertTrue(b2 instanceof Bike), 
								() -> assertTrue(b2 instanceof MechanicBike),
								() -> assertEquals(b2, slot11.getBike()),
								() -> assertEquals(null, slot12.getBike()), 
								() -> assertEquals(null, slot13.getBike())
								
						);
					},
					() -> {
						assertAll("Slot method isOccupied",
								() -> assertEquals(true, slot11.getisOccupied(), "Slot 1 is Online and has a Bike"),
								() -> assertEquals(false, slot12.getisOccupied(), "Slot 2 is Online without a Bike"),
								() -> assertEquals(true, slot13.getisOccupied(), "Slot 3 is Offline and without a Bike")


						);
					},
					() -> {
						
						LocalDateTime t10 = LocalDateTime.of(2019,03,26,16,30);
						LocalDateTime t11 = LocalDateTime.of(2019,03,25,16,30);
						LocalDateTime t1t2 = LocalDateTime.of(2019, 03, 26, 15, 00);
						LocalDateTime t4t5 = LocalDateTime.of(2019,  03, 26, 16, 40);
						assertAll("Computing Occupation Rate and all associated methods",
								() -> {
									assertAll("IndexSlotState method Slot 11",
											() -> {
												assertThrows(NoSlotStateAtDateException.class, 
														() -> {
															Object i = slot11.indexSlotState(t11);	
														},"If date is before creation of a slot, then throws Exception"
													);
											},
											() -> assertEquals(2, slot11.indexSlotState(t10), "If date is during a Slot State"),
											()-> assertEquals(2, slot11.indexSlotState(t3), "If date is at a change of Slot State"),
											
											() -> assertTrue(t10.isAfter(t1)),
											() -> assertTrue(t10.isAfter(t2)),
											() -> assertTrue(t10.isAfter(t3)),
											() -> assertTrue(t10.isBefore(t4)),
											() -> assertTrue(t10.isBefore(t5))
										);
								},
								() -> {
									assertAll("ComputeOccupationsTime for Slot 11",
											() -> assertEquals (69,slot11.computeOccupationTime(t1t2, t4t5),"If start time and end time are during finished Slot States"),
											() -> assertEquals(35, slot11.computeOccupationTime(t2,t4t5), "If start time is a junction time and end time is during Slot State"),
											() -> assertEquals(35, slot11.computeOccupationTime(t3, t4t5)),
											() -> assertEquals(35, slot11.computeOccupationTime(t3, t4), "If start time and end time are the start and the end of the same Slot State"),
											() -> assertEquals(69, slot11.computeOccupationTime(t1, t4t5))
									);
								},
								() -> {
									assertAll("ComputeOccupationsTime for Slot 13",
											() -> assertEquals (69,slot13.computeOccupationTime(t1t2, t4t5),"If start time and end time are during finished Slot States"),
											() -> assertEquals(35, slot11.computeOccupationTime(t2,t4t5), "If start time is a junction time and end time is during Slot State"),
											() -> assertEquals(35, slot11.computeOccupationTime(t3, t4t5)),
											() -> assertEquals(35, slot11.computeOccupationTime(t3, t4), "If start time and end time are the start and the end of the same Slot State"),
											() -> assertEquals(69, slot11.computeOccupationTime(t1, t4t5))
									);
								}
							);
					}
				);
		
				}
		);
	
	}

}
