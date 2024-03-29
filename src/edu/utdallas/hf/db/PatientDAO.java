package edu.utdallas.hf.db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
import edu.utdallas.hf.core.Vitals;

public class PatientDAO {

	/**
	 * Gets the list of patients for the logged in doctor
	 * @return the list of patients
	 */
	public static ArrayList<Patient> getPatientList()
    {
    	ArrayList<Patient> patientList = new ArrayList<Patient>();
    	Patient patient = new Patient();
    	String cmd = "patientList";
    	Log.i("Connection", "Sending message");
    	URLConnection urlConnection = null;
		try
		{
			urlConnection = Connection.connect();
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

	/**
	 * Gets the patient's vitals based on the patient id
	 * @param pid the patient's id
	 * @return the list of vitals for the patient with id pid
	 */
    public static ArrayList<Vitals> getPatientVitals(int pid)
    {
    	ArrayList<Vitals> vList = new ArrayList<Vitals>();
    	Vitals patientVitals;
    	String cmd = "patientVitals";
    	Log.i("Connection", "Sending message");
    	URLConnection urlConnection = null;
		try
		{
			urlConnection = Connection.connect();
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
    
    /**
     * Gets the the patient's medication based on the patient's id
     * @param pid the patient's id
     * @return the medication list for the patient with patient id pid
     */
    public static ArrayList<Medication> getPatientMedication(int pid)
    {
    	ArrayList<Medication> mList = new ArrayList<Medication>();
    	Medication medication;
    	String cmd = "medicationList";
    	Log.i("Connection", "Sending message");
    	URLConnection urlConnection = null;
		try
		{
			urlConnection = Connection.connect();
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

    /**
     * Gets the patient's notes based on the patient's id
     * @param pid the patient's id
     * @return the patient's notes
     */
    public static ArrayList<Note> getPatientNote(int pid)
    {
    	ArrayList<Note> pNotes = new ArrayList<Note>();
    	Note notes;

    	String cmd = "patientNotes";
    	Log.i("Connection", "Sending message");
		int size = 12000;
		String regex = "`";
		URLConnection urlConnection = null;
		try
		{
			urlConnection = Connection.connect();
			//Encode the string combination into a url to send to the php page
			String data = URLEncoder.encode("cmd", "UTF-8") + "=" +
				URLEncoder.encode(cmd, "UTF-8") + "&" +
				URLEncoder.encode("pid", "UTF-8") + "=" +
				URLEncoder.encode(""+pid, "UTF-8");

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

    /**
     * Updates a patient's note with the new body msg
     * @param id the patient's id
     * @param msg the new note to be updated
     * @return success if the note is upated
     */
   public static String updatePatientNote(int id, String msg)
   {
	   String cmd = "updatePatientNote";
	   String result = "";
	   URLConnection urlConnection = null;
	   try
	   {
		   urlConnection = Connection.connect();
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

   /**
    * Creates a new note with the title title and body msg
    * @param pid the patient's id
    * @param title the new notes title
    * @param msg the new notes body
    * @return success if the note was created successfully, fail other wise
    */
   public static String createPatientNote(int pid, String title, String msg)
   {
	   String cmd = "createPatientNote";
	   String result = "";

	   URLConnection urlConnection = null;
	   Log.i("Connection", "Pid: "+pid);
	   try
	   {
		   urlConnection = Connection.connect();
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
}
