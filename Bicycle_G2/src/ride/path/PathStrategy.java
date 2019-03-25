package ride.path;

import bike.Bike;
import station.Station;
import tools.Point;

public interface PathStrategy {
	
	public Station[] findPath(Point start, Point end, int bikeType);
	
	public Station[] findPath(Point start, Point end);
	
	public Station findEndStation(Point start, Point end, Bike bike);

	//rmq Chloé : j'ai crée dans Station les méthodes qui permettent de voir si les velos sont dispo
	
}
