package station;

import java.io.Serializable;

/**
 * An ID generator for the stations.
 * @author Chlo�
 *
 */
public class StationIDGenerator implements Serializable {

	/**
	 * Generated by Papyrus
	 */
	private static final long serialVersionUID = 8251873607435713002L;
	
	private static StationIDGenerator instance = null;
	private int num;
		
	private StationIDGenerator() {}
	
	/**
	 * This method returns the unique instance of the class (singleton pattern).
	 */	
	public static synchronized StationIDGenerator getInstance() {
		if (instance == null) {
			instance = new StationIDGenerator();
		}
		return instance;
	}
		
	public synchronized int getNextStationID() { return num ++; }

}
