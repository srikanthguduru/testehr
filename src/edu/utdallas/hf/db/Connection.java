package edu.utdallas.hf.db;

/**
 * @(#)Connection.java
 *
 *
 * @author : not by binh vu
 * @version 1.00 2010/10/16
 */

import java.net.*;
import java.io.*;
import android.util.Log;


public class Connection
{
	URL url;
	URLConnection urlConnection;

    public void connect()
    {
    	//Establish a connection to the database's php page
    	try
    	{
    		
	    	url = new URL("http://66.207.167.195/androidtest.php");
			urlConnection = url.openConnection();
			//Tell the php page we're sending "POST" messages
			((HttpURLConnection)urlConnection).setRequestMethod("POST");
			urlConnection.setDoInput(true);
	        urlConnection.setDoOutput(true);
	        Log.i("Connection", "Connection Made");
    	}    
	    catch (Exception ex)
    	{
    		Log.i("Connection", "Failed to connect");
    		//System.out.println("Exception caught:\n" + ex.toString());
    	}
    }

    public String sendMessage(String username, String password)
    {
    	String cmd = "blah";
    	Log.i("Connection", "Sending message");
		
    	String output = "";
    	
		try
		{
			connect();
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


}