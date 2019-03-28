package ride.path;

import bike.BikeFactory;
import station.Station;
import tools.Point;

public class FastestPathComparator extends StationComparator {
	
	public final static double WALKING_SPEED = 4.0;
	public final static double ELECTRIC_SPEED = 20.0;
	public final static double MECHANIC_SPEED = 15.0;
	
	private Point returnPoint;

	public FastestPathComparator(Point start, Point returnPoint, int bikeType) {
		super(start, bikeType);
		this.returnPoint = returnPoint;
	}
	
	private double getReturnDiff(Station s1, Station s2) {
		return this.returnPoint.distancePoint(s1.getP()) - this.returnPoint.distancePoint(s2.getP());
	}

	@Override
	public int compare(Station arg0, Station arg1) {
		int res = 0;
		double speed = this.bikeType == BikeFactory.ELECTRIC ? ELECTRIC_SPEED : MECHANIC_SPEED;
		double timeDiff = this.getDistanceDiff(arg0, arg1)/WALKING_SPEED + this.getReturnDiff(arg0, arg1)/speed;
		if (bikeType == 0) {
			res = StationComparator.availabilityComparator(timeDiff, arg0.hasBikeAvailable() != null, arg1.hasBikeAvailable() != null);
		} else if (bikeType == BikeFactory.ELECTRIC) {
			res = StationComparator.availabilityComparator(timeDiff, arg0.hasElectricBikeAvailable() != null, arg1.hasElectricBikeAvailable() != null);
		} else {
			res = StationComparator.availabilityComparator(timeDiff, arg0.hasMechanicBikeAvailable() != null, arg1.hasMechanicBikeAvailable() != null);
		}
		return res;
	}

}
