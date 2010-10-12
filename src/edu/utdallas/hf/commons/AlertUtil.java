package edu.utdallas.hf.commons;

/**
 * @author Jerry Arnold - jxa074000
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertUtil {
	
	//creates an alert message with one choice only
	//when the button is clicked on, dismisses the message
	public static AlertDialog createAlertMessage(Context con, String msg, String btStr){
		AlertDialog.Builder builder = new AlertDialog.Builder(con);
		builder.setMessage(msg)
		       .setCancelable(false)
		       .setNegativeButton(btStr, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.dismiss();
		           }
		       });
		AlertDialog alert = builder.create();
		return alert;
	}
	
	//creates an alert message with yes/no choices
	//for when the user clicks on log out in either doctor view or patient view
	//when clicked on 'yes', finishes the activity
	//when clicked on 'no', dismisses the alert message
	public static AlertDialog createLogoutMessage(final Activity act, Context con, String msg){
		AlertDialog.Builder builder = new AlertDialog.Builder(con);
		builder.setMessage(msg)
			.setCancelable(false)
			.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int id) {
					act.setResult(Activity.RESULT_OK);
					act.finish();
				}
			})
			.setNegativeButton("No", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.dismiss();
		           }
		       });
		AlertDialog alert = builder.create();
		return alert;
	}
}
