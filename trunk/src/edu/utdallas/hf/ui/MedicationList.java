package edu.utdallas.hf.ui;

/**
 * @author Jerry Arnold - jxa074000
 */

import edu.utdallas.hf.R;
import edu.utdallas.hf.commons.AlertUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MedicationList extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	String[][] medications = new String[20][3];
	TableLayout table;
	ScrollView scrollView;
	TableRow row;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicationlist);
        scrollView = (ScrollView)findViewById(R.id.medicationListScrollView);
        scrollView.setScrollbarFadingEnabled(true);
        initValues();
    }
    
    public void initValues(){
    	table = (TableLayout)findViewById(R.id.medicationListRootLayout);
    	for(int i =0; i < 20; i++){
    		row = new TableRow(this);
    		if(i%2 == 0){
    			row.setBackgroundColor(getResources().getColor(R.color.borderColor));
    		}
    		for(int j=0; j<3; j++){
    			medications[i][j] = j+":"+i;
    			if(j==1){
    				TextView border = new TextView(this);
    				border.setWidth(1);
    				if(i%2==0)
    					border.setBackgroundColor(getResources().getColor(R.color.blackBorder));
    				else
    					border.setBackgroundColor(getResources().getColor(R.color.borderColor));
    				border.setPadding(0, 0, 0, 0);
    				border.setGravity(Gravity.CENTER);
    				row.addView(border);
    			}else{
    				TextView text = new TextView(this);
        			text.setText(medications[i][j]);
        			text.setTextSize(18);
        			text.setGravity(Gravity.CENTER);
        			text.setLayoutParams(
        					new TableRow.LayoutParams(
        							LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, (float).5
        							)
        					);
        			text.setId(j*100+i);
        			text.setOnClickListener(this);
        			row.addView(text);
    			}
    		}
    		table.addView(row);
    	}
    }
    

	public void onClick(View v) {
		for(int i =0; i < 20; i++){
			for(int j=0; j<3; j++){
				if(v.getId()==(j*100)+i){
					AlertDialog msg = AlertUtil.createAlertMessage(
							this, "You clicked on: "+j+":"+i, "OK");
					msg.show();
				}
			}
		}
	}
    
}