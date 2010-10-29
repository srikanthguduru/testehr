package edu.utdallas.hf.commons;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberUtil {
	public static String getTwoDigits(float f){
		String value = "";
		NumberFormat formatter = new DecimalFormat("#00.##");
		value = formatter.format(f);
		return value;
	}
	
	public static String getTwoDigits(double d){
		String value = "";
		NumberFormat formatter = new DecimalFormat("#00.##");
		value = formatter.format(d);
		return value;
	}
}
