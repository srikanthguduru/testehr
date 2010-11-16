package edu.utdallas.hf.ui;

/** 
 * As part of the final documentation, Code Comments will be included, as specified by Razo
 * 
 * */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import edu.utdallas.hf.R;
import edu.utdallas.hf.commons.ViewUtil;
import edu.utdallas.hf.db.Connection;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Schedule extends Activity implements OnClickListener{
	String [] times = {"8:00", "8:15", "8:30", "8:45", "9:00", "9:15",
						"9:30", "9:45", "10:00", "10:15", "10:30", "10:45",
						"11:00", "11:15", "11:30", "11:45", "12:00", "12:15",
						"12:30", "12:45", "1:00", "1:15", "1:30", "1:45", "2:00",
						"2:15", "2:30", "2:45", "3:00", "3:15", "3:30", "3:45",
						"4:00", "4:15", "4:30", "4:45", "5:00", "5:15", "5:30", 
						"5:45"};
	ArrayList<Calendar> calendars = new ArrayList<Calendar>();
	TableLayout table;
	ScrollView scrollView;
	TableRow row;
	Button timeButton;
	int month, year, day;
	static final int DATE_DIALOG_ID = 0;
	final Calendar c = Calendar.getInstance();
	
	private int DoctorID = 0;
	ArrayList<edu.utdallas.hf.core.Schedule> scheduleList = new ArrayList<edu.utdallas.hf.core.Schedule>();
	Connection con;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedulelist);
        table = (TableLayout)findViewById(R.id.scheduleListTableLayout);
        scrollView = (ScrollView)findViewById(R.id.scheduleListScrollView);
        scrollView.setScrollbarFadingEnabled(true);
        timeButton = (Button)findViewById(R.id.datePickerButton);
        timeButton.setOnClickListener(this);
        timeButton.setWidth(scrollView.getWidth());
        initValues();
    }
    
    public void initValues(){
    	//pulls doctor id from intent
    	Bundle extras = getIntent().getExtras();
    	if(extras != null){
    		DoctorID = (extras.getInt("did"));
    	}
    	
    	//init calender
    	month = c.get(Calendar.MONTH);
    	year = c.get(Calendar.YEAR);
    	day = c.get(Calendar.DAY_OF_MONTH);
    	
    	for(int i = 0; i < times.length; i++){
    		Calendar cal = new GregorianCalendar();
    		if(Integer.parseInt(times[i].split(":")[0]) < 8){
    			cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(times[i].split(":")[0])+12);
	    		cal.set(Calendar.MINUTE, Integer.parseInt(times[i].split(":")[1]));
    		}else{
	    		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(times[i].split(":")[0]));
	    		cal.set(Calendar.MINUTE, Integer.parseInt(times[i].split(":")[1]));
    		}
    		calendars.add(cal);
    	}
    	
    	//pull schedule list
    	con = new Connection();
    	scheduleList = con.getDoctorSchedule(DoctorID, year+"-"+(month+1)+"-"+day);//obtain list of all schedules for date
    	//fill table with list
    	timeButton.setText(month+1+"/"+day+"/"+year);
    	for(int i =0; i < times.length; i++){
    		TableRow row = new TableRow(this);
    		if(i%2 == 0){
    			row.setBackgroundColor(getResources().getColor(R.color.borderColor));
    		}
    		for(int j = 0; j < 3; j ++){
	    		if(j==1){
	    			TextView border = new TextView(this);
					if(i%2==0)
						border = ViewUtil.createTableBorder(this, R.color.blackBorder);
					else
						border = ViewUtil.createTableBorder(this, R.color.borderColor);
					row.addView(border);
	    		}else if (j == 0){
	    			TextView text = ViewUtil.createTextView(
							this, times[i], (float).30, j*100+i);
	    			row.addView(text);
	    		}else{
	    			String event = "";
    				for(int l=0; l < scheduleList.size(); l++){
    					if(calendars.get(i).get(Calendar.HOUR_OF_DAY) < scheduleList.get(l).getCal().get(Calendar.HOUR_OF_DAY)){
    						if(calendars.get(i).get(Calendar.MINUTE) < scheduleList.get(l).getCal().get(Calendar.MINUTE)){
    							event = scheduleList.get(l).getEvent();
    							break;
    						}
    					}
	    			}
	    			TextView text = ViewUtil.createTextView(
							this, event, (float).70, j*100+i);
	    			row.addView(text);
	    		}
    		}
    		table.addView(row);
    	}
    }
    

	public void onClick(View v) {
		if(v.getId() == R.id.datePickerButton){
			showDialog(DATE_DIALOG_ID);
		}
	}
	
	// the callback received when the user "sets" the date in the dialog
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year_, 
                                      int monthOfYear, int dayOfMonth) {
                    year = year_;
                    month = monthOfYear;
                    day = dayOfMonth;
                    timeButton.setText(month+1+"/"+day+"/"+year);
                    setEvents();
                }
            };
            
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case DATE_DIALOG_ID:
        	DatePickerDialog pickerDialog = new DatePickerDialog(
        										this, mDateSetListener, year, month, day);
        	
            return pickerDialog;
        }
        return null;
    }
    
    private void setEvents(){
    	con = new Connection();
    	String dateStirng = year+"-"+(month+1)+"-"+day;
    	System.out.println(dateStirng);
    	scheduleList = con.getDoctorSchedule(DoctorID, dateStirng);//obtain list of all schedules for date
    	//fill table with list
    	timeButton.setText(month+1+"/"+day+"/"+year);
    	for(int i =0; i < times.length; i++){
    		row = (TableRow) table.getChildAt(i);
			String event = "No Event";
			for(int l=0; l < scheduleList.size(); l++){
				if(calendars.get(i).get(Calendar.HOUR_OF_DAY) < scheduleList.get(l).getCal().get(Calendar.HOUR_OF_DAY)){
					if(calendars.get(i).get(Calendar.MINUTE) < scheduleList.get(l).getCal().get(Calendar.MINUTE)){
						event = scheduleList.get(l).getEvent();
						break;
					}
				}
			}
			System.out.println(event);
			((TextView)row.getChildAt(2)).setText(event);
    	}
    }
    
}