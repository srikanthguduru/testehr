package edu.utdallas.hf.ui;

/** 
 * As part of the final documentation, Code Comments will be included, as specified by Razo
 * 
 * */

import java.util.ArrayList;

import edu.utdallas.hf.R;
import edu.utdallas.hf.commons.ViewUtil;
import edu.utdallas.hf.core.Vitals;
import edu.utdallas.hf.db.Connection;
import android.app.Activity;
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

public class VitalSigns extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	Bitmap checkImage;
	int imageWidth;
	int imageHeight;
	Bitmap exImage;
	TableLayout table;
	ScrollView scroll;
	//connection-----------
	Connection con;
	int pid = 0;//patient id, this is values should be passed from patientView
	ArrayList<Vitals> patientVitals = new ArrayList<Vitals>();
	//----------------------
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vitalsigns);
        initValues();
    }
    
    public void initValues(){
    	Bundle extras = getIntent().getExtras(); 
    	if(extras !=null){
    		pid = extras.getInt("pid");
    	}
    	//connection----------------------
    	con = new Connection();
    	patientVitals = con.getPatientVitals(pid);
    	//--------------------------------
    	scroll = (ScrollView)findViewById(R.id.vitalSignsScrollView);
    	checkImage =  BitmapFactory.decodeResource(getResources(),R.drawable.check);
    	exImage = BitmapFactory.decodeResource(getResources(),R.drawable.ex);
    	table = (TableLayout)findViewById(R.id.vitalSignsRootLayout);
    	scroll.setScrollbarFadingEnabled(true);
    	
    	for(int i = 0; i < patientVitals.size(); i++){
    		TableRow row = new TableRow(this);
    		if(i%2 == 0){
    			row.setBackgroundColor(getResources().getColor(R.color.borderColor));
    		}
    		for(int j = 0; j < 6; j++){
    			if(j==2 || j==4 ){
    				TextView border = new TextView(this);
    				if(i%2==0)
    					border = ViewUtil.createTableBorder(this, R.color.blackBorder);
    				else
    					border = ViewUtil.createTableBorder(this, R.color.borderColor);
    				row.addView(border);
    			}else if(j==1){
    				TextView text = ViewUtil.createTextView(
    						this, patientVitals.get(i).getDateStirng(), (float).48, j*100+i);
        			row.addView(text);
    			}else if(j==3){
    				TextView text = ViewUtil.createTextView(
    						this, patientVitals.get(i).getBmiString(), (float).28, j*100+i);
        			row.addView(text);
    			}else if(j==5){
    				TextView text = ViewUtil.createTextView(
    						this, patientVitals.get(i).getTemperatureString(), (float).20, j*100+i);
        			row.addView(text);
    			}else{
    				ImageView image;
    				if(patientVitals.get(i).getBmi() <= 24.9 && patientVitals.get(i).getBmi() >= 18.5)
    					image = ViewUtil.createImageView(this, R.drawable.check, checkImage);
    				else
    					image = ViewUtil.createImageView(this, R.drawable.ex, exImage);
    				row.setGravity(Gravity.CENTER_VERTICAL);
    				row.addView(image);
    			}
    		}
    		table.addView(row);
		}
	}

	public void onClick(View v) {
		
	}
    
}