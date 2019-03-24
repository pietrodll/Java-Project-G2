package ride.path;

import ride.Network;
import station.Station;
import tools.Point;

public interface PathStrategy {
	
	public Station[] findPath(Point start, Point end, int bikeType);
	
	public Station[] findPath(Point start, Point end);
	
	public Station findEndStation(Point start, Point end);

	//rmq Chloé : j'ai crée dans Station les méthodes qui permettent de voir si les velos sont dispo
	
}
