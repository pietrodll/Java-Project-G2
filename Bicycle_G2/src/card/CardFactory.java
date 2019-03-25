package card;

import user.User;

/**
 * This class creates {@code Card} objects.
 * @author Pietro Dellino
 * @see Card
 */
public class CardFactory {
	
	public static final int VLIBRE = 1;
	public static final int VMAX = 2;
	
	public static Card createCard(int cardType, User user) {
		switch (cardType) {
		case VLIBRE:
			return new VlibreCard(user);
		case VMAX:
			return new VmaxCard(user);
		default:
			return null;
		}
	}

}
