package station;

import ride.Network;
import tools.Point;

/**
 * Class representing a simple factory that can create new types of Stations, to respect the open-close principle
 *
 */
public class StationFactory  {
	
	public Station createStation (String stationType, Point p ) throws TypeStationException, StationSamePositionException {
		if (Network.getStations() != null ) {
			for (Station s : Network.getStations()) {
				if (s.getP().equals(p) ) { throw new StationSamePositionException(p); }
			}
		}

		if (stationType == null) { return null; }
		
		if (stationType.equalsIgnoreCase("Standard")) {
			Station s1 = new StandardStation(p);
			Network.getStations().add(s1);
			return s1;
		} else if (stationType.equalsIgnoreCase("Plus")) {
			Station s2 = new PlusStation(p);
			Network.getStations().add(s2);		
			return s2;
		} else {
			throw new TypeStationException(stationType);
		}
	}
		
}
