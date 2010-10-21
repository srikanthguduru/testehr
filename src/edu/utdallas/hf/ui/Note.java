package edu.utdallas.hf.ui;
/**
 * @author Joshua Reisenauer
 */

import edu.utdallas.hf.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ScrollView;

/*
 * This class is called from within Doctor View and will display a list of all notes taken by doctor.
 * A option to add new notes or delete old notes will be present through buttons.
 */
public class Note extends Activity implements OnClickListener {
	String note = "";
	EditText noteView;
	ScrollView scroll;
	int noteId;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);
        initValues();
    }
    
    public void initValues(){
    	noteView = (EditText)findViewById(R.id.noteTextView);
    	scroll = (ScrollView)findViewById(R.id.noteScrollView);
    	scroll.setScrollbarFadingEnabled(true);
    	
    	Bundle extras = getIntent().getExtras(); 
    	if(extras !=null)
    	{
    		noteId = extras.getInt("noteId");
    	}
    	
    	note = "this is a note for the id: "+noteId;
    	noteView.setText(note);
    	
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
