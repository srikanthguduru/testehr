package edu.utdallas.hf.ui;

/** 
 * As part of the final documentation, Code Comments will be included, as specified by Razo
 * 
 * */

import edu.utdallas.hf.R;
import edu.utdallas.hf.commons.AlertUtil;
import edu.utdallas.hf.core.Doctor;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DoctorView extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private Button patientList;
	private Button scheduleList;
	private Button logout;
	private View doctorScrollView;
	private int DoctorId = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctorview);
        initControls();
    }
    
    private void initControls(){
    	//pulls doctor id from intent
    	Bundle extras = getIntent().getExtras();
    	if(extras != null)
    	{
    		DoctorId = (extras.getInt("did"));
    	}
    	
    	//get the Patient List button from view
    	patientList = (Button)findViewById(R.id.patientList);
    	//get the Schedule List button from view
    	scheduleList = (Button)findViewById(R.id.doctorScheduleList);
    	//get the Logout button from view
    	logout = (Button)findViewById(R.id.doctorLogout);
    	doctorScrollView = (View)findViewById(R.id.doctorViewScrollView);
    	
    	//add onClickListener for the button
    	patientList.setOnClickListener(this);
    	scheduleList.setOnClickListener(this);
    	logout.setOnClickListener(this);
    	doctorScrollView.setScrollbarFadingEnabled(true);
    }

	public void onClick(View v) {
		//When the user clicks on logout
		if(v.getId() == R.id.doctorLogout){
			 AlertDialog confirm = AlertUtil.createLogoutMessage(
					 this, 
					 this, 
					 "Are you sure you want to log out?");
			 confirm.show();
		//when the user clicks on the Schedule button
		}else if(v.getId() == R.id.doctorScheduleList){
			Intent doctorScheduleIntent = new Intent(DoctorView.this, Schedule.class);
			doctorScheduleIntent.putExtra("did", DoctorId);
			DoctorView.this.startActivity(doctorScheduleIntent);
		//when the user clicks on the Patient List button
		}else if(v.getId() == R.id.patientList){
			Intent doctorScheduleIntent = new Intent(DoctorView.this, PatientList.class);
			DoctorView.this.startActivity(doctorScheduleIntent);
		}
	}
}