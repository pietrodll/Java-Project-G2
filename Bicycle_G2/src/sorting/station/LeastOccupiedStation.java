package sorting.station;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import station.Station;

public class LeastOccupiedStation implements SortingStrategy {
	
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	public LeastOccupiedStation(LocalDateTime startDate, LocalDateTime endTime) {
		this.startDate = startDate;
		this.endDate = endTime;
	}
	
	@Override
	public ArrayList<Station> sorting(ArrayList<Station> s) {
		Collections.sort(s,new OccRateComparator(startDate, endDate));
		return s;
	}

}
