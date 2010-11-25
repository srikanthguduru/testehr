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
    	//add the add note menu item
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notesmenu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
    	// when the user chooses add new note
        switch (item.getItemId()) {
        case R.id.newNote:
        	createNewNote();
        	return true;
        default:
        	return super.onOptionsItemSelected(item);
        }
    }
    
    public void createNewNote(){
    	//creates a newnote page when the add note button is clicked on
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
    				//add the note title to the table's first column
    				TextView text = ViewUtil.createTextView(
    						this, 
    						pNotes.get(i-1).getTitle(), 
    						(float).70, 
    						pNotes.get(i-1).getId(),
    						this);
        			row.addView(text);
    			}else if(j==2){
    				//add the creation date of the note to the second column of the table
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
    
    // update notes table after a new note has been added
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
