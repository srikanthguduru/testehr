package edu.utdallas.hf;
import edu.utdallas.hf.ui.Login;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class HealthFoundation extends Activity{
    /** 
     * As part of the final documentation, Code Comments will be included, as specified by Razo
     * Creates a new intent, intents are the equivalent of a new frame within the application.
     * */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //starts the login activity when the app first opens
        Intent login = new Intent(HealthFoundation.this, Login.class);
        HealthFoundation.this.startActivity(login);
    }
    
    public void onResume(){
    	super.onResume();
    	Intent login = new Intent(HealthFoundation.this, Login.class);
        HealthFoundation.this.startActivity(login);
    }
    
}