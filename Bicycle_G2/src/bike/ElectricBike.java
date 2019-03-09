package bike;

public class ElectricBike extends Bike {
	
	private static final double COST = 2;

	@Override
	public double getCost() {
		return COST;
	}

}
