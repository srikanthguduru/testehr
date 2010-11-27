package edu.utdallas.hf.core;

import java.util.Calendar;
import java.util.GregorianCalendar;

import edu.utdallas.hf.commons.DateUtil;

/** 
 * As part of the final documentation, Code Comments will be included, as specified by Razo
 * 
 * */
public class Note {
	private int id;//note id
	private String title;//header text for note
	private String text;//Body of note
	private Calendar date;

	public Note(){
		id = 0;
		title = "";
		text = "";
		date = new GregorianCalendar(); 
	}
	
	public Note(int i, String t, String txt, Calendar d){
		id = i;
		title = t;
		text = txt;
		date = d;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
	public String getDateString(){
		return DateUtil.getDateString(date);
	}
	
}
