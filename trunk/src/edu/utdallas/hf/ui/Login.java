package edu.utdallas.hf.ui;

import edu.utdallas.hf.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private Button loginButton;
	private Button doctorViewButton;
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
    	doctorViewButton = (Button)findViewById(R.id.goToDoctorView);
    	username = (EditText)findViewById(R.id.nameField);
    	password = (EditText)findViewById(R.id.passField);
    	loginButton.setOnClickListener(this);
    	doctorViewButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.goToDoctorView){
			Intent myIntent = new Intent(Login.this, DoctorView.class);
			Login.this.startActivity(myIntent);
		}else{
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
    
}