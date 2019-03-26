package sorting.station;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import station.Station;
import tools.Date;

public class OccRateComparator implements Comparator<Station> {
	//il faut mettre un try catch finally
 
	@Override
	public int compare(Station s1, Station s2) {
		
			System.out.println("Sorting w.r.t. occupation rate");
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter the start date of occupation");
			String start = sc.nextLine();
			System.out.println("Please enter the end date of occupation");
			String end = sc.nextLine();
			LocalDateTime startDate = Date.dateInput (start);
			LocalDateTime endDate = Date.dateInput (end);
			return s1.getRateOccupation(startDate, endDate) - s2.getRateOccupation(startDate, endDate);

	}

}
