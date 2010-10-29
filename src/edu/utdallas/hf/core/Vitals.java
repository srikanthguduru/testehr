package edu.utdallas.hf.core;

import java.util.Calendar;
import java.util.GregorianCalendar;

import edu.utdallas.hf.commons.DateUtil;
import edu.utdallas.hf.commons.NumberUtil;
/** 
 * As part of the final documentation, Code Comments will be included, as specified by Razo
 * 
 * */

public class Vitals {
	private int patientID;
	private int vitalsID;
	private float temperature = 0.0f;
	private Calendar date = new GregorianCalendar();
	private float bmi = 0.0f;
	private float height = 0.0f;//inches
	private float weight = 0.0f;//pounds
	
	
	public Vitals()
	{
		
	}
	public Vitals(int id, int pid, float h, float w, float t)
	{
		patientID = pid;
		vitalsID = id;
		temperature = t;
		weight = w;
		height = h;
	}
	
	public Vitals(int id, int pid, float Bmi)
	{
		patientID = pid;
		vitalsID = id;
		this.bmi = Bmi;
	}
	
	public Vitals(int id, int pid, float Bmi, float temp)
	{
		patientID = pid;
		vitalsID = id;
		this.bmi = Bmi;
		temperature = temp;
	}
	
	public Vitals(int id, int pid, Calendar date, float Bmi, float temp)
	{
		patientID = pid;
		vitalsID = id;
		this.bmi = Bmi;
		this.date = date;
		temperature = temp;
	}
	
	public Calendar getDate() {
		return date;
	}
	public String getDateStirng(){
		return DateUtil.getDateString(date);
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public float getTemperature() {
		return temperature;
	}
	public String getTemperatureString(){
		return NumberUtil.getTwoDigits(temperature);
	}
	public void setBmi(float bmi) {
		this.bmi = bmi;
	}
	public float getBmi() {
		return bmi;
	}
	public String getBmiString(){
		return NumberUtil.getTwoDigits(bmi);
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public int getPatientID() {
		return patientID;
	}
	public void setVitalsID(int vitalsID) {
		this.vitalsID = vitalsID;
	}
	public int getVitalsID() {
		return vitalsID;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getHeight() {
		return height;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getWeight() {
		return weight;
	}
	
	
}
