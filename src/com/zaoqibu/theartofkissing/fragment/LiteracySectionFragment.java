package com.zaoqibu.theartofkissing.fragment;

import java.util.List;

import com.tencent.stat.StatService;
import com.zaoqibu.theartofkissing.R;
import com.zaoqibu.theartofkissing.domain.Article;
import com.zaoqibu.theartofkissing.util.ArticleListBuilder;
import com.zaoqibu.theartofkissing.util.GDTBannerAd;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LiteracySectionFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_literacy, container, false);
		
		RelativeLayout bannerAdLayout = (RelativeLayout)rootView.findViewById(R.id.gdtBannerAd);
		GDTBannerAd.show(getActivity(), bannerAdLayout);
		
		LinearLayout contentView = (LinearLayout)rootView.findViewById(R.id.content_view);

		List<Article> articles = ArticleListBuilder.create(getResources().openRawResource(R.raw.literacy_articles));
		
		for (int i=0; i<articles.size(); ++i)
		{
			Article article = articles.get(i);
			
			StringBuilder sb = new StringBuilder();
			if (article.getTitle() != null)
			{
				sb.append("<h1>").append(article.getTitle()).append("</h1>");
			}
			sb.append(article.getText());
			
			TextView tv = new TextView(rootView.getContext());
			tv.setText(Html.fromHtml(sb.toString()));
			tv.setBackgroundResource(R.drawable.literacy_article_background);
			tv.setTextSize(18);
			
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			
			final int margin = 10;
			layoutParams.setMargins(margin, 
					i==0 ? margin : 0, 
					margin, margin);
			
			contentView.addView(tv, layoutParams);
		}
		
		return rootView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		StatService.onResume(this.getActivity());
	}
	
	@Override
	public void onPause() {
		super.onPause();
		StatService.onPause(this.getActivity());
	}

}
