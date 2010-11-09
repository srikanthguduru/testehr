package edu.utdallas.hf.ui;
/** 
 * As part of the final documentation, Code Comments will be included, as specified by Razo
 * 
 * */

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import edu.utdallas.hf.R;
import edu.utdallas.hf.commons.AlertUtil;
import edu.utdallas.hf.db.Connection;

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
    		pid = extras.getInt("pid");
    	}
    	
    	
    	saveButton.setOnClickListener(this);
    	
    	
	}
	public void onClick(View v) {
		if(v.getId() == R.id.newNoteSaveButton){
			Connection con = new Connection();
			String title = noteTitle.getText().toString();
			String body = noteView.getText().toString();
			AlertDialog alert = AlertUtil.createAlertMessage(this, "Title: "+title+"\nBody: "+body, "OK");
			alert.show();
			con.createPatientNote(pid, title, body);
			AlertDialog finish = AlertUtil.createFinishActivityMessage(this, this, "Note saved.");
			finish.show();
		}
		
	}

}
