package edu.utdallas.hf.ui;
/** 
 * As part of the final documentation, Code Comments will be included, as specified by Razo
 * 
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
public class Note extends Activity implements OnClickListener {
	String note = "";
	EditText noteView;
	Button saveButton;
	ScrollView scroll;
	int noteId;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);
        initValues();
    }
    
    public void initValues(){
    	//the save button in note view
    	saveButton = (Button)findViewById(R.id.noteSaveButton);
    	noteView = (EditText)findViewById(R.id.noteTextView);
    	scroll = (ScrollView)findViewById(R.id.noteScrollView);
    	scroll.setScrollbarFadingEnabled(true);
    	
    	Bundle extras = getIntent().getExtras(); 
    	if(extras !=null)
    	{
    		//get the noteId and note content from PatientNotes class
    		noteId = extras.getInt("noteId");
    		note = extras.getString("text");
    	}
    	noteView.setText(note);
    	saveButton.setOnClickListener(this);
    	
	}
	public void onClick(View v) {
		//When the save button is pushed
		//display the confirmation message, saves the note if yes, does nothing other wise
		if(v.getId() == R.id.noteSaveButton){
			note = noteView.getText().toString();
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Are you sure you want to save this note?")
				.setCancelable(false)
				.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int id) {
						PatientDAO.updatePatientNote(noteId, note);
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
