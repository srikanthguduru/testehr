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

public class PatientView extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private Button vitalSigns;
	private Button medicationList;
	private Button notes;
	private Button appointments;
	private View patientScrollView;
	private int patientId;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientview);
        initControls();
    }
    
    private void initControls(){
    	vitalSigns = (Button)findViewById(R.id.vitalSigns);
    	medicationList = (Button)findViewById(R.id.patientMedicationList);
    	notes = (Button)findViewById(R.id.patientNotes);
    	appointments = (Button)findViewById(R.id.patientAppointments);
    	patientScrollView = (View)findViewById(R.id.patientViewScrollView);
    	
    	Bundle extras = getIntent().getExtras(); 
    	if(extras !=null)
    	{
    		patientId = extras.getInt("patientId");
    	}
    	
    	
    	vitalSigns.setOnClickListener(this);
    	medicationList.setOnClickListener(this);
    	notes.setOnClickListener(this);
    	appointments.setOnClickListener(this);
    	patientScrollView.setScrollbarFadingEnabled(true);
    	
    }

	public void onClick(View v) {
		
		if(v.getId()==R.id.vitalSigns){
			Intent vitalSignsIntent = new Intent(PatientView.this, VitalSigns.class);
			PatientView.this.startActivity(vitalSignsIntent);
		}else if(v.getId() == R.id.patientMedicationList){
			Intent medicationListIntent = new Intent(PatientView.this, MedicationList.class);
			PatientView.this.startActivity(medicationListIntent);
		}else if(v.getId() == R.id.patientNotes){
			Intent patientNotesIntent = new Intent(PatientView.this, PatientNotes.class);
			PatientView.this.startActivity(patientNotesIntent);
		}else{
			AlertDialog alert = AlertUtil.createAlertMessage(this, v.toString(), "OK");
			alert.show();
		}
		
	}
}