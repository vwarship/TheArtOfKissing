package com.vwarship.theartofkissing;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

public class AboutActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		getActionBar().hide();
		
		TextView aboutTitle = (TextView)this.findViewById(R.id.about_title);
		aboutTitle.setBackgroundColor(Color.LTGRAY);
		
		TextView website = (TextView)this.findViewById(R.id.website);
		website.setAutoLinkMask(Linkify.ALL); 
		website.setMovementMethod(LinkMovementMethod.getInstance());
	}
}
