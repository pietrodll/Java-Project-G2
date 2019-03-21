package ride.path;

import point.Point;
import ride.Network;
import station.Station;

public interface PathStrategy {
	
	public Station[] findPath(Point start, Point end, Network net, int bikeType);

}
