package sorting.station;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import station.NoSlotStateAtDateException;
import station.Station;
import tools.Date;
import tools.NegativeTimeException;

public class OccRateComparator implements Comparator<Station> {
 
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
			try {
				double s1occup = s1.getRateOccupation(startDate, endDate);
				double s2occup = s2.getRateOccupation(startDate, endDate);
				if (s1occup - s2occup > 0) {
					return 1;
				} else if (s1occup-s2occup < 0) {
					return -1;
				} else {
					return 0;
				}
			} catch (NoSlotStateAtDateException e) {
				return 0;
			} catch (NegativeTimeException e) {
				return 0;
			}
			

	}

}
