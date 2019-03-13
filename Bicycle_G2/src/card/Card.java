package card;

import bike.ElectricBike;
import bike.MechanicBike;

/**
 * This abstract class is the basis to create user cards. Each {@code Card} has a unique {@code id} (regardless of the type of the card) and a {@code timeCredit}.
 * @author Pietro Dellino
 *
 */
public abstract class Card implements CardVisitor {
	
	private int id;
	private int timeCredit;
	
	public Card() {
		this.id = 0;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the timeCredit
	 */
	public int getTimeCredit() {
		return timeCredit;
	}

	/**
	 * @param timeCredit the timeCredit to set
	 */
	protected void setTimeCredit(int timeCredit) {
		this.timeCredit = timeCredit;
	}
	
	/**
	 * @param newCredit the credit to add to the previous timeCredit
	 */
	protected void addCredit(int newCredit) {
		this.timeCredit += newCredit;
	}
	
	/**
	 * If the timeCredit is high enough, it removes credit from it. If not, it raises a InsufficientCreditException.
	 * @param credit the credit to use.
	 */
	protected void useCredit(int credit) throws InsufficientCreditException {
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
