package edu.utdallas.hf.ui;
/** 
 * As part of the final documentation, Code Comments will be included, as specified by Razo
 * 
 * */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import edu.utdallas.hf.R;
import edu.utdallas.hf.db.PatientDAO;

/*
 * This class is called from within Doctor View and will display a list of all notes taken by doctor.
 * A option to add new notes or delete old notes will be present through buttons.
 */
public class NewNote extends Activity implements OnClickListener {
	String note = "";
	EditText noteView;
	EditText noteTitle;
	Button saveButton;
	ScrollView scroll;
	int pid;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newnote);
        initValues();
    }
    
    public void initValues(){
    	saveButton = (Button)findViewById(R.id.newNoteSaveButton);
    	noteTitle = (EditText)findViewById(R.id.newNoteTitle);
    	noteView = (EditText)findViewById(R.id.newNoteBody);
    	scroll = (ScrollView)findViewById(R.id.noteScrollView);
    	scroll.setScrollbarFadingEnabled(true);
    	Bundle extras = getIntent().getExtras(); 
    	if(extras !=null)
    	{
    		//gets the pid from PatientNotes
    		pid = extras.getInt("pid");
    	}
    	
    	
    	saveButton.setOnClickListener(this);
    	
    	
	}
	public void onClick(View v) {
		if(v.getId() == R.id.newNoteSaveButton){
			//when the save button is clicked,
			//ask the user if he/she wants to save the note for sure
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Are you sure you want to save this note?")
				.setCancelable(false)
				.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int id) {
						String title = noteTitle.getText().toString();
						String body = noteView.getText().toString();
						PatientDAO.createPatientNote(pid, title, body);
						setResult(Activity.RESULT_OK);
						finish();
					}
				})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			                dialog.dismiss();
			           }
			       });
			AlertDialog alert = builder.create();
			alert.show();
		}
		
	}

}
