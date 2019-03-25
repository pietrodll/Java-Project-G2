package tools;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

public class PointTest {
	
	@Test
	void testEqualPosition() {
		assertAll(
				() -> {
					Point p1 = new Point(2,9);
					Point p2 = new Point (2,8);
					assertTrue(p2.equals(p1));
				},
				() -> {
					Point p1 = new Point(0,8);
					Point p2 = new Point (2,8);
					assertFalse(p2.equals(p1));
					
				},
				() -> {
					Point p1 = new Point(0.5,8);
					Point p2 = new Point (0.5,8);
					assertTrue(p2.equals(p1));
				
				},
				() -> {
					Point p1 = new Point(6,8);
					Point p2 = new Point (6.0, 8);
					assertTrue(p2.equals(p1));
						
				}
		);	
	}

	
	@Test 
	void testDistance() {
		assertAll(
				() -> {
					Point p1 = new Point(3,9);
					Point p2 = new Point (2,8);
				}
		);
	}

}
