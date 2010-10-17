package edu.utdallas.hf.ui;
/**
 * @author Joshua Reisenauer
 */

import edu.utdallas.hf.R;
import edu.utdallas.hf.commons.ViewUtil;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/*
 * This class is called from within Doctor View and will display a list of all notes taken by doctor.
 * A option to add new notes or delete old notes will be present through buttons.
 */
public class DoctorNotes extends Activity implements OnClickListener {
	
	
	
	/** Called when the activity is first created. */
	String[][] doctorNotes = new String[20][6];
	Bitmap checkImage;
	int imageWidth;
	int imageHeight;
	Bitmap exImage;
	TableLayout table;
	ScrollView scroll;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctornotes);
        initValues();
    }
    
    public void initValues(){
    	scroll = (ScrollView)findViewById(R.id.doctorNotesScrollView);
    	checkImage =  BitmapFactory.decodeResource(getResources(),R.drawable.check);
    	exImage = BitmapFactory.decodeResource(getResources(),R.drawable.ex);
    	table = (TableLayout)findViewById(R.id.doctorNotesRootLayout);
    	scroll.setScrollbarFadingEnabled(true);
    	
    	
    	for(int i = 0; i < 20; i++)
    	{
    		TableRow row = new TableRow(this);
    		if(i%2 == 0)
    		{
    			row.setBackgroundColor(getResources().getColor(R.color.borderColor));
    		}
    		for(int j = 0; j < 6; j++)
    		{
    			doctorNotes[i][j] = j+":"+i;
    			
    			if(j==2 || j==4 )
    			{
    				TextView border = new TextView(this);
    				if(i%2==0)
    					border = ViewUtil.createTableBorder(this, R.color.blackBorder);
    				else
    					border = ViewUtil.createTableBorder(this, R.color.borderColor);
    				row.addView(border);
    			}
    			else if(j==1)
    			{
    				TextView text = ViewUtil.createTextView(
    						this, doctorNotes[i][j], (float).48, j*100+i);
        			row.addView(text);
    			}
    			else if(j==3)
    			{
    				TextView text = ViewUtil.createTextView(
    						this, doctorNotes[i][j], (float).28, j*100+i);
        			row.addView(text);
    			}
    			else if(j==5)
    			{
    				TextView text = ViewUtil.createTextView(
    						this, doctorNotes[i][j], (float).20, j*100+i);
        			row.addView(text);
    			}
    			else
    			{
    				ImageView image;
    				if(i%2==0)
    					image = ViewUtil.createImageView(this, R.drawable.check, checkImage);
    				else
    					image = ViewUtil.createImageView(this, R.drawable.ex, exImage);
    				row.setGravity(Gravity.CENTER_VERTICAL);
    				row.addView(image);
    			}
    		}
    		row.setOnClickListener(this);
    		table.addView(row);
		}
    	
    	
	}
	
	
	
	
//Based on what row is clicked, different data will be loaded into Note.class
	public void onClick(View v) {
		
		// TODO Auto-generated method stub
		Intent doctorNotesIntent = new Intent(DoctorNotes.this, Note.class);
		DoctorNotes.this.startActivity(doctorNotesIntent);
	}

}
