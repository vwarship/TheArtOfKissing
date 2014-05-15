package com.zaoqibu.theartofkissing.fragment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import com.zaoqibu.theartofkissing.R;
import com.zaoqibu.theartofkissing.domain.Article;
import com.zaoqibu.theartofkissing.util.ResourceBitmapHelper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class BenefitAdapter extends BaseAdapter
{
	private Context context;
	private int calcGridItemWidth;
	
	private List<Article> articles;
	
	public BenefitAdapter(Context context, int calcGridItemWidth, List<Article> articles)
	{
		this.context = context;
		this.calcGridItemWidth = calcGridItemWidth;
		this.articles = articles;
	}

	@Override
	public int getCount() {
		return articles.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		
		View item = null;
		
		if (convertView == null)
		{
			item = layoutInflater.inflate(R.layout.gridview_item, parent, false);
			item.setLayoutParams(new GridView.LayoutParams(calcGridItemWidth, calcGridItemWidth));
		}
		else
		{
			item = convertView;
		}
		
		Article article = articles.get(position);
		
        ResourceBitmapHelper rbHelper = new ResourceBitmapHelper(context.getResources());
        BitmapDrawable drawable = rbHelper.getDrawableFromResourceNameWithNoCompress(article.getIcon(), calcGridItemWidth);

		//Bitmap image = decodeFile(context.getResources().openRawResource(R.drawable.benefit_jf), calcGridItemWidth, calcGridItemWidth);
		
		ImageView imageView = (ImageView)item.findViewById(R.id.itemImage);
		imageView.setImageBitmap(drawable.getBitmap());
		
		TextView textView = (TextView)item.findViewById(R.id.itemText);
		textView.setText(article.getTitle());
		
		return item;
	}

	public static Bitmap decodeFile(InputStream inStream, int WIDTH, int HIGHT) {
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(inStream, null, o);
			final int REQUIRED_WIDTH = WIDTH;
			final int REQUIRED_HIGHT = HIGHT;
			int scale = 1;
			while (o.outWidth / scale / 2 >= REQUIRED_WIDTH
					&& o.outHeight / scale / 2 >= REQUIRED_HIGHT)
				scale *= 2;
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			return BitmapFactory.decodeStream(inStream, null, o2);
	}

}
