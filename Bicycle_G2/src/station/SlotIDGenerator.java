package station;

import java.io.Serializable;

public class SlotIDGenerator implements Serializable {

		
	private static SlotIDGenerator instance = null;
	private int num;
		
	private SlotIDGenerator() {}
		
	public static synchronized SlotIDGenerator getInstance() {
		if (instance == null) {
			instance = new SlotIDGenerator();
		}
		return instance;
		
	}
		
	public synchronized int getSlotID(Station s) {
		int numSlots = s.getNumberSlots();
		return 1000*s.getId() + numSlots + 1;
	}


}
 
