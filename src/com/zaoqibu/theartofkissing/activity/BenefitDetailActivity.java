package com.zaoqibu.theartofkissing.activity;

import com.umeng.analytics.MobclickAgent;
import com.zaoqibu.theartofkissing.R;
import com.zaoqibu.theartofkissing.domain.Article;
import com.zaoqibu.theartofkissing.util.ResourceBitmapHelper;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.TextView;

public class BenefitDetailActivity extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_benefit_detail);
		
		Article article = (Article)getIntent().getExtras().get("article");
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle(article.getTitle());

		TextView tv = (TextView)findViewById(R.id.benefitDetailText);
		CharSequence charSequence = Html.fromHtml(article.getText(), new Html.ImageGetter() {
		    @Override
		    public Drawable getDrawable(String source) {
		    	DisplayMetrics metrics = new DisplayMetrics();
		        getWindowManager().getDefaultDisplay().getMetrics(metrics);
		        
		        ResourceBitmapHelper rbHelper = new ResourceBitmapHelper(getResources());
		        BitmapDrawable drawable = rbHelper.getDrawableFromResourceName(source, metrics.widthPixels);
		    	
		    	return drawable;
		    }
		}, null);
		tv.setText(charSequence);
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
	
	private static final String PAGE_NAME = "GoodDetail";
	public void onResume() {
		super.onResume();
	    MobclickAgent.onPageStart(PAGE_NAME);
		MobclickAgent.onResume(this);
	}
	public void onPause() {
		super.onPause();
	    MobclickAgent.onPageEnd(PAGE_NAME); 
		MobclickAgent.onPause(this);
	}

}
