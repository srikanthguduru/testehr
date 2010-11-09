package edu.utdallas.hf.commons;

/** 
 * As part of the final documentation, Code Comments will be included, as specified by Razo
 * 
 * */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertUtil {
	
	/** creates an alert message with one choice only
	 *  when the button is clicked on, dismisses the message
	 *  @param con The contact that the alert message shows up in
	 *  @param msg The message to display in the alert dialog
	 *  @param btString the string on the button that the user clicks on 
	 *  @return The Alert Dialog
	 */
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
	
	/**creates an alert message with yes/no choices
	 * for when the user clicks on log out in either doctor view or patient view
	 * when clicked on 'yes', finishes the activity
	 * when clicked on 'no', dismisses the alert message
	 * 
	 * @param act The Activity that the user is on
	 * @param con the context that the user is on
	 * @param msg the message to show on the alert dialog
	 * @return the Alert dialog
	 */
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
	
	/**
	 * Creates an message to show the user that the activity is going to finish
	 * @param act The activity to finish
	 * @param con the context
	 * @param msg the message to show the user
	 * @return the AlertDialog
	 */
	public static AlertDialog createFinishActivityMessage(final Activity act, Context con, String msg){
		AlertDialog.Builder builder = new AlertDialog.Builder(con);
		builder.setMessage(msg)
			.setCancelable(false)
			.setPositiveButton("OK", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int id) {
					act.setResult(Activity.RESULT_OK);
					act.finish();
				}
			});
		AlertDialog alert = builder.create();
		return alert;
	}
}
