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
	private Button labReviews;
	private Button medicationList;
	private Button notes;
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
    	labReviews = (Button)findViewById(R.id.labReviews);
    	medicationList = (Button)findViewById(R.id.doctorMedicationList);
    	notes = (Button)findViewById(R.id.doctorNotes);
    	scheduleList = (Button)findViewById(R.id.doctorScheduleList);
    	logout = (Button)findViewById(R.id.doctorLogout);
    	doctorScrollView = (View)findViewById(R.id.doctorViewScrollView);
    	
    	
    	patientList.setOnClickListener(this);
    	labReviews.setOnClickListener(this);
    	medicationList.setOnClickListener(this);
    	notes.setOnClickListener(this);
    	scheduleList.setOnClickListener(this);
    	logout.setOnClickListener(this);
    	doctorScrollView.setScrollbarFadingEnabled(true);
    }

	public void onClick(View v) {
		if(v.getId() == R.id.doctorLogout){
			 AlertDialog confirm = AlertUtil.createLogoutMessage(
					 this, this, "Are you sure you want to log out?");
			 confirm.show();
		}else if(v.getId() == R.id.labReviews){
			Intent labReviewIntent = new Intent(DoctorView.this, LabReview.class);
			DoctorView.this.startActivity(labReviewIntent);
		}else if(v.getId() == R.id.doctorMedicationList){
			Intent medicationListIntent = new Intent(DoctorView.this, MedicationList.class);
			DoctorView.this.startActivity(medicationListIntent);
		}else if(v.getId() == R.id.doctorNotes){
			Intent doctorNotesIntent = new Intent(DoctorView.this, DoctorNotes.class);
			DoctorView.this.startActivity(doctorNotesIntent);
		}else if(v.getId() == R.id.doctorScheduleList){
			Intent doctorScheduleIntent = new Intent(DoctorView.this, Schedule.class);
			DoctorView.this.startActivity(doctorScheduleIntent);
		}
		else{
			AlertDialog alert = AlertUtil.createAlertMessage(this, v.toString(), "OK");
			alert.show();
		}
	}
}