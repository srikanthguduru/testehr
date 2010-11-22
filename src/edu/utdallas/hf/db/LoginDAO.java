package edu.utdallas.hf.db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.util.Log;

public class LoginDAO {
	
	/**
	 * Logs in the user with the given username and password,
	 * 
	 * @param username
	 * @param password
	 * @return pass or fail based on the log in status
	 */
	public static String login(String username, String password){
    	String cmd = "login";
    	Log.i("Connection", "Sending message");
    	URLConnection urlConnection = null;
    	String output = "";

		try
		{
			urlConnection = Connection.connect();
			Log.i("Connection", "Encoding message: Username was: " + username + " and password was: " + password);
			//Encode the string combination into a url to send to the php page
			String data = URLEncoder.encode("username", "UTF-8") + "=" +
				URLEncoder.encode(username, "UTF-8") + "&" +
				URLEncoder.encode("password", "UTF-8") + "=" +
				URLEncoder.encode(password, "UTF-8") + "&" +
				URLEncoder.encode("cmd", "UTF-8") + "=" +
				URLEncoder.encode(cmd, "UTF-8");

			Log.i("Connection", "Sending message: " + data);

		    OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
            wr.write(data);
            wr.flush();
            Log.i("Connection", "Sending message to web");
			String buffer;

			BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			while ((buffer = rd.readLine()) != null)
			{
				Log.i("Connection", "Retrieving message buffer is " + buffer);
				if (buffer.equals("Pass."))
					output = "success";
				else if (buffer.equals("Fail."))
					output = "fail";
				else
					;
			}

			wr.close();
			rd.close();

		}
		catch (Exception ex)
		{
			Log.i("Connection", "Exception: " + ex);
			//System.out.println(ex.toString());
		}
		return output;
    }
	
	/**
	 * Returns the doctor ID based on the given login name
	 * @param username the logged in user's username
	 * @return the doctor ID
	 */
	public static int getDoctorId(String username)
    {
    	int id = 0;
    	String cmd = "hasLoggedIn";
    	Log.i("Connection", "UserName in getDoctorId: "+username); 
    	URLConnection urlConnection = null;
    	try
    	{
    		urlConnection = Connection.connect();
    		String data = URLEncoder.encode("cmd", "UTF-8") + "=" +
    				URLEncoder.encode(cmd, "UTF-8") + "&" +
    				URLEncoder.encode("username", "UTF-8") + "=" +
    				URLEncoder.encode(username, "UTF-8");

    		OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
            wr.write(data);
            wr.flush();
            Log.i("Connection", "Sending message to web");
			String buffer;

			BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			while ((buffer = rd.readLine()) != null)
			{
				Log.i("Connection", "Retrieving message buffer is " + buffer);
				id = Integer.parseInt(buffer);
			}

			wr.close();
			rd.close();

		}
		catch (Exception ex)
		{
			Log.i("Connection", "Exception: " + ex);
			//System.out.println(ex.toString());
		}
    	return id;
    }
}
