package edu.utdallas.hf.core;

import java.util.Calendar;



public class Patient {
	private String fName; 
	private String lName;
	private int id;
	private Calendar dob;
	private Vitals vitals;
	
	public Patient(){
		fName = "";
		lName = "";
		id = 0;
		dob = null;
		vitals = null;
	}
	public Patient(String f, String l, Calendar d, int i){
		fName = f;
		lName = l;
		id = i;
		dob = d;
	}
	public Patient(String f, String l, Calendar d, int i, Vitals v){
		fName = f;
		lName = l;
		id = i;
		dob = d;
		vitals = v;
	}
	
	public String getFName(){
		return fName;
	}
	public String getLName(){
		return lName;
	}
	public int getId(){
		return id;
	}
	public Calendar getDob(){
		return dob;
	}
	public String getDobString(){
		return dob.get(Calendar.MONTH)+"/"+dob.get(Calendar.DAY_OF_MONTH)+"/"+dob.get(Calendar.YEAR);
	}
	public Vitals getVitals(){
		return vitals;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public void setDob(Calendar dob) {
		this.dob = dob;
	}
	public void setVitals(Vitals vitals) {
		this.vitals = vitals;
	}
	public void setId(int i){
		id = i;
	}
}
