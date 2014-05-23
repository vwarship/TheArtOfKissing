package com.zaoqibu.theartofkissing.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tencent.stat.StatService;
import com.zaoqibu.theartofkissing.R;
import com.zaoqibu.theartofkissing.activity.ArticleActivity;
import com.zaoqibu.theartofkissing.domain.Article;
import com.zaoqibu.theartofkissing.util.ArticleListBuilder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class TrainingSectionFragment extends ListFragment {
	public static final String TITLE = "title";
	public static final String TEXT = "text";
	
	private List<Article> mArticles;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		mArticles = ArticleListBuilder.create(getResources().openRawResource(R.raw.training_articles));
		
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), 
				getData(), 
				R.layout.listviewwithimage_item, 
				new String[] {TITLE}, 
				new int[] {R.id.title});
		
		setListAdapter(adapter);
	}
	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		
		for (Article article : mArticles)
		{
			Map<String, Object> data = new HashMap<String, Object>();
			data.put(TITLE, article.getTitle());
			datas.add(data);
		}
		
		return datas;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Article article = mArticles.get(position);
		
		Intent intent = new Intent(getActivity(), ArticleActivity.class);
		intent.putExtra(TITLE, article.getTitle());
		intent.putExtra(TEXT, article.getText());
		startActivity(intent);
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
