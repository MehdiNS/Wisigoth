package com.project.map;

import com.project.wisigoth.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class MapService extends BroadcastReceiver {
	private static final int NOTIFICATION_ID = 1001;

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("Wisigoth onStop","map");
		Toast.makeText(context, "Welcome to my Area", 600).show();
		
		String key = LocationManager.KEY_PROXIMITY_ENTERING;
		Boolean entering = intent.getBooleanExtra(key, false);
		if (entering) {
			Log.d(getClass().getSimpleName(), "entering");
		}else {
			Log.d(getClass().getSimpleName(), "exiting");
		}
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		Intent notificationIntent = new Intent(context, WebviewActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
		Notification notification = createNotification();
		notification.setLatestEventInfo(context, "Wisigoth", "Cliquer ici pour découvrir le point d'intérêt !", pendingIntent);

		notificationManager.notify(NOTIFICATION_ID, notification);
	}

	private Notification createNotification() {
		Notification notification = new Notification();
		notification.icon = R.drawable.marker;
		notification.when = System.currentTimeMillis();
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		notification.defaults |= Notification.DEFAULT_VIBRATE;
		notification.defaults |= Notification.DEFAULT_LIGHTS;
		notification.ledARGB = Color.WHITE;
		notification.ledOnMS = 1500;
		notification.ledOffMS = 1500;
		return notification;
	}

}