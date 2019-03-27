package station;

import ride.Network;
import tools.Point;

/**
 * Class representing a simple factory that can create new types of Stations, to respect the open-close principle
 *
 */
public class StationFactory  {
	
	public Network net;
	
	public StationFactory(Network net) { this.net = net; }
	
	public Station createStation (String stationType, Point p ) throws TypeStationException, StationSamePositionException {
		for (Station s : this.net.getStations()) {
			if (s.getP().equals(p) ) { throw new StationSamePositionException(p); }
		}
		if (stationType == null) { return null; }
		if (stationType.equalsIgnoreCase("Standard")) {
			Station s1 = new StandardStation(p);
			this.net.addStation(s1);
			return s1;
		} else if (stationType.equalsIgnoreCase("Plus")) {
			Station s2 = new PlusStation(p);
			this.net.addStation(s2);	
			return s2;
		} else {
			throw new TypeStationException(stationType);
		}
	}
		
}
