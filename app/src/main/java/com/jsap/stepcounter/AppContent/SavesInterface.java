package com.jsap.stepcounter.AppContent;

public interface SavesInterface {
	public void setExitStatus(int flag);
	public void setTemporaryStepCount(int count);
	public void appendStepCount(int count);
	public void setStepCount(int count);
	public void setWarnFlag(boolean flag);

	public int getExitStatus();
	public int getTemporaryStepCount();
	public int getStepCount();
	public boolean getWarnFlag();
}
