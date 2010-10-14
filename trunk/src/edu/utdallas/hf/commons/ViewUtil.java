package edu.utdallas.hf.commons;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * @author Jerry Arnold - jxa074000
 */

public class ViewUtil {
	
	/**
	 * Creates an ImageView that is the same size as the image
	 * @param con The context
	 * @param resid The resource Id of the image
	 * @param img The image itself
	 * @return image The ImageView created
	 */
	public static ImageView createImageView(Context con, int resid, Bitmap img){
		ImageView image = new ImageView(con);
		int height = img.getHeight();
		int width = img.getWidth();
		image.setBackgroundResource(resid);
		image.setLayoutParams(
				new TableRow.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		image.setAdjustViewBounds(true);
		image.setMaxHeight(height);
		image.setMaxWidth(width);
		return image;
	}
	
	/**
	 * Creates a TextView with text size of 18, without OnClickListener
	 * @param con The Context
	 * @param txt The TextView's Text
	 * @param weight The space that the TextView takes in the table
	 * @param id The id of the TextView
	 * @return text The TextView itself
	 */
	public static TextView createTextView(Context con, String txt, float weight, int id){
		TextView text = new TextView(con);
		text.setText(txt);
		text.setTextSize(18);
		text.setGravity(Gravity.CENTER);
		text.setLayoutParams(
				new TableRow.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, weight
						)
				);
		text.setId(id);
		return text;
	}
	
	/**
	 * Creates a TextView with text size of 18, with OnClickListener
	 * @param con The Context
	 * @param txt The TextView's Text
	 * @param weight The space that the TextView takes in the table
	 * @param id The id of the TextView
	 * @param listener The OnClickListener for the TextView
	 * @return text The TextView itself
	 */
	public static TextView createTextView(Context con, String txt, float weight, int id, OnClickListener listener){
		TextView text = new TextView(con);
		text.setText(txt);
		text.setTextSize(18);
		text.setGravity(Gravity.CENTER);
		text.setLayoutParams(
				new TableRow.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, weight
						)
				);
		text.setId(id);
		text.setOnClickListener(listener);
		return text;
	}
	
	/**
	 * Creates a TextView to act as a table border
	 * @param con The Context
	 * @param col The color of the table border
	 * @return border the TextView itself
	 */
	public static TextView createTableBorder(Context con, int col){
		TextView border = new TextView(con);
		border.setWidth(1);
		border.setPadding(0, 0, 0, 0);
		border.setGravity(Gravity.CENTER);
		border.setBackgroundColor(col);
		return border;
	}
}
