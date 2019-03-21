package ride;

import point.Point;
import station.Station;

public class Ride {
	
	private Point start;
	private Point end;
	private Station startStation;
	private Station endStation;
	
	public Ride(Point start, Point end) {
		this.start = start;
		this.end = end;
	}
	
	public void computePath(Network net, pathStrategy ps) {
		
	}

}
