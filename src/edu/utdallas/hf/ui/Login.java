package edu.utdallas.hf.ui;

/** 
 * As part of the final documentation, Code Comments will be included, as specified by Razo
 * 
 * */

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import edu.utdallas.hf.R;
import edu.utdallas.hf.commons.AlertUtil;
import edu.utdallas.hf.db.LoginDAO;

public class Login extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private Button loginButton;
	private EditText username;
	private EditText password;
	NumberFormat formatter = new DecimalFormat("#00.##");
	ProgressDialog waiting;
	String message = "";
	
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
    
    public void onResume(){
    	super.onResume();
    	password.setText("");
    	message = "";
    }

	public void onClick(View v) {
		if(v.getId() == R.id.loginButton){
			System.out.println("Login clicked");
			message = LoginDAO.login(
					username.getText().toString().trim(), 
					password.getText().toString().trim());
		    if(message.equals("success")){
				int docId = LoginDAO.getDoctorId(username.getText().toString().trim());
				Intent doctorViewIntent = new Intent(this, DoctorView.class);
				doctorViewIntent.putExtra("did", docId);
				this.startActivity(doctorViewIntent);
			//if the log in failed, display the fail message
			}else if (message.equals("fail")){
				AlertDialog alert = AlertUtil.createAlertMessage(
						this, 
						"Login failed, username or password incorrect.", 
						"OK"
						);
				alert.show();
			} else {
				AlertDialog alert = AlertUtil.createAlertMessage(
						this, 
						"Login failed, check your network connection.", 
						"OK");
				alert.show();
			}
		}
		
	}
    
}