package com.zaoqibu.theartofkissing;

import java.util.Locale;

import com.qq.e.appwall.GdtAppwall;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;
import com.zaoqibu.theartofkissing.R;
import com.zaoqibu.theartofkissing.fragment.BenefitSectionFragment;
import com.zaoqibu.theartofkissing.fragment.LiteracySectionFragment;
import com.zaoqibu.theartofkissing.fragment.TrainingSectionFragment;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
	private Context context;
	private Menu optionsMenu;
	private BackgroundSound mBackgroundSound = new BackgroundSound();
	
	public class BackgroundSound 
	{
		private MediaPlayer mPlayer = null;
		
		public void create()
		{
			if (mPlayer == null)
			{
				mPlayer = MediaPlayer.create(MainActivity.this, R.raw.my_heart_will_go_on_love_theme_from_titanic_celine_dion); 
				mPlayer.setLooping(true);
				mPlayer.setVolume(100,100);
			}
		}
		
		public void release()
		{
			if (mPlayer != null)
			{
				mPlayer.stop();
				mPlayer.release();
				mPlayer = null;
			}
		}
		
		public void onOff()
		{
			MenuItem backgroundMusicMenuItem = optionsMenu.findItem(R.id.action_bgmusic);

			if (mPlayer == null)
				create();
			
			if (mPlayer.isPlaying())
			{
				mPlayer.pause();
				backgroundMusicMenuItem.setIcon(R.drawable.ic_action_play);
				
				MobclickAgent.onEvent(context, "pause");
			}
			else
			{
				mPlayer.start();
				backgroundMusicMenuItem.setIcon(R.drawable.ic_action_pause);
				
				MobclickAgent.onEvent(context, "play");
			}
		}
	}
	
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		context = this;
		
		MobclickAgent.openActivityDurationTrack(false);
		MobclickAgent.updateOnlineConfig(this);
		UmengUpdateAgent.update(this);
		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		optionsMenu = menu;
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	    	case R.id.action_appwall:
	    		GdtAppwall wall = new GdtAppwall(MainActivity.this, "1101366311", "9079537215733406649", false);
				wall.doShowAppWall();
				Log.i("debug", "wall........");
	    		return true;
	        case R.id.action_bgmusic:
	        	mBackgroundSound.onOff();
	            return true;
	        case R.id.action_about:
	        	Intent intent = new Intent(MainActivity.this, AboutActivity.class);
	        	startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			switch (position)
			{
			case 0:
				return new BenefitSectionFragment();
			case 1:
				return new TrainingSectionFragment();
			case 2:
				return new LiteracySectionFragment();
			}
			
			return new BenefitSectionFragment();
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mBackgroundSound.release();
	}
	
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
	
}