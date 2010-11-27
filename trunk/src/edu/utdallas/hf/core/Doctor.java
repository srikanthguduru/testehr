package edu.utdallas.hf.core;

import java.util.ArrayList;
import java.util.Calendar;
/** 
 * As part of the final documentation, Code Comments will be included, as specified by Razo
 * 
 * 
 * */
public class Doctor {
	private String fName;//first name,last name
	private String lName;
	private Calendar dob;//date of birth
	private int id;//index id for tables
	private ArrayList<Patient> patients;//patients for doctor
	public Doctor(){
		fName = "";
		lName ="";
		dob = null;
		id = 0;
		patients = null;
	}
	
	public String getFName(){
		return fName;
	}
	public String getLName(){
		return lName;
	}
	public Calendar getDob(){
		return dob;
	}
	public int getId(){
		return id; 
	}
	public ArrayList<Patient> getPatients(){
		return patients;
	}
	
	public void setFName(String f){
		fName = f;
	}
	public void setLName(String l){
		lName = l;
	}
	public void setDob(Calendar d){
		dob = d;
	}
	public void setId(int i){
		id = i;
	}
}
