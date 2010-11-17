package edu.utdallas.hf.db;

/**
 * As part of the final documentation, Code Comments will be included, as specified by Razo
 *Functions for pulling and pushing data streams on to a server side php file.
 * */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.util.Log;
import edu.utdallas.hf.commons.DateUtil;
import edu.utdallas.hf.core.Medication;
import edu.utdallas.hf.core.Note;
import edu.utdallas.hf.core.Patient;
import edu.utdallas.hf.core.Schedule;
import edu.utdallas.hf.core.Vitals;


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


    public int getDoctorId(String username)
    {
    	int id = 0;
    	String cmd = "hasLoggedIn";
    	try
    	{
    		connect();
    		String data = URLEncoder.encode("username", "UTF-8") + "=" +
    				URLEncoder.encode(username, "UTF-8") + "&" +
    				URLEncoder.encode("cmd", "UTF-8") + "=" +
    				URLEncoder.encode(cmd, "UTF-8");

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

    public String login(String username, String password)
    {
    	String cmd = "login";
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





    public ArrayList<Patient> getPatientList()
    {
    	ArrayList<Patient> patientList = new ArrayList<Patient>();
    	Patient patient = new Patient();
    	String cmd = "patientList";
    	Log.i("Connection", "Sending message");

		try
		{
			connect();
			//Encode the string combination into a url to send to the php page
			String data =
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
				String[] bufferString = buffer.split(",");
				Log.i("Connection", "DOB: "+bufferString[2]);
				Calendar patientDob = new GregorianCalendar();
				//set the calendar from the date string from database
				patientDob = DateUtil.parseDateString(bufferString[2]);
				//create new patient with the date from database query
				patient = new Patient(bufferString[0],
									  bufferString[1],
									  patientDob,
									  Integer.parseInt(bufferString[3]));
				patientList.add(patient);
			}

			wr.close();
			rd.close();

		}
		catch (Exception ex)
		{
			Log.i("Connection", "Exception: " + ex);
			//System.out.println(ex.toString());
		}
		return patientList;

    }


    public ArrayList<Vitals> getPatientVitals(int pid)
    {
    	ArrayList<Vitals> vList = new ArrayList<Vitals>();
    	Vitals patientVitals;
    	String cmd = "patientVitals";
    	Log.i("Connection", "Sending message");

		try
		{
			connect();
			//Encode the string combination into a url to send to the php page
			String data = URLEncoder.encode("pid", "UTF-8") + "=" +
				URLEncoder.encode(""+pid, "UTF-8") + "&" +
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
				String[] bufferString = buffer.split(",");
				Calendar vitalDate = new GregorianCalendar();
				vitalDate = DateUtil.parseDateString(bufferString[1]);
				patientVitals = new Vitals(Integer.parseInt(bufferString[0]),
											pid,
											vitalDate,
											Float.parseFloat(bufferString[2]),
											Float.parseFloat(bufferString[3]));
				vList.add(patientVitals);
			}

			wr.close();
			rd.close();

		}
		catch (Exception ex)
		{
			Log.i("Connection", "Exception: " + ex);
			//System.out.println(ex.toString());
		}
		return vList;

    }

    public ArrayList<Medication> getPatientMedication(int pid)
    {
    	ArrayList<Medication> mList = new ArrayList<Medication>();
    	Medication medication;
    	String cmd = "medicationList";
    	Log.i("Connection", "Sending message");

		try
		{
			connect();
			//Encode the string combination into a url to send to the php page
			String data = URLEncoder.encode("pid", "UTF-8") + "=" +
				URLEncoder.encode(""+pid, "UTF-8") + "&" +
				URLEncoder.encode("cmd", "UTF-8") + "=" +
				URLEncoder.encode(cmd, "UTF-8");

			Log.i("Connection", "Sending message: " + data);
			
		    OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
            wr.write(data);
            wr.flush();
            Log.i("Connection", "Sending message to web");
			String buffer;
			//load messages into buffer and parse
			BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			while ((buffer = rd.readLine()) != null)
			{

				Log.i("Connection", "Retrieving message buffer is " + buffer);
				String[] bufferString = buffer.split(",");

				boolean active = false;
				int act = Integer.parseInt(bufferString[5]);
				if(act == 1) active = true;
				else if (act == 0) active = false;
				Log.e("Connection", "Drug: "+bufferString[2]);
				
				if (bufferString[3] == null || bufferString[3].equals(""))
					bufferString[3] = "0";
				
				medication = new Medication(
						Integer.parseInt(bufferString[0]),
						Integer.parseInt(bufferString[1]),
						bufferString[2],
						Integer.parseInt(bufferString[3]),
						Integer.parseInt(bufferString[4]),
						active);
				mList.add(medication);
			}

			wr.close();
			rd.close();

		}
		catch (Exception ex)
		{
			Log.i("Connection", "Exception: " + ex);
			//System.out.println(ex.toString());
		}
		return mList;

    }

    public ArrayList<Note> getPatientNote(int pid)
    {
    	ArrayList<Note> pNotes = new ArrayList<Note>();
    	Note notes;

    	String cmd = "patientNotes";
    	Log.i("Connection", "Sending message");
		int size = 12000;
		String regex = "`";
		try
		{
			connect();
			//Encode the string combination into a url to send to the php page
			String data = URLEncoder.encode("pid", "UTF-8") + "=" +
				URLEncoder.encode(""+pid, "UTF-8") + "&" +
				URLEncoder.encode("cmd", "UTF-8") + "=" +
				URLEncoder.encode(cmd, "UTF-8");

			Log.i("Connection", "Sending message: " + data);

		    OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
            wr.write(data);
            wr.flush();
            Log.i("Connection", "Sending message to web");
			String buffer;

			BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()), size);
			while ((buffer = rd.readLine()) != null)
			{

				Log.i("Connection", "Retrieving message buffer is " + buffer);
				Log.i("Connection", "My regex is " + regex);
				String[] bufferString = buffer.split(regex);

				//0 >> 3
				//Log.e("Connection", "Drug: "+bufferString[2]);
				Calendar noteDate = new GregorianCalendar();
				noteDate = DateUtil.parseDateString(bufferString[3]);
				notes = new Note(Integer.parseInt(bufferString[0]),
						bufferString[1],
						bufferString[2],
						noteDate
						);
				pNotes.add(notes);
			}

			wr.close();
			rd.close();


		}
		catch (Exception blah)
		{
			Log.i("Connection", "Exception: " + blah);
		}
		return pNotes;
   }

   public String updatePatientNote(int id, String msg)
   {
	   String cmd = "updatePatientNote";
	   String result = "";
	   try
	   {
		   connect();
		   String data = URLEncoder.encode("cmd", "UTF-8") + "=" +
			URLEncoder.encode(cmd, "UTF-8") + "&" +
			URLEncoder.encode("msg", "UTF-8") + "=" +
			URLEncoder.encode(msg, "UTF-8") + "&" +
			URLEncoder.encode("id", "UTF-8") + "=" +
			URLEncoder.encode(""+id, "UTF-8");

		   OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
           wr.write(data);
           wr.flush();


           BufferedReader rd =  new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
           String buffer;

           while ((buffer = rd.readLine()) != null)
		   {
        	   if (buffer.equals("Hi"))
        	   		result = "Success";
        	   //else if (buffer.equals("Fail"))
        		 //   result = "Fail";
        	   else
        		   ;
		   }

           wr.close();
           rd.close();

	   }
	   catch (Exception blah)
	   {
	   		Log.i("Connection", "Exception: " + blah);
	   }
	   return result;
   }

   //creates new patient in database
   public String createPatientNote(int pid, String title, String msg)
   {
	   String cmd = "createPatientNote";
	   String result = "";

	   try
	   {
		   connect();
		   String data = URLEncoder.encode("cmd", "UTF-8") + "=" +
			URLEncoder.encode(cmd, "UTF-8") + "&" +
			URLEncoder.encode("pid", "UTF-8") + "=" +
			URLEncoder.encode(""+pid, "UTF-8") + "&" +
		    URLEncoder.encode("title", "UTF-8") + "=" +
			URLEncoder.encode(title, "UTF-8") + "&" +
			URLEncoder.encode("msg", "UTF-8") + "=" +
			URLEncoder.encode(msg, "UTF-8");;

		    OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
            wr.write(data);
            wr.flush();


            BufferedReader rd =  new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String buffer;

            while ((buffer = rd.readLine()) != null)
		    {
       	        if (buffer.equals("Success"))
       	        	result = "Success";
       	        else if (buffer.equals("Fail"))
       	        	result = "Fail";
       	        else
       	        	;
		   }
	   }
	   catch (Exception blah)
	   {
		   Log.i("Connection", "Exception: " + blah);
	   }

	   return result;

   }

   public ArrayList<Schedule> getDoctorSchedule(int id, String date)
   {
   	   String cmd = "getDoctorSchedule";
	   ArrayList<Schedule> docScheduleList = new ArrayList<Schedule>();
	   Schedule docSchedule = new Schedule();
	   try
	   {
		   connect();
		   String data = URLEncoder.encode("cmd", "UTF-8") + "=" +
			URLEncoder.encode(cmd, "UTF-8") + "&" +
			URLEncoder.encode("aid", "UTF-8") + "=" +
			URLEncoder.encode(""+id, "UTF-8") + "&" +
		    URLEncoder.encode("date", "UTF-8") + "=" +
			URLEncoder.encode(date+"%", "UTF-8");

		    OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
            wr.write(data);
            wr.flush();


            BufferedReader rd =  new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String buffer;

            while ((buffer = rd.readLine()) != null)
		    {
		    	//This arraylist holds a title or a name at every even index and a datetime at every odd index
		    	Calendar date1 = new GregorianCalendar();
		    	String[] bufferString = buffer.split(",");
		    	String event = bufferString[0];
				date1 = DateUtil.parseDateTimeString(bufferString[1]);
				if (bufferString[0].equals("Office Visit")){
					event = bufferString[2] +" "+ bufferString[3];
				}
				docSchedule = new Schedule(id, date1, event);
				docScheduleList.add(docSchedule);
		    }
	   }
	   catch (Exception blah)
	   {
		   Log.i("Connection", "Exception: " + blah);
	   }

	   return docScheduleList;
   }



}