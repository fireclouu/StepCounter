package com.jsap.stepcounter;

import android.app.Service;
import android.content.*;
import android.os.IBinder;
import android.hardware.SensorEvent;
import android.util.Log;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.widget.*;
import com.jsap.stepcounter.AppContent.*;
import android.app.*;

public class MainService extends Service
{
	Context context;
	BroadcastReceiver sensorReceiver;
	AppResources appRes;
	SavePrefsApplication mRecords;
	NotificationApplication mNotification;
	SensorEventListener mSensorListener;
	SensorManager mSensorMgr;
	Sensor mSensor;
	
	private int step = 0;
	
	@Override
	public IBinder onBind(Intent p1) {
		return null;
	}

	@Override
	public void onCreate() {
		// Store context
		this.context = getApplicationContext();

		initObjects();
		startReceiver();
		
		mNotification.showNotification(step, mRecords);
		
		Log.w(appRes.TAG, "SERVICE STARTED!");
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY;
	};
	

	@Override
	public void onDestroy() {
		mSensorMgr.unregisterListener(mSensorListener);
		mNotification.cancelNotification();
		mRecords.appendStepCount(step);
		Log.w(appRes.TAG, "TASK DESTROYED");
		
		super.onDestroy();
	}

	@Override
	public void onTaskRemoved(Intent rootIntent) {
		Log.w(appRes.TAG, "TASK REMOVED");
		super.onTaskRemoved(rootIntent);
	}
	
	///// METHODS /////
	
	private void initObjects() {
		// Notifications
		mNotification = new NotificationApplication(context);
		
		// SharedPreds
		mRecords = new SavePrefsApplication(context);
		
		// Sensors
		mSensorListener = new SensorEventListener() {
			@Override
			public void onSensorChanged(SensorEvent p1)
			{
				// send broadcast to MainActivity with tags included
				step++;
				mNotification.showNotification(step, mRecords);
				sendBroadcast(new Intent(appRes.ACTION_LOCAL_BROADCAST));
			}

			@Override
			public void onAccuracyChanged(Sensor p1, int p2)
			{
				// TODO: Implement this method
			}
		};
		
		mSensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
		mSensor = mSensorMgr.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
		mSensorMgr.registerListener(mSensorListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	@Deprecated
	private void startReceiver() {
		// Broadcast pipe to filter only matching String provided
	}
}
