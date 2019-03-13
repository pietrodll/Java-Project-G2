package bike;

import card.Card;

public class ElectricBike extends Bike {

	/**
	 * This methods computes the price of a ride according to the user card.
	 * @see bike.Bike#ridePrice(card.Card, int)
	 */
	@Override
	public float ridePrice(Card card, int rideTime) {
		return card.computeRidePrice(this, rideTime);
	}
	
	

}
