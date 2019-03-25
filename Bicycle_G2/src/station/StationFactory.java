package station;

import ride.Network;
import tools.Point;

/**
 * Class representing a simple factory that can create new types of Stations, to respect the open-close principle
 *
 */
public class StationFactory  {
	
	public Station createStation (String stationType, Point p ) throws TypeStationException, StationSamePositionException {
		for (Station s : Network.getStations()) {
			if (s.getP().equals(p) ) { throw new StationSamePositionException(p);
			}
		}

		if (stationType == null) {
				return null;
			}
		if (stationType.equalsIgnoreCase("Standard")) {
			return new StandardStation(p);
		} else if (stationType.equalsIgnoreCase("Plus")) {
			return new PlusStation(p);
		} else { throw new TypeStationException(stationType);}
	}
		
}
