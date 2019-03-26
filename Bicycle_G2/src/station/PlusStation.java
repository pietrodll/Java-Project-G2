package station;

import java.time.LocalDateTime;

import card.Card;
import tools.NegativeTimeException;
import tools.Point;

public class PlusStation extends Station {
	
	public PlusStation (Point p) {
		super (p);
	}
	
	@Override
	public synchronized void dropBike(Card card, LocalDateTime dropTime) throws NegativeTimeException {
		super.dropBike(card, dropTime);
		card.addCredit(5);
	}

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
