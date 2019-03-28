package card;

import bike.ElectricBike;
import bike.MechanicBike;
import user.User;

public class CreditCard extends Card {
	
	private final int timeCredit = 0;

	public CreditCard(User user) {
		super(user);
	}

	@Override
	public float computeRidePrice(ElectricBike bike, int rideTime) {
		return (float) 2*rideTime/60;
	}

	@Override
	public float computeRidePrice(MechanicBike bike, int rideTime) {
		return (float) rideTime/60;
	}

	@Override
	public String getTypeString() { return "Credit"; }
	
	@Override
	public int getTimeCredit() { return this.timeCredit; }

}
