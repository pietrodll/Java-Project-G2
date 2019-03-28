package station;

import tools.Point;

public class StandardStation extends Station  {
	
	public StandardStation (Point p) {
		super (p);
	}

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
