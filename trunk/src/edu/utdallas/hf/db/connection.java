package edu.utdallas.hf.db;

/**
 * @(#)Connection.java
 *
 *
 * @author
 * @version 1.00 2010/10/16
 */

import javax.net.ssl.HttpsURLConnection;
import java.net.*;
import java.io.*;


public class Connection
{
	URL url;
	HttpsURLConnection urlConnection;
	DataOutputStream outStream;
	DataInputStream inStream;

    public void connect()
    {
    	try
    	{

	    	url = new URL("https://www.northtxconnect.com/game/androidtest.php");
			urlConnection = (HttpsURLConnection)url.openConnection();
			((HttpsURLConnection)urlConnection).setRequestMethod("POST");
			urlConnection.setDoInput(true);
	        urlConnection.setDoOutput(true);
	        urlConnection.setUseCaches(false);
	        //urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        //urlConnection.setRequestProperty("Content-Length", ""+ 50);

    	}
    	catch (Exception ex)
    	{
    		System.out.println("Exception caught:\n" + ex.toString());
    	}
    }

    public String sendMessage(String username, String password)
    {
    	String cmd = "blah";
		//Scanner in = new Scanner(System.in);
		//System.out.println("Input username: ");
		//username = in.nextLine();
		//System.out.println("Input was " + username);

		//System.out.println("Input password: ");
		//password = in.nextLine();
		//System.out.println("Input was " + password);
    	String output = "";


		try
		{
			connect();
			String data = URLEncoder.encode("username", "UTF-8") + "=" +
				URLEncoder.encode(username, "UTF-8") + "&" +
				URLEncoder.encode("password", "UTF-8") + "=" +
				URLEncoder.encode(password, "UTF-8") + "&" +
				URLEncoder.encode("cmd", "UTF-8") + "=" +
				URLEncoder.encode(cmd, "UTF-8");
			//outStream = new DataOutputStream(urlConnection.getOutputStream());
			//inStream = new DataInputStream(urlConnection.getInputStream());

			//outStream.write(data);
			//outStream.flush();
			//outStream.close();

		    OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
            wr.write(data);
            wr.flush();

			String buffer;
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			while ((buffer = rd.readLine()) != null)
			{
				if (buffer.equals("Okay!"))
					output = "success";
				else if (buffer.equals("Epic Fail."))
					output = "fail";
				else
					;
			}
			
			wr.close();
			rd.close();

			outStream.close();
			inStream.close();
			
		}
		catch (Exception ex)
		{
			//System.out.println(ex.toString());
		}
		return output;

    }


}