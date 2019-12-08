package com.jsap.stepcounter;

import android.app.*;
import android.content.pm.*;
import android.content.*;

public class AppResources
{
	public static final String TAG = "FC_STEP_COUNTER"; // FC = fireclouu
	public static final String ACTION_LOCAL_BROADCAST = TAG + "_LOCAL_BROADCAST";
	
	public static final String EXTRA_STEP_COUNT = "STEP_COUNT";
	
	public static final int INTENT_FLAG_SINGLE_ACTIVITY = Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP;
	
	public static final int NULL_POINTER = -1;
	
	public static final int EXIT_NORMAL = 0;
	public static final int EXIT_LOW_MEMORY = 1;
	
	public static final String PREF_NAME_MAIN = "mainPrefs";
	
	public static final String ITEM_EXIT_STATUS = "exit_status";
	public static final String ITEM_TEMPORARY_STEP_COUNT = "pre_step";
	public static final String ITEM_SAVED_STEP_COUNTER = "total_step_counter";
	public static final String ITEM_WARN_FLAG = "warn_flag";
	
	public static final String STRING_INFO_QUIT_TAP_TWICE = "Tap again to quit";
	
	// Machine learning implementation maybe implemented
	public static final String STATUS_IDLE = "Idle";
	public static final String STATUS_WALKING = "Walking";
	public static final String STATUS_RUNNING = "Running"; // NARUTO DASH
	public static final String STATUS_RESTING = "Resting..";
	public static final String STATUS_UNKNOWN = "Unknown";
	
	public static final int THEME_COLOR_PRIMARY = 0xFF_AB_45_87;
	public static final int THEME_COLOR_PRIMARY_DARK = 0xFF_C6_00_FF;
	
	public static final String APP_CHANGELOGS_RAW_URL1 = "https://imgbin.com/png/pFu6q5pC/foot-gait-analysis-computer-icons-nail-png";
	
	public static final String APP_CHANGELOGS = 
		"Changelogs:" + "\n" +
		"------------------------------" + "\n" +
		"v0.01" + "\n" +
		"- Initial build" + "\n" +
		"- free icon provided (" + APP_CHANGELOGS_RAW_URL1 + ")" + "\n" +
		"------------------------------" + "\n" +
		"v0.02" + "\n" +
		"- Removed appcompat-v4 dependency to reduce application size up to ~90%" + "\n" +
		"- Added persistent notification" + "\n" +
		"- optimizations" + "\n" +
		"------------------------------" + "\n" +
		"v0.03" + "\n" +
		"- request for wakelock added to make app run even in lock screen" + "\n" +
		"- organized components to its respected class" + "\n" +
		"- more optimization and fixes" + "\n" +
		"------------------------------" + "\n" +
		"v0.04" + "\n" +
		"- added status bar app primary color" + "\n" +
		"- Fix overridden activity stacks, also fixes notification's content intent (and I'm very happy for this fix!)" + "\n" +
		"- code neatly organized, another optimization and removal of unused variables" + "\n" +
		"------------------------------" + "\n" +
		"\n" +
		"------------------------------" + "\n" +
		"v0.05" + "\n" +
		"- Overall reworks and fixes to memory leaks introduced in main launcher" + "\n" +
		"- Integrate sensor listener to service for better background handling" + "\n" +
		"- another fix attempt to notification pending intent activity" + "\n" +
		"- remove redundant receiver calls" + "\n" +
		"------------------------------" + "\n" +
		"Future implementations" + "\n" +
		"----------" + "\n" +
		"- Animation" + "\n" +
		"- AI implementations" + "\n" +
		"- More user-friendly UI" + "\n" +
		"- Less power-consumption services" + "\n" +
		"- Optimizations" + "\n" +
		"\n" +
		"This is my practice app :) , I always maintain it almost everyday, feature implementations are added whenever I had some great ideas I think will work on this app!" + "\n" +
		"jasper_aquino";
		
	private Activity a;
	
	public AppResources(Activity a) {
		this.a = a;
	}
	
	
	public String getVersionName() {
		try
		{
			return "v" + a.getApplicationContext().getPackageManager().getPackageInfo(a.getPackageName(), 0).versionName + " (unreleased)";
		}
		catch (PackageManager.NameNotFoundException e)
		{
			e.printStackTrace();
		}

		return null;
	}
}
