package edu.utdallas.hf.ui;

/**
 * @author Jerry Arnold - jxa074000
 */

import edu.utdallas.hf.R;
import edu.utdallas.hf.commons.AlertUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PatientView extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private Button doctorList;
	private Button medicalRecords;
	private Button medicationList;
	private Button notes;
	private Button appointments;
	private Button logout;
	private View patientScrollView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientview);
        initControls();
    }
    
    private void initControls(){
    	doctorList = (Button)findViewById(R.id.doctorList);
    	medicalRecords = (Button)findViewById(R.id.medicalRecords);
    	medicationList = (Button)findViewById(R.id.patientMedicationList);
    	notes = (Button)findViewById(R.id.patientNotes);
    	appointments = (Button)findViewById(R.id.patientAppointments);
    	logout = (Button)findViewById(R.id.patientLogout);
    	patientScrollView = (View)findViewById(R.id.patientViewScrollView);
    	
    	doctorList.setOnClickListener(this);
    	medicalRecords.setOnClickListener(this);
    	medicationList.setOnClickListener(this);
    	notes.setOnClickListener(this);
    	appointments.setOnClickListener(this);
    	logout.setOnClickListener(this);
    	patientScrollView.setScrollbarFadingEnabled(true);
    }

	public void onClick(View v) {
		if(v.getId()==R.id.patientLogout){
			AlertDialog confirm = AlertUtil.createLogoutMessage(
					this, this, "Are you sure you want to log out?");
			confirm.show();
		}else{
			AlertDialog alert = AlertUtil.createAlertMessage(this, v.toString(), "OK");
			alert.show();
		}
	}
}