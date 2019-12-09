package com.jsap.stepcounter;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import com.jsap.stepcounter.Dialogs.CustomDialog;
import com.jsap.stepcounter.AppContent.SavePrefsApplication;
import android.graphics.*;

public class MainActivity extends Activity
{
	// TODO: Cleanup
	
	Intent mServiceMainIntent;
	WakelockApplication mWakelock;
	SavePrefsApplication mRecords;
	TextView mTextviewStepCount;
	BroadcastReceiver sensorReceiver;
	private int step = 0;
	RelativeLayout mRelativelayoutMain;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		// THEME
		//setTheme(R.style.DarkTheme);
		
		// STATUSBAR THEMING
		Window window = getWindow();
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		window.setStatusBarColor(AppResources.THEME_COLOR_PRIMARY_DARK);
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		initObjects();
		startEvents();
		initViews();
		
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		// Avoid creating new instances of this activity
		intent.addFlags(AppResources.INTENT_FLAG_SINGLE_ACTIVITY);
		
		super.onNewIntent(intent);
	}

	@Override
	protected void onResume() {
		updateCounterView(step);
		checkExtraIntent();
		
		super.onResume();
	}

	long delay;
	@Override
	public void onBackPressed() {
		// Twice tap to quit
		long time = new Date().getTime();
		if (time <= delay) {
			stopRunningComponents();
		} else {
			delay = time + 1_000;
			Toast.makeText(getApplicationContext(), AppResources.STRING_INFO_QUIT_TAP_TWICE, Toast.LENGTH_SHORT).show();
		}
	}

	
	///// Methods /////

	private void initObjects() {
		// Android Views
		mTextviewStepCount = findViewById(R.id.tv_stepcounter);
		
		// SharedPrefs
		mRecords = new SavePrefsApplication(getApplicationContext());
		
		// Service intent
		mServiceMainIntent = new Intent(MainActivity.this, MainService.class);
		
		// Power Manager
		mWakelock = new WakelockApplication(MainActivity.this);
	}
	
	private void startEvents() {	// Start events like Receiver/Listener, Services, etc.
		// Start Main Service
		startService(mServiceMainIntent);
		
		// Start Broadcast Listener
		startBroadcastListener();
		
		// Acquire Wakelock (Partial)
		mWakelock.startWakelock();
	}
	
	private void initViews() {
		updateCounterView(step);
	}
	
	// Listen to broadcast from Step Sensor
	private void startBroadcastListener() {
		sensorReceiver = new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				// Verify received broadcast is local
				String action = intent.getAction();
				if (action.equalsIgnoreCase(AppResources.ACTION_LOCAL_BROADCAST)) {
					step++;
					updateCounterView(step);
				}
			}
		};
		
		IntentFilter intentFilter = new IntentFilter(AppResources.ACTION_LOCAL_BROADCAST);
		
		registerReceiver(sensorReceiver, intentFilter);
	}

	private void updateCounterView(int step) {
		mTextviewStepCount.setText("" + (step + mRecords.getStepCount()));
	}
	
	private void checkExtraIntent() {
		boolean isCalledOnNotif = getIntent().getBooleanExtra("EXTRA_MISC", false);
		if (isCalledOnNotif) {
			Toast.makeText(getApplicationContext(), "Activity started using Pending Intent! Saving old data and restarting app...", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			
		}
	}
	
	private void stopRunningComponents() {
		mWakelock.releaseWakelock();
		stopService(mServiceMainIntent);
		unregisterReceiver(sensorReceiver);
		
		finish();
	}

	public void mOnClickInfo(View v) {
		new CustomDialog(MainActivity.this).show();
	}
	
	public void showMsg(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	}
}
