package edu.utdallas.hf.ui;

/** 
 * As part of the final documentation, Code Comments will be included, as specified by Razo
 * 
 * */

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import edu.utdallas.hf.R;
import edu.utdallas.hf.commons.ViewUtil;
import edu.utdallas.hf.core.Medication;
import edu.utdallas.hf.db.Connection;

public class MedicationList extends Activity{
    /** Called when the activity is first created. */
	ArrayList<Medication> medications = new ArrayList<Medication>();
	TableLayout table;
	ScrollView scrollView;
	TableRow row;
	Connection con;
	int pid = 0;//patient id, this is values should be passed from patientView
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicationlist);
        scrollView = (ScrollView)findViewById(R.id.medicationListScrollView);
        scrollView.setScrollbarFadingEnabled(true);
        initValues();
    }
    
    public void initValues(){
    	Bundle extras = getIntent().getExtras(); 
    	if(extras !=null){
    		pid = extras.getInt("pid");
    	}
    	//create a new connection to get data from database
    	con = new Connection();
    	//get the medication list from the database
    	medications = con.getPatientMedication(pid);
    	table = (TableLayout)findViewById(R.id.medicationListRootLayout);
    	//loop through all the medications in the list
    	for(int i =0; i < medications.size(); i++){
    		row = new TableRow(this);
    		if(i%2 == 0){
    			row.setBackgroundColor(getResources().getColor(R.color.borderColor));
    		}
    		for(int j=0; j<3; j++){
    			//create the border in the table
    			if(j==1){
    				TextView border;
    				if(i%2==0)
    					border = ViewUtil.createTableBorder(this, R.color.blackBorder);
    				else
    					border = ViewUtil.createTableBorder(this, R.color.borderColor);
    				row.addView(border);
    			//display the drug/dose/unit
    			}else if(j==0){
    				TextView text = ViewUtil.createTextView(
    						this, medications.get(i).getDrugString(), (float).5, j*100+i);
        			row.addView(text);
        		//display the status
    			}else if(j==2){
    				TextView text = ViewUtil.createTextView(
    						this, medications.get(i).getActiveString(), (float).5, j*100+i);
        			row.addView(text);
    			}
    		}
    		table.addView(row);
    	}
    }
    
}