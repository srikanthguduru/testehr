package edu.utdallas.hf.commons;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtil {
	
	public static String getDateString(Calendar cal){
		String dateString = "";
		dateString+=cal.get(Calendar.MONTH)+1+"/"+
					cal.get(Calendar.DAY_OF_MONTH)+"/"+
					cal.get(Calendar.YEAR);
		return dateString;
	}
	
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
}
