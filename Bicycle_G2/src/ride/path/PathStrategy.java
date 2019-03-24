package ride.path;

import ride.Network;
import station.Station;
import tools.Point;

public interface PathStrategy {
	
	public Station[] findPath(Point start, Point end, Network net, int bikeType);

	//rmq Chloé : j'ai crée dans Station les méthodes qui permettent de voir si les velos sont dispo
	
}
