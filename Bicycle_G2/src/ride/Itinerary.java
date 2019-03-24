package ride;

import ride.path.PathStrategy;
import station.Station;
import tools.Point;

/**
 * This class represents an itinerary for the users. There is a start.
 * @author Pietro Dellino
 *
 */
public class Itinerary {
	
	private Point start;
	private Point end;
	private Station startStation;
	private Station endStation;
	
	public Itinerary(Point start, Point end) {
		this.start = start;
		this.end = end;
	}
	
	//il faudrait permettre de ne pas choisir de strategy (mettre une stratégie par défaut)
	public void computePath(Network net, PathStrategy ps, int bikeType) {
		Station[] stations = ps.findPath(this.start, this.end, net, bikeType);
		this.startStation = stations[0];
		this.endStation = stations[1];
	}

	/**
	 * @return the start
	 */
	public Point getStart() { return start; }

	/**
	 * @return the end
	 */
	public Point getEnd() { return end; }

	/**
	 * @return the startStation
	 */
	public Station getStartStation() { return startStation; }

	/**
	 * @return the endStation
	 */
	public Station getEndStation() { return endStation; }
	
	

}
