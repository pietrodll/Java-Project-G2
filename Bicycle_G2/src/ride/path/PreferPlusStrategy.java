package ride.path;

import java.util.ArrayList;
import java.util.Collections;

import bike.Bike;
import ride.Network;
import station.PlusStation;
import station.Station;
import tools.Point;

public class PreferPlusStrategy implements PathStrategy {
	
	private ArrayList<Station> stations;
	
	public PreferPlusStrategy() {
		this.stations = Network.getStations();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Station[] findPath(Point start, Point end, int bikeType) {
		ArrayList<Station> stationsBis = (ArrayList<Station>) this.stations.clone();
		Station[] stations = new Station[2];
		DistanceStartComparator distanceComp = new DistanceStartComparator(start, bikeType);
		stations[0] = Collections.min(this.stations, distanceComp);
		DistanceEndComparator distEndComp = new DistanceEndComparator(end);
		Collections.sort(stationsBis, distEndComp);
		double minDist = end.distancePoint(stationsBis.get(0).getP());
		stations[1] = stationsBis.get(0);
		for (Station s : stationsBis) {
			if (s instanceof PlusStation && s.getP().distancePoint(end) <= 1.10*minDist) {
				stations[1] = s;
				break;
			} else if (s.getP().distancePoint(end) > 1.10*minDist) {
				break;
			}
		}
		return stations;
	}

	@Override
	public Station[] findPath(Point start, Point end) {
		ArrayList<Station> stationsBis = (ArrayList<Station>) this.stations.clone();
		Station[] stations = new Station[2];
		DistanceStartComparator distanceComp = new DistanceStartComparator(start);
		stations[0] = Collections.min(this.stations, distanceComp);
		DistanceEndComparator distEndComp = new DistanceEndComparator(end);
		stations[1] = Collections.min(this.stations, distEndComp);
		stations[0] = Collections.min(this.stations, distanceComp);
		Collections.sort(stationsBis, distEndComp);
		double minDist = end.distancePoint(stationsBis.get(0).getP());
		stations[1] = stationsBis.get(0);
		for (Station s : stationsBis) {
			if (s instanceof PlusStation && s.getP().distancePoint(end) <= 1.10*minDist) {
				stations[1] = s;
				break;
			} else if (s.getP().distancePoint(end) > 1.10*minDist) {
				break;
			}
		}
		return stations;
	}

	@Override
	public Station findEndStation(Point start, Point end, Bike bike) {
		// TODO Auto-generated method stub
		return null;
	}

}