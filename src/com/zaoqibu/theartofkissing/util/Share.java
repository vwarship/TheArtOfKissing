package com.zaoqibu.theartofkissing.util;

import com.tencent.stat.StatService;
import com.zaoqibu.theartofkissing.R;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Share
{
	public static void share(Context context) 
	{
		String title = context.getResources().getString(R.string.app_name);
		String text = "我正在使用早起步开发的应用【接吻的艺术】，让我们一起玩、学习和成长。";

		try
		{
	    	Intent intent = new Intent(Intent.ACTION_SEND);
		    intent.setType("text/plain");
		    intent.putExtra(Intent.EXTRA_SUBJECT, title);
		    intent.putExtra(Intent.EXTRA_TEXT, text);
		    context.startActivity(Intent.createChooser(intent, title));
		    
		    StatService.trackCustomEvent(context, "share", "true");
	    }
	    catch (ActivityNotFoundException e)
	    {
	    	Toast.makeText(context, "您的手机上没有可分享的应用。", Toast.LENGTH_SHORT).show();
	    }
	}
	
}
