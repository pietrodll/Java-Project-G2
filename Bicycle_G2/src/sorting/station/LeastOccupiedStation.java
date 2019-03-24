package sorting.station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import station.Station;

public class LeastOccupiedStation implements SortingStrategy {
	
	@Override
	public ArrayList<Station> sorting(ArrayList<Station> s) {
		Collections.sort(s,new OccRateComparator());
		return s;
	}

}
