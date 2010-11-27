package edu.utdallas.hf.core;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Schedule {
	int did;//doctor id
	Calendar cal;
	String event;//Schedule text entry
	
	public Schedule(){
		did = 0;
		cal = new GregorianCalendar();
		event = "";
	}
	
	public Schedule(int did, Calendar cal, String event){
		this.did = did;
		this.cal = cal;
		this.event = event;
	}
	
	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public Calendar getCal() {
		return cal;
	}

	public void setCal(Calendar cal) {
		this.cal = cal;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
	
}
