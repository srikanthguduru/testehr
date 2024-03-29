package edu.utdallas.hf.core;

import java.util.ArrayList;
import java.util.Calendar;

import edu.utdallas.hf.commons.DateUtil;
/** 
 * As part of the final documentation, Code Comments will be included, as specified by Razo
 * 
 * */

public class Patient {
	private String fName; 
	private String lName;
	private int id;//patient id
	private Calendar dob;//date of birth
	private Vitals vitals;//patient vital signs
	private ArrayList<Note> notes;//patient notes
	
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
		return DateUtil.getDateString(dob);
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
	public void setNotes(ArrayList<Note> notes) {
		this.notes = notes;
	}
	public ArrayList<Note> getNotes() {
		return notes;
	}
}
