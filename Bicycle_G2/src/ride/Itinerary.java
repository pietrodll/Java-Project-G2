package ride;

import point.Point;
import ride.path.PathStrategy;
import station.Station;

public class Itinerary {
	
	private Point start;
	private Point end;
	private Station startStation;
	private Station endStation;
	
	public Itinerary(Point start, Point end) {
		this.start = start;
		this.end = end;
	}
	
	public void computePath(Network net, PathStrategy ps, int bikeType) {
		Station[] stations = ps.findPath(this.start, this.end, net, bikeType);
		this.startStation = stations[0];
		this.endStation = stations[1];
	}

}
