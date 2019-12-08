package com.jsap.stepcounter;

import android.content.*;
import android.app.*;
import com.jsap.stepcounter.AppContent.*;

public class NotificationApplication extends Notification
{
	Context context;
	
	Builder builder;
	Notification notification;
	NotificationManager notificationManger;
	Intent intent;
	PendingIntent pendingIntent;
	
	final int ID = 1;
	
	public NotificationApplication(Context context) {
		this.context = context;
		
		intent = new Intent (context, MainActivity.class);
		intent.putExtra("EXTRA_MISC", true);
		intent.addFlags(AppResources.INTENT_FLAG_SINGLE_ACTIVITY);
		pendingIntent = PendingIntent.getActivity(context, PendingIntent.FLAG_UPDATE_CURRENT, intent, Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		
		builder = new Notification.Builder(context);
		builder.setContentText(AppResources.STATUS_IDLE);
		builder.setNumber(1);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setColorized(true);
		builder.setShowWhen(false);
		builder.setColor(AppResources.THEME_COLOR_PRIMARY);
        builder.setPriority(Notification.PRIORITY_MAX);
		builder.setOngoing(true);
		
		notification = builder.build();
		notificationManger = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	}
	
	public void showNotification(int counter, SavePrefsApplication records) {
		builder.setContentTitle("Steps: " + (counter + records.getStepCount())); // separate number steps (e.g. 32 756)
		builder.build();
		notificationManger.notify(ID, notification);
	}
	
	public void cancelNotification() {
		notificationManger.cancelAll();
	}
}
