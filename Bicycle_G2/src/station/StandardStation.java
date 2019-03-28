package station;

import tools.Point;
/**
 * This class represents Standard stations, it extends the class {@code Station}.
 * @author Chloé
 * @see Station
 */
public class StandardStation extends Station  {
	
	public StandardStation (Point p) {
		super (p);
	}

	/**
	 * Redefinition of the equals() method
	 */
	@Override
	public boolean equals(Object obj) {
		boolean res = false;
		if (obj instanceof StandardStation) {
			StandardStation other = (StandardStation) obj;
			res = this.getId() == other.getId();
		}
		return res;
	}
	
	

}
