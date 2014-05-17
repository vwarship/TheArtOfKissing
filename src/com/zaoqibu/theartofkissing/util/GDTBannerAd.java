package com.zaoqibu.theartofkissing.util;

import android.app.Activity;
import android.widget.RelativeLayout;

import com.qq.e.ads.AdListener;
import com.qq.e.ads.AdRequest;
import com.qq.e.ads.AdSize;
import com.qq.e.ads.AdView;
import com.zaoqibu.theartofkissing.Constants;

public final class GDTBannerAd
{
	//显示广点通的Banner Ad
	public static void show(Activity activity, RelativeLayout bannerAdLayout)
	{
		AdView adv = new AdView(activity, AdSize.BANNER, 
				Constants.GDT_APP_ID, Constants.GDT_BANNERPOS_ID);		
		bannerAdLayout.addView(adv);
		AdRequest adRequest = new AdRequest();
		adRequest.setTestAd(false);
		adRequest.setRefresh(30);
		adv.setAdListener(new AdListener() {
			@Override
			public void onNoAd() {}
			@Override
			public void onAdReceiv() {}
			@Override
			public void onBannerClosed() {}
		});
		adv.fetchAd(adRequest);
	}
}
