package station;

import java.time.LocalDateTime;

import card.Card;
import tools.NegativeTimeException;
import tools.Point;

/**
 * This class represents Plus stations, it extends the class {@code Station}.
 * @author Chloé
 * @see Station
 */
public class PlusStation extends Station {
	
	public PlusStation (Point p) {
		super (p);
	}
	
	/**
	 * If a {@code User} drops a {@code Bike} in a {@code PlusStation}, he gains 5 Time Credits on his Card (if he has one).
	 */
	@Override
	public synchronized void dropBike(Card card, LocalDateTime dropTime) throws NegativeTimeException {
		super.dropBike(card, dropTime);
		card.addCredit(5);
	}

	/**
	 * Redefinition of the equals() method
	 */
	@Override
	public boolean equals(Object obj) {
		boolean res = false;
		if (obj instanceof PlusStation) {
			PlusStation other = (PlusStation) obj;
			res = this.getId() == other.getId();
		}
		return res;
	}

}
