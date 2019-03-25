package ride.path;

import java.util.ArrayList;
import java.util.Collections;

import bike.Bike;
import ride.Network;
import station.Station;
import tools.Point;

public class FastestPathStrategy implements PathStrategy {
	
	private ArrayList<Station> stations;

	public FastestPathStrategy() {
		this.stations = Network.getStations();
	}

	@Override
	public Station[] findPath(Point start, Point end, int bikeType) {
		DistanceEndComparator dec = new DistanceEndComparator(end);
		Station returnStation = Collections.min(this.stations, dec);
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Station[] findPath(Point start, Point end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Station findEndStation(Point start, Point end, Bike bike) {
		// TODO Auto-generated method stub
		return null;
	}

}
