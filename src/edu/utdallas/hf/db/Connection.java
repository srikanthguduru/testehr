package edu.utdallas.hf.db;

/**
 * As part of the final documentation, Code Comments will be included, as specified by Razo
 *Functions for pulling and pushing data streams on to a server side php file.
 * */

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.util.Log;


public class Connection
{
	

    public static URLConnection connect()
    {
    	URL url;
    	URLConnection urlConnection = null;
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
	    return urlConnection;
    }

}