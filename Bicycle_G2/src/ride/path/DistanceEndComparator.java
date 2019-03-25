package ride.path;

import java.util.Comparator;

import station.Station;
import tools.Point;

public class DistanceEndComparator implements Comparator<Station> {
	
	private Point point;
	
	public DistanceEndComparator(Point point) {
		this.point = point;
	}
	
	public Point getPoint() { return point; }
	public void setPoint(Point point) { this.point = point; }



	@Override
	public int compare(Station o1, Station o2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
