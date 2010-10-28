package edu.utdallas.hf.ui;
/** 
 * As part of the final documentation, Code Comments will be included, as specified by Razo
 * 
 * */

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

/*
 * This class is called from within Doctor View and will display a list of all notes taken by doctor.
 * A option to add new notes or delete old notes will be present through buttons.
 */
public class PatientNotes extends Activity implements OnClickListener {
	
	
	
	/** Called when the activity is first created. */
	String[][] patientNotes = new String[20][6];
	TableLayout table;
	ScrollView scroll;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientnotes);
        initValues();
    }
    
    public void initValues(){
    	scroll = (ScrollView)findViewById(R.id.patientNotesScrollView);
    	table = (TableLayout)findViewById(R.id.patientNotesRootLayout);
    	scroll.setScrollbarFadingEnabled(true);
    	
    	
    	for(int i = 0; i < 20; i++)
    	{
    		TableRow row = new TableRow(this);
    		if(i%2 == 0)
    		{
    			row.setBackgroundColor(getResources().getColor(R.color.borderColor));
    		}
    		for(int j = 0; j < 3; j++)
    		{
    			patientNotes[i][j] = j+":"+i;
    			
    			if(j==1)
    			{
    				TextView border = new TextView(this);
    				if(i%2==0)
    					border = ViewUtil.createTableBorder(this, R.color.blackBorder);
    				else
    					border = ViewUtil.createTableBorder(this, R.color.borderColor);
    				row.addView(border);
    			}
    			else if(j==0)
    			{
    				TextView text = ViewUtil.createTextView(
    						this, patientNotes[i][j], (float).70, j*100+i, this);
        			row.addView(text);
    			}
    			else if(j==2)
    			{
    				TextView text = ViewUtil.createTextView(
    						this, patientNotes[i][j], (float).30, j*100+i);
        			row.addView(text);
    			}
    		}
    		row.setOnClickListener(this);
    		table.addView(row);
		}
    	
    	
	}
	
//Based on what row is clicked, different data will be loaded into Note.class
	public void onClick(View v) {
		
		for(int i =0; i < 20; i++){
			for(int j=0; j<3; j++){
				if(v.getId()==(j*100)+i){
					Intent patientNotesIntent = new Intent(PatientNotes.this, Note.class);
					patientNotesIntent.putExtra("noteId", v.getId());
					PatientNotes.this.startActivity(patientNotesIntent);
				}
			}
		}
	}

}
