package station;

import java.io.Serializable;

public class StationIDGenerator implements Serializable {

	private static final long serialVersionUID = 8251873607435713002L;
	
	private static StationIDGenerator instance = null;
	private int num;
		
	private StationIDGenerator() {}
		
	public static synchronized StationIDGenerator getInstance() {
		if (instance == null) {
			instance = new StationIDGenerator();
		}
		return instance;
		
	}
		
	public synchronized int getNextStationID() {
		return num ++;
	}


}
