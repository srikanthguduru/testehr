package edu.utdallas.hf.ui;

/** 
 * As part of the final documentation, Code Comments will be included, as specified by Razo
 * List all patients in database system in order by table id
 * */

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import edu.utdallas.hf.R;
import edu.utdallas.hf.commons.ViewUtil;
import edu.utdallas.hf.core.Patient;
import edu.utdallas.hf.db.PatientDAO;

public class PatientList extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	TableLayout table;
	ScrollView scrollView;
	TableRow row;
	ArrayList<Patient> patientList = new ArrayList<Patient>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientlist);
        scrollView = (ScrollView)findViewById(R.id.patientListScrollView);
        scrollView.setScrollbarFadingEnabled(true);
        initValues();
    }
    
    private void initValues(){
    	patientList = PatientDAO.getPatientList();//obtain list of all patients
    	table = (TableLayout)findViewById(R.id.patientListRootLayout);
    	
    	//populate table with list entries
    	for(int i =0; i < patientList.size(); i++){
    		row = new TableRow(PatientList.this);
    		
    		if(i%2 == 0){
    			row.setBackgroundColor(getResources().getColor(R.color.borderColor));
    		}
    		
    		for(int j=0; j<3; j++){
    			if(j==1){
    				TextView border;
    				if(i%2==0)
    					border = ViewUtil.createTableBorder(PatientList.this, R.color.blackBorder);
    				else
    					border = ViewUtil.createTableBorder(PatientList.this, R.color.borderColor);
    				row.addView(border);
    			}else if(j==0){
    				//add the patient's first and last name to the first column
    				TextView text = ViewUtil.createTextView(
    						PatientList.this, 
    						patientList.get(i).getFName()+ " " +
    							patientList.get(i).getLName(), 
    						(float).7, 
    						patientList.get(i).getId(), 
    						PatientList.this);
    				text.setPadding(0, 5, 0, 5);
        			row.addView(text);
    			}else if(j==2){
    				//add the patient's date of birth to the second column
    				TextView text = ViewUtil.createTextView(
    						PatientList.this,
    						patientList.get(i).getDobString(), 
    						(float).3, 
    						patientList.get(i).getId());
    				text.setPadding(0, 5, 0, 5);
        			row.addView(text);
    			}
    		}
    		table.addView(row);
    	}
    }

	public void onClick(View v) {
		for(int i =0; i < patientList.size(); i++){
			for(int j=0; j<3; j++){
				if(v.getId()==patientList.get(i).getId()){
					//when the patient's name is clicked on, go to the patient's info page
					Intent patientViewIntent = new Intent(PatientList.this, PatientView.class);
					patientViewIntent.putExtra("patientId", v.getId());//passes patient id through the intent.putExtra
					PatientList.this.startActivity(patientViewIntent);
					break;
				}
			}
		}
	}
    
}