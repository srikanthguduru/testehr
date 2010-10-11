package edu.utdallas.hf.ui;

import edu.utdallas.hf.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.patientLogout){
			setResult(RESULT_OK);
	         finish();
		}else{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(v.toString())
			       .setCancelable(false)
			       .setNegativeButton("OK", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			                dialog.dismiss();
			           }
			       });
			AlertDialog alert = builder.create();
			alert.show();
		}
	}
}