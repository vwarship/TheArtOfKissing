package com.zaoqibu.theartofkissing.fragment;

import java.util.ArrayList;
import java.util.List;

import com.umeng.analytics.MobclickAgent;
import com.zaoqibu.theartofkissing.R;
import com.zaoqibu.theartofkissing.domain.Article;
import com.zaoqibu.theartofkissing.util.ArticleListBuilder;
import com.zaoqibu.theartofkissing.util.ResourceBitmapHelper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
		gridView.setAdapter(new BenefitAdapter(context, 340, articles));
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//				Intent intent = new Intent(context, ColorDetailActivity.class);
//				intent.putExtra(ColorDetailActivity.ARG_COLORS, colors);
//				intent.putExtra(ColorDetailActivity.ARG_POSITION, position);
//				startActivity(intent);
			}
		});
		
/*		
		boolean isFirst = true;
		LinearLayout contentView = (LinearLayout)rootView.findViewById(R.id.content_view);
		for (Article benefit : mBenefits)
		{
			TextView tv = new TextView(rootView.getContext());
			CharSequence charSequence = Html.fromHtml(benefit.getText(), new Html.ImageGetter() {
			    @Override
			    public Drawable getDrawable(String source) {
			    	DisplayMetrics metrics = new DisplayMetrics();
			        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
			        
			        ResourceBitmapHelper rbHelper = new ResourceBitmapHelper(getResources());
			        BitmapDrawable drawable = rbHelper.getDrawableFromResourceName(source, metrics.widthPixels);
			    	
			        bitmapList.add(drawable.getBitmap());
			    	return drawable;
			    }
			}, null);
			tv.setText(charSequence);
			
			tv.setBackgroundResource(R.drawable.benefit_article_background);
			
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			
			final int margin = 10;
			layoutParams.setMargins(margin, 
					isFirst ? margin : 0, 
					margin, margin);
			
			contentView.addView(tv, layoutParams);
			
			isFirst = false;
		}
*/
		return rootView;
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
