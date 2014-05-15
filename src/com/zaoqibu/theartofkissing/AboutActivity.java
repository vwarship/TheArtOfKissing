package com.zaoqibu.theartofkissing;

import com.umeng.analytics.MobclickAgent;
import com.zaoqibu.theartofkissing.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

public class AboutActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle(R.string.action_about);
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
	
	private static final String PAGE_NAME = "About";
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
