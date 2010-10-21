package edu.utdallas.hf;
import edu.utdallas.hf.ui.Login;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class HealthFoundation extends Activity{
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent login = new Intent(HealthFoundation.this, Login.class);
        HealthFoundation.this.startActivity(login);
    }
    
}