package edu.utdallas.hf.commons;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberUtil {
	/**
	 * Round a float to max two digits after decimal and return it as a string
	 * @param f the number to be rounded
	 * @return The String representation of the float rounded to two digits
	 */
	public static String getTwoDigits(float f){
		String value = "";
		NumberFormat formatter = new DecimalFormat("#00.##");
		value = formatter.format(f);
		return value;
	}
	
	/**
	 * Round a double to max two digits after decimal and return it as a string
	 * @param d the number to be rounded
	 * @return The String representation of the double rounded to two digits
	 */
	public static String getTwoDigits(double d){
		String value = "";
		NumberFormat formatter = new DecimalFormat("#00.##");
		value = formatter.format(d);
		return value;
	}
}
