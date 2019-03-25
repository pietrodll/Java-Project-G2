package ride.path;

import station.Station;
import tools.Point;

public class FastestPathComparator extends StationComparator {
	
	public final float WALKING_SPEED = 4;
	public final float ELECTRIC_SPEED = 20;
	public final float MECHANIC_SPEED = 15;
	
	private Point destination;

	public FastestPathComparator(Point pickup, Point destination, int bikeType) {
		super(pickup, bikeType);
		this.destination = destination;
	}
	
	private double getDestinationDiff(Station s1, Station s2) {
		return this.destination.distancePoint(s1.getP()) - this.destination.distancePoint(s2.getP());
	}

	@Override
	public int compare(Station arg0, Station arg1) {
		
		// TODO Auto-generated method stub
		return 0;
	}

}
