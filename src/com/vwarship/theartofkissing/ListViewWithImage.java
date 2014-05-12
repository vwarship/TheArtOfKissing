package com.vwarship.theartofkissing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vwarship.theartofkissing.R;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class ListViewWithImage extends ListActivity {
	public ListViewWithImage(Context context) {
		super();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SimpleAdapter adapter = new SimpleAdapter(this, 
				getData(), 
				R.layout.listviewwithimage_item, 
				new String[] {"title"}, 
				new int[] {R.id.title});
		
		setListAdapter(adapter);
	}
	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("title", "测试1");
		datas.add(data);
		
		data = new HashMap<String, Object>();
		data.put("title", "测试2");
		datas.add(data);
		
		return datas;
	}
	
}
