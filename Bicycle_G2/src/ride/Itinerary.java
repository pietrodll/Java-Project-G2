package ride;

import point.Point;
import ride.path.PathStrategy;
import station.Station;

/**
 * This class represents a 
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
	
	public void computePath(Network net, PathStrategy ps, int bikeType) {
		Station[] stations = ps.findPath(this.start, this.end, net, bikeType);
		this.startStation = stations[0];
		this.endStation = stations[1];
	}

	/**
	 * @return the start
	 */
	protected Point getStart() {
		return start;
	}

	/**
	 * @return the end
	 */
	protected Point getEnd() {
		return end;
	}

	/**
	 * @return the startStation
	 */
	protected Station getStartStation() {
		return startStation;
	}

	/**
	 * @return the endStation
	 */
	protected Station getEndStation() {
		return endStation;
	}
	
	

}
