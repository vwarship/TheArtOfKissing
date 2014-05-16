package com.zaoqibu.theartofkissing.fragment;

import java.util.ArrayList;
import java.util.List;

import com.qq.e.appwall.GdtAppwall;
import com.umeng.analytics.MobclickAgent;
import com.zaoqibu.theartofkissing.Constants;
import com.zaoqibu.theartofkissing.R;
import com.zaoqibu.theartofkissing.activity.BenefitDetailActivity;
import com.zaoqibu.theartofkissing.domain.Article;
import com.zaoqibu.theartofkissing.util.ArticleListBuilder;
import com.zaoqibu.theartofkissing.util.SystemMetadata;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class BenefitSectionFragment extends Fragment {
	private List<Bitmap> bitmapList = new ArrayList<Bitmap>();
	private List<Article> articles;
	
	private Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_good, container, false);
		context = this.getActivity();
		
		articles = ArticleListBuilder.create(getResources().openRawResource(R.raw.benefit_articles));

		GridView gridView = (GridView)rootView.findViewById(R.id.gridView);
		gridView.setAdapter(new BenefitAdapter(context, calcGridItemWidth(), articles));
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Article article = articles.get(position);
				
				if (article.getText().isEmpty())
				{
		    		GdtAppwall wall = new GdtAppwall(context, 
		    				Constants.GDT_APP_ID, Constants.GDT_APPWALLPOS_ID, 
		    				false);
					wall.doShowAppWall();
				}
				else
				{
					Intent intent = new Intent(context, BenefitDetailActivity.class);
					intent.putExtra("article", article);
					startActivity(intent);
				}
			}
		});
		
		return rootView;
	}
	
	private int calcGridItemWidth()
	{
		int colNum = Constants.BENEFIT_GRID_NUMBER;
		
		Resources r = getResources();
		float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 
				8*2 + (colNum-1)*8, 
				r.getDisplayMetrics());
		
		final int screenWidth = SystemMetadata.getScreenWidth(this.getActivity());
		return (int) ( (screenWidth-padding) / colNum);
	}

	@Override
	public void onDestroy() {
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
	
	private static final String PAGE_NAME = "Good";
	public void onResume() {
	    super.onResume();
	    MobclickAgent.onPageStart(PAGE_NAME);
	}
	public void onPause() {
	    super.onPause();
	    MobclickAgent.onPageEnd(PAGE_NAME); 
	}
	
}
