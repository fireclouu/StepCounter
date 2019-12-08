 package com.jsap.stepcounter.AppContent;

import android.content.SharedPreferences;
import android.content.Context;
import com.jsap.stepcounter.AppResources;

public class SavePrefsApplication implements SavesInterface {
	private Context context;
	private SharedPreferences prefs;
	private SharedPreferences.Editor pref_editor;

	private static AppResources lib;

	public SavePrefsApplication(Context context) {
		this.context = context;
		this.prefs = context.getSharedPreferences(lib.PREF_NAME_MAIN, 0);
		this.pref_editor = prefs.edit();
	}


	@Override
	public void setExitStatus(int flag) {
		pref_editor.putInt(lib.ITEM_EXIT_STATUS, flag);
		pref_editor.apply();
	}

	@Override
	public void setTemporaryStepCount(int count) {
		pref_editor.putInt(lib.ITEM_TEMPORARY_STEP_COUNT, count);
		pref_editor.apply();
	}

	@Override
	public void appendStepCount(int count) {
		pref_editor.putInt(lib.ITEM_SAVED_STEP_COUNTER, count + this.getStepCount());
		pref_editor.apply();
	}

	@Override
	public void setStepCount(int count) {
		pref_editor.putInt(lib.ITEM_SAVED_STEP_COUNTER, count);
		pref_editor.apply();
	}

	@Override
	public void setWarnFlag(boolean flag) {
		pref_editor.putBoolean(lib.ITEM_WARN_FLAG, flag);
		pref_editor.apply();
	}

	@Override
	public int getExitStatus() {
		return prefs.getInt(lib.ITEM_EXIT_STATUS, lib.NULL_POINTER);
	}

	@Override
	public int getTemporaryStepCount() {
		return prefs.getInt(lib.ITEM_TEMPORARY_STEP_COUNT, lib.NULL_POINTER);
	}

	@Override
	public int getStepCount() {
		return prefs.getInt(lib.ITEM_SAVED_STEP_COUNTER, lib.NULL_POINTER);
	}

	@Override
	public boolean getWarnFlag() {
		return prefs.getBoolean(lib.ITEM_WARN_FLAG, false);
	}
}
