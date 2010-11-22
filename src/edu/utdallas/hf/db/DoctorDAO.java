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
import edu.utdallas.hf.core.Schedule;

public class DoctorDAO {
	public static ArrayList<Schedule> getDoctorSchedule(int id, String date)
	   {
	   	   String cmd = "getDoctorSchedule";
		   ArrayList<Schedule> docScheduleList = new ArrayList<Schedule>();
		   Schedule docSchedule = new Schedule();
		   int size = 20000;
		   URLConnection urlConnection = null;
		   try
		   {
			   urlConnection = Connection.connect();
			   String data = URLEncoder.encode("cmd", "UTF-8") + "=" +
				URLEncoder.encode(cmd, "UTF-8") + "&" +
				URLEncoder.encode("aid", "UTF-8") + "=" +
				URLEncoder.encode(""+id, "UTF-8") + "&" +
			    URLEncoder.encode("date", "UTF-8") + "=" +
				URLEncoder.encode(date, "UTF-8");

			    OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
	            wr.write(data);
	            wr.flush();


	            BufferedReader rd =  new BufferedReader(new InputStreamReader(urlConnection.getInputStream()), size);
	            String buffer;

	            while ((buffer = rd.readLine()) != null)
			    {
	            	Log.i("connection", "buffer is : " + buffer);
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
