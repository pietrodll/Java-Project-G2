package card;

/**
 * This class creates {@code Card} objects.
 * @author Pietro Dellino
 * @see Card
 */
public class CardFactory {
	
	public static final int VLIBRE = 1;
	public static final int VMAX = 2;
	
	public static Card createCard(int cardType) {
		switch (cardType) {
		case VLIBRE:
			return new VlibreCard();
		case VMAX:
			return new VmaxCard();
		default:
			return null;
		}
	}

}
