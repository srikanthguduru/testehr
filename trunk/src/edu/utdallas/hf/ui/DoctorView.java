package edu.utdallas.hf.ui;

import edu.utdallas.hf.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.doctorLogout){
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