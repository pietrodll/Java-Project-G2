package sorting.station;

import java.util.ArrayList;
import java.util.Comparator;
import station.Station;

public class NumberOpComparator implements Comparator<Station> {
	
	@Override
	public int compare (Station s1, Station s2){
		return s1.getTotalOperations() - s2.getTotalOperations();
	}
	

}
