package ride.path;

import bike.BikeFactory;
import station.Station;
import tools.Point;

public class FastestPathComparator extends StationComparator {
	
	public final static float WALKING_SPEED = 4;
	public final static float ELECTRIC_SPEED = 20;
	public final static float MECHANIC_SPEED = 15;
	
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
		float speed = this.bikeType == BikeFactory.ELECTRIC ? ELECTRIC_SPEED : MECHANIC_SPEED;
		double timeDiff = WALKING_SPEED*this.getDistanceDiff(arg0, arg1) + speed*this.getReturnDiff(arg0, arg1);
		if (bikeType == 0) {
			res = timeDiff < 0 && arg0.hasBikeAvailable() != null ? -1 : timeDiff > 0 && arg1.hasBikeAvailable() != null ? 1 : 0;
		} else if (bikeType == BikeFactory.ELECTRIC) {
			res = timeDiff < 0 && arg0.hasElectricBikeAvailable() != null ? -1 : timeDiff > 0 && arg1.hasElectricBikeAvailable() != null ? 1 : 0;
		} else {
			res = timeDiff < 0 && arg0.hasMechanicBikeAvailable() != null ? -1 : timeDiff > 0 && arg1.hasMechanicBikeAvailable() != null ? 1 : 0;
		}
		return res;
	}

}
