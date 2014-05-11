package com.vwarship.theartofkissing;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.LinearLayout;
import android.widget.TextView;

public class BenefitSectionFragment extends Fragment {
	private List<Bitmap> bitmapList = new ArrayList<Bitmap>();
	private List<Article> mBenefits;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_good, container, false);
		
		mBenefits = ArticleListBuilder.create(getResources().openRawResource(R.raw.benefit_articles));

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
			
			//tv.setBackgroundColor(Color.parseColor("#7CCD7C"));	//#FF83FA(粉色)
			tv.setBackgroundResource(R.drawable.benefit_article_background);
			//tv.setPadding(20, 20, 20, 20);
			
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
	
//	public static Bitmap getBitmap(String imageFilePath, int displayWidth, int displayHeight) {
//			BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//			bitmapOptions.inJustDecodeBounds = true;
//			Bitmap bmp = BitmapFactory.decodeFile(imageFilePath, bitmapOptions);
//
//			// 编码后bitmap的宽高,bitmap除以屏幕宽度得到压缩比
//			int widthRatio = (int) FloatMath.ceil(bitmapOptions.outWidth
//			/ (float) displayWidth);
//			int heightRatio = (int) FloatMath.ceil(bitmapOptions.outHeight
//			/ (float) displayHeight);
//
//			if (widthRatio > 1 && heightRatio > 1) {
//			if (widthRatio > heightRatio) {
//			// 压缩到原来的(1/widthRatios)
//			bitmapOptions.inSampleSize = widthRatio;
//			} else {
//			bitmapOptions.inSampleSize = heightRatio;
//			}
//			}
//			bitmapOptions.inJustDecodeBounds = false;
//			bmp = BitmapFactory.decodeFile(imageFilePath, bitmapOptions);
//			return bmp;
//	}	
}
