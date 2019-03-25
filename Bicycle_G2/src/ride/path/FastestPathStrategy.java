package ride.path;

import java.util.ArrayList;
import java.util.Collections;

import bike.Bike;
import bike.BikeFactory;
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
		FastestPathComparator fpc = new FastestPathComparator(start, returnStation.getP(), bikeType);
		Station pickupStation = Collections.min(this.stations, fpc);
		Station[] stations = {pickupStation, returnStation};
		return stations;
	}

	@Override
	public Station[] findPath(Point start, Point end) {
		Station[] stElec = this.findPath(start, end, BikeFactory.ELECTRIC);
		Station[] stMech = this.findPath(start, end, BikeFactory.MECHANIC);
		double timeMechanic = FastestPathComparator.MECHANIC_SPEED*stMech[0].getP().distancePoint(stMech[1].getP()) + FastestPathComparator.WALKING_SPEED*(start.distancePoint(stMech[0].getP()) + end.distancePoint(stMech[1].getP()));
		double timeElec = FastestPathComparator.ELECTRIC_SPEED*stElec[0].getP().distancePoint(stElec[1].getP()) + FastestPathComparator.WALKING_SPEED*(start.distancePoint(stElec[0].getP()) + end.distancePoint(stElec[1].getP()));
		return timeElec < timeMechanic ? stElec : stMech;
	}

	@Override
	public Station findEndStation(Point start, Point end, Bike bike) {
		DistanceEndComparator dec = new DistanceEndComparator(end);
		Station station = Collections.min(this.stations, dec);
		return station;
	}

}
