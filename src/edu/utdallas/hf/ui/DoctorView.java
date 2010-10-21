package edu.utdallas.hf.ui;

/**
 * @author Jerry Arnold - jxa074000
 */

import edu.utdallas.hf.R;
import edu.utdallas.hf.commons.AlertUtil;
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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctorview);
        initControls();
    }
    
    private void initControls(){
    	patientList = (Button)findViewById(R.id.patientList);
    	scheduleList = (Button)findViewById(R.id.doctorScheduleList);
    	logout = (Button)findViewById(R.id.doctorLogout);
    	doctorScrollView = (View)findViewById(R.id.doctorViewScrollView);
    	
    	
    	patientList.setOnClickListener(this);
    	scheduleList.setOnClickListener(this);
    	logout.setOnClickListener(this);
    	doctorScrollView.setScrollbarFadingEnabled(true);
    }

	public void onClick(View v) {
		if(v.getId() == R.id.doctorLogout){
			 AlertDialog confirm = AlertUtil.createLogoutMessage(
					 this, this, "Are you sure you want to log out?");
			 confirm.show();
		}else if(v.getId() == R.id.doctorScheduleList){
			Intent doctorScheduleIntent = new Intent(DoctorView.this, Schedule.class);
			DoctorView.this.startActivity(doctorScheduleIntent);
		}else if(v.getId() == R.id.patientList){
			Intent doctorScheduleIntent = new Intent(DoctorView.this, PatientList.class);
			DoctorView.this.startActivity(doctorScheduleIntent);
		}
		else{
			AlertDialog alert = AlertUtil.createAlertMessage(this, v.toString(), "OK");
			alert.show();
		}
	}
}