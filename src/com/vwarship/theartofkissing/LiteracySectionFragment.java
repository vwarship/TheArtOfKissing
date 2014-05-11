package com.vwarship.theartofkissing;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LiteracySectionFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_good, container, false);
		LinearLayout contentView = (LinearLayout)rootView.findViewById(R.id.content_view);
		
		List<Article> articles = ArticleListBuilder.create(getResources().openRawResource(R.raw.literacy_articles));
		
		for (int i=0; i<articles.size(); ++i)
		{
			Article article = articles.get(i);
			
			StringBuilder sb = new StringBuilder();
			sb.append("<h1>").append(i+1).append(" ").append(article.getTitle()==null ? "" : article.getTitle()).append("</h1>").append(article.getText());
			
			TextView tv = new TextView(rootView.getContext());
			tv.setText(Html.fromHtml(sb.toString()));
			tv.setBackgroundResource(R.drawable.literacy_article_background);
			
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
	
}
