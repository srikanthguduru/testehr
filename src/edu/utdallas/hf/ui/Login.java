package edu.utdallas.hf.ui;

/** 
 * As part of the final documentation, Code Comments will be included, as specified by Razo
 * 
 * */

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import edu.utdallas.hf.R;
import edu.utdallas.hf.commons.AlertUtil;
import edu.utdallas.hf.db.Connection;
import edu.utdallas.hf.core.Note;

public class Login extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private Button loginButton;
	private Button doctorViewButton;
	private Button test;
	private EditText username;
	private EditText password;
	NumberFormat formatter = new DecimalFormat("#00.##");
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initControls();
    }
    
    
    
    private void initControls(){
    	//when database end complete remove this subset of buttons----------------------
    	doctorViewButton = (Button)findViewById(R.id.goToDoctorView);
    	test = (Button)findViewById(R.id.test);
    	//------------------------------------------------------------------------------
    	
    	loginButton = (Button)findViewById(R.id.loginButton);
    	username = (EditText)findViewById(R.id.nameField);
    	password = (EditText)findViewById(R.id.passField);
    	
    	loginButton.setOnClickListener(this);
    	doctorViewButton.setOnClickListener(this);
    	test.setOnClickListener(this);
    }

	public void onClick(View v) {
		if(v.getId() == R.id.goToDoctorView){
			Intent doctorViewIntent = new Intent(Login.this, DoctorView.class);
			Login.this.startActivity(doctorViewIntent);
		}else if(v.getId() == R.id.loginButton){
			Connection con = new Connection();
			String message = con.login(
					username.getText().toString().trim(), 
					password.getText().toString().trim());
			if(message.equals("success")){
				int docId = con.getDoctorId(username.getText().toString().trim());
				Intent doctorViewIntent = new Intent(Login.this, DoctorView.class);
				doctorViewIntent.putExtra("did", docId);
				Login.this.startActivity(doctorViewIntent);
			}else if (message.equals("fail")){
				AlertDialog alert = AlertUtil.createAlertMessage(
						this, 
						"Login failed, username or password incorrect.", 
						"OK"
						);
				alert.show();
			} else {
				AlertDialog blargh = AlertUtil.createAlertMessage(
						this, 
						"Login failed, check your network connection.", 
						"OK");
				blargh.show();
			}
		}else if(v.getId() == R.id.test){
			Connection con = new Connection();
			String testContent = "";
			ArrayList<Note> patientList = con.getPatientNote(1);
			for(int i =0; i < patientList.size(); i++){
				testContent += "Title: "+patientList.get(i).getTitle()+"\n";
				testContent += "Text: "+patientList.get(i).getText()+"\n";
			}
			AlertDialog blargh = AlertUtil.createAlertMessage(
					this, 
					testContent, 
					"OK");
			blargh.show();
			
		}
	}
    
}