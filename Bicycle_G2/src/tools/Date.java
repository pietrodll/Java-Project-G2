package tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.*;
import java.util.Scanner;


public class Date {
	
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public static int computeTime (LocalDateTime startTime, LocalDateTime endTime) throws NegativeTimeException {
		int timeSpend = 0;
		if (startTime == null) {
			System.out.println("Error : The starting date is null");
			return (Integer) null;
		} else if (endTime == null) {
			System.out.println("Error : The ending date is null");
			return (Integer) null;
		} else if (startTime== null && endTime == null){
			System.out.println("Error : Both the starting date and the endind date are null");
			return (Integer) null;
		}
		else {
			timeSpend = (int) startTime.until (endTime, ChronoUnit.MINUTES);
			if (timeSpend < 0) {
				throw new NegativeTimeException(startTime, endTime);
			}
			else {return timeSpend;}
		}
	}

	public static LocalDateTime dateInput(String userInput) {
		try {
			LocalDateTime formatDateTime = LocalDateTime.parse(userInput, formatter);
			return (formatDateTime);
		} catch (DateTimeParseException e) {
			System.out.println("Error : The date you entered [" + userInput + "] does not follow the formatter [yyyy-MM-dd HH:mm].");
			return null;
		}
	}
	
	
	//test
	public static void main(String[] args) throws NegativeTimeException {
		String user = "2019-11-23 12:54";
		String user2 = "2019-11/23 12:59";
		LocalDateTime d1 = dateInput(user);
		LocalDateTime d2 =  dateInput (user2);
		System.out.println("new date d1 : " + d1);
		System.out.println("new date d2 : " + d2);
		long time = computeTime (d1,d2);
		System.out.println("The time between d1 and d2 is = " + time);
		LocalDateTime d3 = null;
		long time2 = computeTime (d1, d3);
		System.out.println("The time between d1 and d3 is = " + time2);
		
	}
}