package com.gueei.applocker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StartupServiceReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("Detector", intent.getAction() + " " + "receivng braodcast");
		if (AppLockerPreference.getInstance(context).isServiceEnabled()){
			Log.d("Detector", intent.getAction() + " " + "Starting services");
			context.startService(new Intent(context, DetectorService.class));
		}
	}
}
