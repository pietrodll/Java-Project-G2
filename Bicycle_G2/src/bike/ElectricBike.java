package bike;

import card.CardVisitor;

public class ElectricBike extends Bike {

	/**
	 * This methods computes the price of a ride according to the user card.
	 * @see bike.Bike#ridePrice(card.Card, int)
	 */
	@Override
	public float ridePrice(CardVisitor card, int rideTime) {
		return card.computeRidePrice(this, rideTime);
	}
	
	

}
