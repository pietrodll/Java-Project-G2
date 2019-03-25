package ride.path;

import java.util.Comparator;

import bike.BikeFactory;
import station.Station;
import tools.Point;

public class DistanceToPointComparator implements Comparator<Station> {
	
	private Point point;
	private int bikeType = 0;
	
	public DistanceToPointComparator(Point p, int bikeType) {
		this.point = p;
		if (bikeType == BikeFactory.ELECTRIC || bikeType == BikeFactory.MECHANIC) {
			this.bikeType = bikeType;
		}
	}
	
	public DistanceToPointComparator(Point p) {
		this.point = p;
	}
	
	public Point getPoint() { return this.point; }
	public void setPoint(Point p) { this.point = p; }

	/**
	 * Compares the stations according to their distance to {@code this.point}. It 
	 */
	@Override
	public int compare(Station arg0, Station arg1) {
		int res = 0;
		double distanceDiff = this.point.distancePoint(arg0.getP()) - this.point.distancePoint(arg1.getP());
		if (bikeType == 0) {
			res = distanceDiff < 0 && arg0.hasBikeAvailable() ? -1 : distanceDiff > 0 && arg1.hasBikeAvailable() ? 1 : 0;
		} else if (bikeType == BikeFactory.ELECTRIC) {
			res = distanceDiff < 0 && arg0.hasElectricBikeAvailable() ? -1 : distanceDiff > 0 && arg1.hasElectricBikeAvailable() ? 1 : 0;
		} else {
			res = distanceDiff < 0 && arg0.hasMechanicBikeAvailable() ? -1 : distanceDiff > 0 && arg1.hasMechanicBikeAvailable() ? 1 : 0;
		}
		
		return res;
	}

}
