package edu.utdallas.hf.ui;

/**
 * @author Jerry Arnold - jxa074000
 */

import edu.utdallas.hf.R;
import edu.utdallas.hf.commons.AlertUtil;
import edu.utdallas.hf.db.Connection;
import android.app.Activity;
import android.app.AlertDialog;
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
	private Button patientViewButton;
	private EditText username;
	private EditText password;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initControls();
    }
    
    
    
    private void initControls(){
    	//when database end complete remove this subset of buttons----------------------
    	doctorViewButton = (Button)findViewById(R.id.goToDoctorView);
    	patientViewButton = (Button)findViewById(R.id.goToPatientView);
    	//------------------------------------------------------------------------------
    	
    	loginButton = (Button)findViewById(R.id.loginButton);
    	username = (EditText)findViewById(R.id.nameField);
    	password = (EditText)findViewById(R.id.passField);
    	
    	loginButton.setOnClickListener(this);
    	doctorViewButton.setOnClickListener(this);
    	patientViewButton.setOnClickListener(this);
    }

	public void onClick(View v) {
		if(v.getId() == R.id.goToDoctorView){
			Intent doctorViewIntent = new Intent(Login.this, DoctorView.class);
			Login.this.startActivity(doctorViewIntent);
		}else if(v.getId() == R.id.goToPatientView){
			Intent patientViewIntent = new Intent(Login.this, PatientView.class);
			Login.this.startActivity(patientViewIntent);
		}else if(v.getId() == R.id.loginButton){
			Connection con = new Connection();
			String message = con.sendMessage(username.getText().toString(), password.getText().toString());
			if(message.equals("success")){
				Intent doctorViewIntent = new Intent(Login.this, DoctorView.class);
				Login.this.startActivity(doctorViewIntent);
			}else if (message.equals("fail")){
				AlertDialog alert = AlertUtil.createAlertMessage(
						this, 
						"Login Failed", 
						"OK"
						);
				alert.show();
			} else {
				AlertDialog blargh = AlertUtil.createAlertMessage(this, 
						"Login Failed", "OK");
				blargh.show();
			}
		}
	}
    
}