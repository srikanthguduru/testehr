package edu.utdallas.hf.ui;

/**
 * @author Jerry Arnold - jxa074000
 */

import edu.utdallas.hf.R;
import edu.utdallas.hf.commons.ViewUtil;
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

public class LabReview extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	String[][] labReviews = new String[20][6];
	Bitmap checkImage;
	int imageWidth;
	int imageHeight;
	Bitmap exImage;
	TableLayout table;
	ScrollView scroll;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.labreview);
        initValues();
    }
    
    public void initValues(){
    	scroll = (ScrollView)findViewById(R.id.labReviewScrollView);
    	checkImage =  BitmapFactory.decodeResource(getResources(),R.drawable.check);
    	exImage = BitmapFactory.decodeResource(getResources(),R.drawable.ex);
    	table = (TableLayout)findViewById(R.id.labReviewRootLayout);
    	scroll.setScrollbarFadingEnabled(true);
    	for(int i = 0; i < 20; i++){
    		TableRow row = new TableRow(this);
    		if(i%2 == 0){
    			row.setBackgroundColor(getResources().getColor(R.color.borderColor));
    		}
    		for(int j = 0; j < 6; j++){
    			labReviews[i][j] = j+":"+i;
    			if(j==2 || j==4 ){
    				TextView border = new TextView(this);
    				if(i%2==0)
    					border = ViewUtil.createTableBorder(this, R.color.blackBorder);
    				else
    					border = ViewUtil.createTableBorder(this, R.color.borderColor);
    				row.addView(border);
    			}else if(j==1){
    				TextView text = ViewUtil.createTextView(
    						this, labReviews[i][j], (float).48, j*100+i);
        			row.addView(text);
    			}else if(j==3){
    				TextView text = ViewUtil.createTextView(
    						this, labReviews[i][j], (float).28, j*100+i);
        			row.addView(text);
    			}else if(j==5){
    				TextView text = ViewUtil.createTextView(
    						this, labReviews[i][j], (float).20, j*100+i);
        			row.addView(text);
    			}else{
    				ImageView image;
    				if(i%2==0)
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