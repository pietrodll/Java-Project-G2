package card;

import bike.ElectricBike;
import bike.MechanicBike;

/**
 * This abstract class is the basis to create user cards. Each {@code Card} has a unique {@code id} (regardless of the type of the card) and a {@code timeCredit}.
 * @author Pietro Dellino
 * @see CardIdGenerator
 * @see VlibreCard
 * @see VmaxCard
 */
public abstract class Card implements CardVisitor {
	
	private int id;
	private int timeCredit;
	
	public Card() {
		CardIdGenerator idGenerator = CardIdGenerator.getInstance();
		this.id = idGenerator.getNextId();
	}

	public int getId() {
		return id;
	}

	public int getTimeCredit() {
		return timeCredit;
	}

	/**
	 * @param timeCredit the timeCredit to set
	 */
	public void setTimeCredit(int timeCredit) {
		if (timeCredit >= 0) this.timeCredit = timeCredit;
	}
	
	/**
	 * @param newCredit the credit to add to the previous timeCredit
	 */
	public void addCredit(int newCredit) {
		this.timeCredit += newCredit;
	}
	
	/**
	 * If the {@code timeCredit} is high enough, it removes {@code credit} from it. If not, it raises a {@code InsufficientCreditException}.
	 * @param credit the credit to use.
	 */
	public void useCredit(int credit) throws InsufficientCreditException {
		if (this.timeCredit >= credit) {
			this.timeCredit -= credit;
		} else {
			throw new InsufficientCreditException(this.timeCredit, credit);
		}
	}

	@Override
	public abstract float computeRidePrice(ElectricBike bike, int rideTime);

	@Override
	public abstract float computeRidePrice(MechanicBike bike, int rideTime);

}
