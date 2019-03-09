package card;

import bike.MechanicBike;
import bike.ElectricBike;

public interface CardVisitor {
	
	public float computeRidePrice(ElectricBike bike, int rideTime);
	
	public float computeRidePrice(MechanicBike bike, int rideTime);

}
