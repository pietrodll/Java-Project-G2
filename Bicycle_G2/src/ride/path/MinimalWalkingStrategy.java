package ride.path;

import java.util.ArrayList;
import java.util.Collections;

import bike.Bike;
import ride.Network;
import station.Station;
import tools.Point;

public class MinimalWalkingStrategy implements PathStrategy {
	
	ArrayList<Station> stations = Network.getStations();

	@Override
	public Station[] findPath(Point start, Point end, int bikeType) {
		Station[] stations = new Station[2];
		DistanceToPointComparator distanceComp = new DistanceToPointComparator(start);
		stations[0] = Collections.min(this.stations, distanceComp);
		distanceComp.setPoint(end);
		stations[1] = Collections.min(this.stations, distanceComp);
		return stations;
	}

	@Override
	public Station[] findPath(Point start, Point end) {
		Station[] stations = new Station[2];
		DistanceToPointComparator distanceComp = new DistanceToPointComparator(start);
		stations[0] = Collections.min(this.stations, distanceComp);
		distanceComp.setPoint(end);
		stations[1] = Collections.min(this.stations, distanceComp);
		return stations;
	}

	@Override
	public Station findEndStation(Point start, Point end, Bike bike) {
		// TODO Auto-generated method stub
		return null;
	}

}
