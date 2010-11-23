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
	//private Button doctorViewButton;
	//private Button test;
	private EditText username;
	private EditText password;
	NumberFormat formatter = new DecimalFormat("#00.##");
	ProgressDialog waiting;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initControls();
    }
    
    
    
    private void initControls(){
    	/*//when database end complete remove this subset of buttons----------------------
    	doctorViewButton = (Button)findViewById(R.id.goToDoctorView);
    	test = (Button)findViewById(R.id.test);
    	//------------------------------------------------------------------------------
    	 */    	
    	loginButton = (Button)findViewById(R.id.loginButton);
    	username = (EditText)findViewById(R.id.nameField);
    	password = (EditText)findViewById(R.id.passField);
    	
    	loginButton.setOnClickListener(this);
    	waiting = ProgressDialog.show(this, "", 
                "Loading. Please wait...", true);
    	//doctorViewButton.setOnClickListener(this);
    	//test.setOnClickListener(this);
    }
    
    public void onResume(){
    	super.onResume();
    	password.setText("");
    	waiting.dismiss();
    }

	public void onClick(View v) {
		if(v.getId() == R.id.loginButton){
			System.out.println("Login clicked");
			waiting = ProgressDialog.show(this, "", 
	                "Loading. Please wait...", true);
			LoginThread searchThread = new LoginThread();
		    searchThread.start();
		}
		
	}
	
	private class LoginThread extends Thread {
		@Override
        public void run() {         
			String message = LoginDAO.login(
					username.getText().toString().trim(), 
					password.getText().toString().trim());
			if(message.equals("success")){
				int docId = LoginDAO.getDoctorId(username.getText().toString().trim());
				Intent doctorViewIntent = new Intent(Login.this, DoctorView.class);
				doctorViewIntent.putExtra("did", docId);
				Login.this.startActivity(doctorViewIntent);
			}else if (message.equals("fail")){
				waiting.dismiss();
				AlertDialog alert = AlertUtil.createAlertMessage(
						Login.this, 
						"Login failed, username or password incorrect.", 
						"OK"
						);
				alert.show();
			} else {
				waiting.dismiss();
				AlertDialog blargh = AlertUtil.createAlertMessage(
						Login.this, 
						"Login failed, check your network connection.", 
						"OK");
				blargh.show();
			}
        }
    }
    
}