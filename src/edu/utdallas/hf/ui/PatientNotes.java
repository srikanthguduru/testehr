package edu.utdallas.hf.ui;
/** 
 * As part of the final documentation, Code Comments will be included, as specified by Razo
 * 
 * */

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import edu.utdallas.hf.R;
import edu.utdallas.hf.commons.ViewUtil;
import edu.utdallas.hf.core.Note;
import edu.utdallas.hf.db.PatientDAO;

/*
 * This class is called from within Doctor View and will display a list of all notes taken by doctor.
 * A option to add new notes or delete old notes will be present through buttons.
 */
public class PatientNotes extends Activity implements OnClickListener {
	
	
	
	/** Called when the activity is first created. */
	ArrayList<Note> pNotes = new ArrayList<Note>();
	TableLayout table;
	int pid = 0;
	ScrollView scroll;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientnotes);
        initValues();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notesmenu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.newNote:
        	createNewNote();
        	return true;
        default:
        	return super.onOptionsItemSelected(item);
        }
    }
    
    public void createNewNote(){
    	Intent newPatientNotesIntent = new Intent(PatientNotes.this, NewNote.class);
    	newPatientNotesIntent.putExtra("pid", pid);
		PatientNotes.this.startActivity(newPatientNotesIntent);
    }
    
    public void initValues(){
    	scroll = (ScrollView)findViewById(R.id.patientNotesScrollView);
    	table = (TableLayout)findViewById(R.id.patientNotesRootLayout);
    	scroll.setScrollbarFadingEnabled(true);
    	
    	Bundle extras = getIntent().getExtras(); 
    	if(extras !=null)
    	{
    		pid = extras.getInt("pid");
    	}
    	
    	pNotes = PatientDAO.getPatientNote(pid);
    	
    	for(int i = 1; i < pNotes.size()+1; i++){
    		TableRow row = new TableRow(this);
    		if(i%2 == 1){
    			row.setBackgroundColor(getResources().getColor(R.color.borderColor));
    		}
    		for(int j = 0; j < 3; j++){	
    			if(j==1){
    				TextView border = new TextView(this);
    				if(i%2==1)
    					border = ViewUtil.createTableBorder(this, R.color.blackBorder);
    				else
    					border = ViewUtil.createTableBorder(this, R.color.borderColor);
    				row.addView(border);
    			}else if(j==0){
    				TextView text = ViewUtil.createTextView(
    						this, 
    						pNotes.get(i-1).getTitle(), 
    						(float).70, 
    						pNotes.get(i-1).getId(),
    						this);
        			row.addView(text);
    			}else if(j==2){
    				TextView text = ViewUtil.createTextView(
    						this, pNotes.get(i-1).getDateString(), (float).30, pNotes.get(i-1).getId());
        			row.addView(text);
    			}
    		}
    		row.setOnClickListener(this);
    		table.addView(row);
		}
    	
    	
	}
	
    public void onResume(){
    	super.onResume();
    	updateNotes();
    }
    
    private void updateNotes(){
    	pNotes = PatientDAO.getPatientNote(pid);
    	if(pNotes.size() > table.getChildCount()-1){
    		System.out.println("If added new Note");
	    	TableRow row = new TableRow(this);
	    	if(pNotes.size()%2 == 1){
    			row.setBackgroundColor(getResources().getColor(R.color.borderColor));
    		}
	    	for(int j = 0; j < 3; j++){	
				if(j==1){
					TextView border = new TextView(this);
					if(pNotes.size()%2==1)
						border = ViewUtil.createTableBorder(this, R.color.blackBorder);
					else
						border = ViewUtil.createTableBorder(this, R.color.borderColor);
					row.addView(border);
				}else if(j==0){
					TextView text = ViewUtil.createTextView(
							this, 
							pNotes.get(pNotes.size()-1).getTitle(), 
							(float).70, 
							pNotes.get(pNotes.size()-1).getId(),
							this);
	    			row.addView(text);
				}else if(j==2){
					TextView text = ViewUtil.createTextView(
							this, pNotes.get(pNotes.size()-1).getDateString(), (float).30, pNotes.get(pNotes.size()-1).getId());
	    			row.addView(text);
				}
			}
			row.setOnClickListener(this);
			table.addView(row);
    	}else{
    		//If updated a note
    		/*if(table.getChildCount()>1){
	    		TableRow lastRow;
	    		lastRow = (TableRow)(table.getChildAt(pNotes.size()-1));
	    		TextView noteDate = (TextView)lastRow.getChildAt(2);
	    		noteDate.setText(pNotes.get(pNotes.size()-1).getDateString());
    		}*/
    	}
    	
    }
    
    //Based on what row is clicked, different data will be loaded into Note.class
	public void onClick(View v) {
		pNotes = PatientDAO.getPatientNote(pid);
		for(int i = 0; i < pNotes.size(); i ++){
			if(v.getId() == pNotes.get(i).getId()){
				Intent patientNotesIntent = new Intent(PatientNotes.this, edu.utdallas.hf.ui.Note.class);
				patientNotesIntent.putExtra("noteId", pNotes.get(i).getId());
				patientNotesIntent.putExtra("text", pNotes.get(i).getText());
				PatientNotes.this.startActivity(patientNotesIntent);
			}
		}
	}

}
