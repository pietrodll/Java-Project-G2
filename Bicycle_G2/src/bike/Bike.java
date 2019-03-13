package bike;

import card.Card;

/**
 * A class representing bikes. Each {@code Bike} has an unique id, regardless of the type.
 * @author Pietro Dellino
 */
public abstract class Bike {
	
	private int id;
	private boolean isRented;
	private int stationId;
	private int slotId;
	
	public Bike() {
		BikeIdGenerator idGenerator = BikeIdGenerator.getInstance();
		this.id = idGenerator.getNextId();
	}

	public int getId() {
		return id;
	}
	
	public abstract float ridePrice(Card card, int rideTime);

}
