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
	private static final String[] benefits = {
		"<b>美容</b><br><br><img src='benefit_mr'/><br>一个热情的吻会使面部34块肌肉处于紧张状态，使皮肤更光滑，使面部皮肤的血液供应量增加30%，有助于改善面部肌肉组织，起到美容作用。",
		"<b>护齿</b><br><br><img src='benefit_hc'/><br>热吻过程中唾液分泌增加。唾液中所含的微量元素钙和磷，被釉质吸收后可预防龋齿；在接吻过程中，唾液的酸碱度是中性的，也能预防牙齿疾病。唾液中的天然抗生素——酶，则可控制口腔中的细菌数量。唾液中还有有助于伤口愈合的生长基因。",
		"<b>免疫</b><br><br><img src='benefit_my'/><br>我们的唾液含有大量细菌。其中20%的细菌因人而异。在接吻对象的口腔中，一旦加入了他人的细菌，这些微生物就会产生不同反应。因此可以刺激一个人的免疫系统产生特定抗体。",
		"<b>减肥</b><br><br><img src='benefit_jf'/><br>接吻一分钟，可以消耗26卡路里的热量。热吻中男性心跳可提高到每分钟110次，女性心跳可提高到每分钟108次，进而使血液循环加快。这些变化调动起全身的细胞燃料——三磷酸腺苷轻松燃烧脂肪。法式热吻所消耗的热量最多。",
		"<b>止痛</b><br><br><img src='benefit_zt'/><br>接吻越有激情，人体就会分泌越多内啡肽。内啡肽荷尔蒙是一种很好的麻醉剂，一次激吻产生的荷尔蒙能够达到一片止痛药的效果。",
		"<b>减压</b><br><br><img src='benefit_jy'/><br>肾上腺皮质激素还是一种压力激素，接吻既然能够阻止肾上腺皮质激素的形成，所以接吻就能有效地帮你从紧张情绪中平静下来，喜欢接吻的人通常都很乐观，也是因为这个原因。每天接吻三次可以让你24小时都处在浪漫、愉快的氛围中。",
		"<b>健康</b><br><br><img src='benefit_jk'/><br>1、使心血管更加稳定、降低高血压、降低胆固醇。热吻会让人心跳加速，血液流通畅快可提高体内供氧水平。肾上腺皮质激素也是造成高血压、高胆固醇、肌肉萎缩以及失眠的罪魁祸首，而接吻能够阻止肾上腺皮质激素的形成。<br>2、锻炼肺活量。我们常在接吻前深吸一口气，再在接吻过程中慢慢将气呼出，不自觉地让肺泡做了一次深呼吸，改善了肺泡内的氧气供应，锻炼了肺活量。<br>3、降低患胃病、血液病几率。那些经常接吻的人在胃、膀胱以及血液系统方面患病的几率会更小。"
	};
	
	private List<Bitmap> bitmapList = new ArrayList<Bitmap>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_benefit, container, false);
		
		boolean isFirst = true;
		LinearLayout contentView = (LinearLayout)rootView.findViewById(R.id.content_view);
		for (String benefit : benefits)
		{
			TextView tv = new TextView(rootView.getContext());
			//tv.setText(Html.fromHtml(benefit));
			CharSequence charSequence = Html.fromHtml(benefit, new Html.ImageGetter() {
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
