package velib.core;

public class MechanicBike extends Bike {
	
	private static final double COST = 1;

	@Override
	public double getCost() {
		return COST;
	}

}
