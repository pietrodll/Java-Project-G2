package card;

import bike.Bike;
import bike.ElectricBike;
import bike.MechanicBike;
import user.User;

public class VmaxCard extends Card {

	public VmaxCard(User user) {
		super(user);
	}

	@Override
	public float computeRidePrice(ElectricBike bike, int rideTime) {
		return computeVmax(bike, rideTime);
	}

	@Override
	public float computeRidePrice(MechanicBike bike, int rideTime) {
		return computeVmax(bike, rideTime);
	}
	
	private float computeVmax(Bike bike, int rideTime) {
		float cost = 0;
		if (rideTime > 60) {
			int exceedingTime = rideTime - 60;
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

	@Override
	public String getTypeString() {
		return "Vmax";
	}

}
