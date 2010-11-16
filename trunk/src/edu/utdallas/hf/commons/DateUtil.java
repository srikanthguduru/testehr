package edu.utdallas.hf.commons;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtil {
	
	/**
	 * The Date as a string based on the calendar object
	 * @param cal The calendar object
	 * @return The String representation of the calendar object mm/dd/yyyy
	 */
	public static String getDateString(Calendar cal){
		String dateString = "";
		dateString+=cal.get(Calendar.MONTH)+1+"/"+
					cal.get(Calendar.DAY_OF_MONTH)+"/"+
					cal.get(Calendar.YEAR);
		return dateString;
	}
	
	/**
	 * The date as a Calendar object based on a date string
	 * @param dateString The string representation of a date (yyyy-mm-dd)
	 * @return the calendar obeject based on the dateString
	 */
	public static Calendar parseDateString(String dateString){
		Calendar cal = null;
		cal = new GregorianCalendar();
		String [] initialParse = dateString.split(" ");
		String [] dateParse = initialParse[0].split("-");
		cal.set(Integer.parseInt(dateParse[0]), 
				Integer.parseInt(dateParse[1])-1, 
				Integer.parseInt(dateParse[2]));
		return cal; 
	}
	
	/**
	 * The date as a Calendar object based on a datetime string
	 * @param dateTimeString The string representation of a date (yyyy-mm-dd hh:mm:ss)
	 * @return the calendar obeject based on the dateString
	 */
	public static Calendar parseDateTimeString(String dateTimeString){
		Calendar cal = new GregorianCalendar();
		String [] initialParse = dateTimeString.split(" ");
		String [] dateParse = initialParse[0].split("-");
		String [] timeParse = initialParse[1].split(":");
		cal.set(Integer.parseInt(dateParse[0]),
				Integer.parseInt(dateParse[1])-1,
				Integer.parseInt(dateParse[2]),
				Integer.parseInt(timeParse[0]),
				Integer.parseInt(timeParse[1]),
				Integer.parseInt(timeParse[2]));
		return cal;
	}
}
