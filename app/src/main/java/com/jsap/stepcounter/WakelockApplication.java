package com.jsap.stepcounter;

import android.app.*;
import android.content.*;
import android.os.*;

public class WakelockApplication
{
	Context context;
	
	// Permission in AndroidManifest
	
	PowerManager pm;
	PowerManager.WakeLock wl;
	
	public WakelockApplication(Activity activity) {
		this.context = activity;
		
		pm = (PowerManager) context.getSystemService(context.POWER_SERVICE);
		wl = pm.newWakeLock(pm.PARTIAL_WAKE_LOCK, AppResources.TAG);
	}
	
	public void startWakelock() {
		wl.acquire();
	}
	
	public void releaseWakelock() {
		wl.release();
	}
}
