package com.zaoqibu.theartofkissing;

import java.util.ArrayList;
import java.util.List;

import com.umeng.analytics.MobclickAgent;
import com.zaoqibu.theartofkissing.R;
import com.zaoqibu.theartofkissing.fragment.TrainingSectionFragment;
import com.zaoqibu.theartofkissing.util.ResourceBitmapHelper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.TextView;

public class ArticleActivity extends Activity {
	private String title;
	private TextView mText;
	
	private List<Bitmap> bitmapList = new ArrayList<Bitmap>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article);
		
		title = getIntent().getExtras().getCharSequence(TrainingSectionFragment.TITLE).toString();
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle(title);
		
		mText = (TextView)this.findViewById(R.id.article_text);
		
		String html = getIntent().getExtras().getCharSequence(TrainingSectionFragment.TEXT).toString();
		
		CharSequence charSequence = Html.fromHtml(html, new Html.ImageGetter() {
		    @Override
		    public Drawable getDrawable(String source) {
		    	DisplayMetrics metrics = new DisplayMetrics();
		        getWindowManager().getDefaultDisplay().getMetrics(metrics);
		        
		        ResourceBitmapHelper rbHelper = new ResourceBitmapHelper(getResources());
		        BitmapDrawable drawable = rbHelper.getDrawableFromResourceName(source, metrics.widthPixels);
		    	
		        bitmapList.add(drawable.getBitmap());
		    	return drawable;
		    	
//	            Drawable drawable = getResources().getDrawable(getResourcesDrawableId(source));
//	            
//	            DisplayMetrics metrics = new DisplayMetrics();
//	            getWindowManager().getDefaultDisplay().getMetrics(metrics);
//	            
//	            int containerWidth = metrics.widthPixels;
//	            int imageWidth = drawable.getIntrinsicWidth();
//	            double drawableFactor = (double)containerWidth/imageWidth;
//	            
//	            drawable.setBounds(0, 0, 
//	            		(int)(drawable.getIntrinsicWidth()*drawableFactor), 
//	            		(int)(drawable.getIntrinsicHeight()*drawableFactor));
//	            return drawable;
		    }
		}, null);
		
		mText.setText(charSequence);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case android.R.id.home:
			onBackPressed();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		for (Bitmap bitmap : bitmapList)
		{
			if (!bitmap.isRecycled())
			{
				bitmap.recycle();
			}
		}
		
		bitmapList.clear();
		System.gc();
	}
	
	private static final String PAGE_NAME = "How To Article: ";
	public void onResume() {
		super.onResume();
	    MobclickAgent.onPageStart(PAGE_NAME + title); 
		MobclickAgent.onResume(this);
	}
	public void onPause() {
		super.onPause();
	    MobclickAgent.onPageEnd(PAGE_NAME + title); 
		MobclickAgent.onPause(this);
	}

}
