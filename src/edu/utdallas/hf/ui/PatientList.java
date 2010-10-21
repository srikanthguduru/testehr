package edu.utdallas.hf.ui;

/**
 * @author Jerry Arnold - jxa074000
 */

import edu.utdallas.hf.R;
import edu.utdallas.hf.commons.ViewUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class PatientList extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	String[][] patients = new String[20][3];
	TableLayout table;
	ScrollView scrollView;
	TableRow row;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientlist);
        scrollView = (ScrollView)findViewById(R.id.patientListScrollView);
        scrollView.setScrollbarFadingEnabled(true);
        initValues();
    }
    
    public void initValues(){
    	table = (TableLayout)findViewById(R.id.patientListRootLayout);
    	for(int i =0; i < 20; i++){
    		row = new TableRow(this);
    		if(i%2 == 0){
    			row.setBackgroundColor(getResources().getColor(R.color.borderColor));
    		}
    		for(int j=0; j<3; j++){
    			patients[i][j] = j+":"+i;
    			if(j==1){
    				TextView border;
    				if(i%2==0)
    					border = ViewUtil.createTableBorder(this, R.color.blackBorder);
    				else
    					border = ViewUtil.createTableBorder(this, R.color.borderColor);
    				row.addView(border);
    			}else if(j==0){
    				TextView text = ViewUtil.createTextView(
    						this, patients[i][j], (float).7, (j*100)+i, this);
    				text.setPadding(0, 5, 0, 5);
        			row.addView(text);
    			}else if(j==2){
    				TextView text = ViewUtil.createTextView(
    						this, patients[i][j], (float).3, (j*100)+i);
    				text.setPadding(0, 5, 0, 5);
        			row.addView(text);
    			}
    		}
    		table.addView(row);
    	}
    }
    

	public void onClick(View v) {
		for(int i =0; i < 20; i++){
			for(int j=0; j<3; j++){
				if(v.getId()==(j*100)+i){
					Intent patientViewIntent = new Intent(PatientList.this, PatientView.class);
					PatientList.this.startActivity(patientViewIntent);
				}
			}
		}
	}
    
}