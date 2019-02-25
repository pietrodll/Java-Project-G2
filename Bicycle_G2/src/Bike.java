
public abstract class Bike {
	
	private int id;
	private static int numBike;
	
	public Bike() {
		this.id = Bike.numBike;
		Bike.numBike += 1;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the numBike
	 */
	public static int getNumBike() {
		return numBike;
	}

}
