package ride.path;

import station.Station;
import tools.Point;

public class DistanceEndComparator extends StationComparator {
	
	public DistanceEndComparator(Point point) { super(point); }

	@Override
	public int compare(Station arg0, Station arg1) {
		int res = 0;
		double distanceDiff = this.getDistanceDiff(arg0, arg1);
		res = distanceDiff < 0 && !arg0.isStationFull() ? -1 : distanceDiff > 0 && !arg1.isStationFull() ? 1 : 0;
		return res;
	}

}
