package edu.utdallas.hf;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class HealthFoundation extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private Button loginButton;
	private EditText username;
	private EditText password;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initControls();
    }
    
    private void initControls(){
    	loginButton = (Button)findViewById(R.id.loginButton);
    	username = (EditText)findViewById(R.id.nameField);
    	password = (EditText)findViewById(R.id.passField);
    	loginButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Username: "+username.getText()+"\nPassword: "+password.getText())
		       .setCancelable(false)
		       .setNegativeButton("OK", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.dismiss();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
    
}