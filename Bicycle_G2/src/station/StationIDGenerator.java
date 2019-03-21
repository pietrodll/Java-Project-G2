package station;

import java.io.Serializable;

public class StationIDGenerator implements Serializable {

		
	private static StationIDGenerator instance = null;
	private int num;
		
	private StationIDGenerator() {}
		
	public static synchronized StationIDGenerator getInstance() {
		if (instance == null) {
			instance = new StationIDGenerator();
		}
		return instance;
		
	}
		
	public int getNextStationID() {
		return num ++;
	}


}
