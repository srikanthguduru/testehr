package edu.utdallas.hf.ui;

/**
 * @author Jerry Arnold - jxa074000
 */

import edu.utdallas.hf.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
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
    	imageWidth = checkImage.getWidth();
    	imageHeight = checkImage.getHeight();
    	for(int i = 0; i < 20; i++){
    		TableRow row = new TableRow(this);
    		if(i%2 == 0){
    			row.setBackgroundColor(getResources().getColor(R.color.borderColor));
    		}
    		for(int j = 0; j < 6; j++){
    			labReviews[i][j] = j+":"+i;
    			if(j==2 || j==4 ){
    				TextView border = new TextView(this);
    				border.setWidth(1);
    				if(i%2==0)
    					border.setBackgroundColor(getResources().getColor(R.color.blackBorder));
    				else
    					border.setBackgroundColor(getResources().getColor(R.color.borderColor));
    				border.setPadding(0, 0, 0, 0);
    				border.setGravity(Gravity.CENTER);
    				row.addView(border);
    			}else if(j==1 || j==3 || j==5){
    				TextView text = new TextView(this);
        			text.setText(labReviews[i][j]);
        			text.setTextSize(18);
        			text.setGravity(Gravity.CENTER);
        			text.setLayoutParams(
        					new TableRow.LayoutParams(
        							LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, (float).32
        							)
        					);
        			text.setId(j*100+i);
        			text.setOnClickListener(this);
        			row.addView(text);
    			}else{
    				ImageView image = new ImageView(this);
    				if(i%2==0)
    					image.setBackgroundResource(R.drawable.check);
    				else
    					image.setBackgroundResource(R.drawable.ex);
    				image.setLayoutParams(
    						new TableRow.LayoutParams(
    								LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    				image.setAdjustViewBounds(true);
    				image.setMaxHeight(imageHeight);
    				image.setMaxWidth(imageWidth);
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