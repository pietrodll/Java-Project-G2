package card;

import bike.ElectricBike;
import bike.MechanicBike;

/**
 * This class represents VLibre Cards.
 * Its methods allow to calculate the price of a ride when the users has this type of card.
 * @author Pietro Dellino
 */
public class VlibreCard extends Card {

	/**
	 * This method calculates the price of a ride for a user having a {@code VlibreCard} when he uses an {@code ElectricBike}.
	 * @param bike The bike used for the ride.
	 * @param rideTime The duration of the ride, in minutes.
	 */
	@Override
	public float computeRidePrice(ElectricBike bike, int rideTime) {
		float cost = 0;
		int exceedingTime = rideTime - 60;
		if (exceedingTime > 0) {
			int credit = this.getTimeCredit();
			if (credit >= exceedingTime) {
				try {
					this.useCredit(exceedingTime);
				} catch (InsufficientCreditException e) {
					e.printStackTrace();
				} finally {
					cost = 1;
				}
			} else {
				try {
					this.useCredit(credit);
				} catch (InsufficientCreditException e) {
					e.printStackTrace();
				} finally {
					cost = 1 + 2*(exceedingTime - credit)/60;
				}
			}
		} else {
			cost = rideTime/60;
		}
		return cost;
	}

	/**
	 * This method calculates the price of a ride for a user having a {@code VlibreCard} when he uses a {@code MechanicBike}.
	 * @param bike The bike used for the ride.
	 * @param rideTime The duration of the ride, in minutes.
	 */
	@Override
	public float computeRidePrice(MechanicBike bike, int rideTime) {
		float cost = 0;
		int exceedingTime = rideTime - 60;
		if (exceedingTime > 0) {
			int credit = this.getTimeCredit();
			if (credit >= exceedingTime) {
				try {
					this.useCredit(exceedingTime);
				} catch (InsufficientCreditException e) {
					e.printStackTrace();
					this.setTimeCredit(0);
				}
			} else {
				try {
					this.useCredit(credit);
				} catch (InsufficientCreditException e) {
					e.printStackTrace();
					this.setTimeCredit(0);
				} finally {
					cost = (exceedingTime - credit)/60;
				}
			}
		}
		return cost;
	}

}
