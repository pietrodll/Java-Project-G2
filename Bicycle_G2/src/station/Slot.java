package station;

/**
 * A class representing parking slots
 *
 */
public class Slot {

	private Station s;
	private double id;
	private boolean isOnline;
	private boolean hasABike;
	/**
	private Bike bikeParked;
	**/
	// est ce que tu penses que l'on dit quel bike est garé où ? Genre un parking slot sait quel bike est chez lui ?
	
	public Slot(Station s) {
		super();
		this.s = s;
	}
	
	
}
