package com.jsap.stepcounter.Dialogs;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.jsap.stepcounter.*;

public class CustomDialog extends Dialog implements android.view.View.OnClickListener
{
	public Activity activity;
	public Dialog d;
	public TextView mTextViewChangeLogs, mTextViewVersionName;
	
	public CustomDialog(Activity activity) {
		super(activity);
		this.activity = activity;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super .onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.custom_dialog);
		
		mTextViewChangeLogs = findViewById(R.id.tv_changelogs);
		mTextViewVersionName = findViewById(R.id.tv_versionName);

		mTextViewChangeLogs.setText(AppResources.APP_CHANGELOGS);
		mTextViewVersionName.setText(new AppResources(activity).getVersionName());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId())
		{
			default :
				break;
		}
		
		dismiss();
	}
}
