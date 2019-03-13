package bike;

/**
 * A class representing bikes. Each {@code Bike} has an unique id, regardless of the type.
 * @author Pietro Dellino
 *
 */
public abstract class Bike {
	
	private int id;
	private boolean isRented;
	private int stationId;
	private int slotId;
	
	public Bike() {
		this.id = 0;
	}

	/**
	 * @return The ID
	 */
	public int getId() {
		return id;
	}

}
