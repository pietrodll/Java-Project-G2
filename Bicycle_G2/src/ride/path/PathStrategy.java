package ride.path;

import point.Point;
import station.Station;

public interface PathStrategy {
	
	public Station[] findPath(Point start, Point end);

}
