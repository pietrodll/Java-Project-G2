package ride.path;

import java.awt.SecondaryLoop;
import java.util.Comparator;

import bike.BikeFactory;
import station.Station;
import tools.Point;

public abstract class StationComparator implements Comparator<Station> {
	
	protected Point point;
	protected int bikeType = 0;
	
	public StationComparator(Point p) {
		this.point = p;
	}
	
	public StationComparator(Point p, int bikeType) {
		this.point = p;
		if (bikeType == BikeFactory.ELECTRIC || bikeType == BikeFactory.MECHANIC) {
			this.bikeType = bikeType;
		}
	}
	
	public Point getPoint() { return point; }
	public void setPoint(Point point) { this.point = point; }

	@Override
	public abstract int compare(Station arg0, Station arg1);
	
	/**
	 * 
	 * @param s1 The first {@code Station}
	 * @param s2 The second {@code Station}
	 * @return The difference between the distances of the stations to {@code this.point}
	 */
	protected double getDistanceDiff(Station s1, Station s2) {
		return this.point.distancePoint(s1.getP()) - this.point.distancePoint(s2.getP());
	}

}
