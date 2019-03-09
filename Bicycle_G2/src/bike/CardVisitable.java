package bike;

import card.CardVisitor;

public interface CardVisitable {
	
	public int accept(CardVisitor card);

}
