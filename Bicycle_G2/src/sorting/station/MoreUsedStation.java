package sorting.station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import station.Station;

public class MoreUsedStation implements SortingStrategy {

	@Override
	public ArrayList<Station> sorting(ArrayList<Station> s) {
		Collections.sort(s,new NumberOpComparator());
		Collections.reverse(s);
		return s;
	}
}
