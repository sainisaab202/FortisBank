package bus;

import java.util.Calendar;
import java.util.Date;

public class InternalDate {

	public long daysBetween(Date one, Date two) 
	{
		long difference = (one.getTime() - two.getTime())/86400000;
		return Math.abs(difference);
	}
	
	public long generateDate(Calendar myNextCalendar) {
		
		Date today = new Date();
		
		myNextCalendar = Calendar.getInstance();
		myNextCalendar.set(2021,2, 17); // this is the date before, 
		Date nyd = myNextCalendar.getTime(); // this is how we set the date before taken from clients openDate
		
		//nyd.
		
		InternalDate ourDate = new InternalDate();
		long days = ourDate.daysBetween(nyd, today);
		
		return days;
	}
	
}

