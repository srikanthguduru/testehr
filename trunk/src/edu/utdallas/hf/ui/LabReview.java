package edu.utdallas.hf.ui;

/**
 * @author Jerry Arnold - jxa074000
 */

import edu.utdallas.hf.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class LabReview extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.labreview);
    }
    

	public void onClick(View v) {
		
	}
    
}