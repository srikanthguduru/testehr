package edu.utdallas.hf.ui;
/**
 * @author Joshua Reisenauer
 */

import edu.utdallas.hf.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/*
 * This class is called from within Doctor View and will display a list of all notes taken by doctor.
 * A option to add new notes or delete old notes will be present through buttons.
 */
public class Note extends Activity implements OnClickListener {
	
	
	
	/** Called when the activity is first created. */
	String Note = "";
	Button b1,b2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);
        initValues();
    }
    
    public void initValues(){
    	b1 = (Button)findViewById(R.id.noteButton01);
    	b2 = (Button)findViewById(R.id.noteButton02);
    	b1.setOnClickListener(this);
    	b2.setOnClickListener(this);
    	//tv.setOnClickListener(this);
    	
	}
	
	
	
	

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
