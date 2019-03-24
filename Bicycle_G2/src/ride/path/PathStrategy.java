package ride.path;

import ride.Network;
import station.Station;
import tools.Point;

public interface PathStrategy {
	
	public Station[] findPath(Point start, Point end, int bikeType);
	
	public Station[] findPath(Point start, Point end);
	
	public Station findEndStation(Point start, Point end);

	//rmq Chlo� : j'ai cr�e dans Station les m�thodes qui permettent de voir si les velos sont dispo
	
}
